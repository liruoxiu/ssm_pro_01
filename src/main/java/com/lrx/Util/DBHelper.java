package com.lrx.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;


//import org.apache.commons.validator.GenericValidator;
//import org.displaytag.tags.TableTagParameters;
//import org.displaytag.util.ParamEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class DBHelper extends JdbcDaoSupport {
	private static Logger logger = LoggerFactory.getLogger(DBHelper.class);
	public TransactionTemplate transactionTemplate;
	public LobHandler lobHandler;
	public StringHelper str = new StringHelper();

	public LobHandler getLobHandler() {
		return lobHandler;
	}

	public void setLobHandler(LobHandler lobHandler) {
		this.lobHandler = lobHandler;
	}

	public synchronized static DBHelper getInstance() {
		DBHelper db = null;
		try {
			db = (DBHelper) SpringHelper.getBean("dbHelper");
		} catch (Exception e) {
			logger.error("创建数据操作对象失败！");
		}
		return db;
	}

	public synchronized static Connection getDBConnection() {
		Connection conn = null;
		DBHelper db = DBHelper.getInstance();
		try {
			conn = db.getConnection();
		} catch (Exception e) {
			// logger.error("获取数据库Connection失败！");
			logger.error(e.toString());
		}
		return conn;
	}

	/**
	 * 查询条件确定时
	 * 
	 * @param sql
	 *            传入的sql语句: select *from table where name=? and msisdn like
	 *            '%?%'
	 * @param objects
	 *            :传入的参数 new Object[] { "张三",1234}
	 * @param rowMapper
	 * @return
	 */
	public Object queryForObject(String sql, Object[] objects,
			RowMapper rowMapper) {
		Object object = null;
		object = this.getJdbcTemplate().queryForObject(sql, objects, rowMapper);
		return object;
	}

	/**
	 * 返回一条记录
	 * 
	 * @param sql
	 *            传入的sql语句: select *from table where user_id=?
	 * @param objects
	 * @return
	 */
	public Map queryForMap(String sql, Object[] objects) {
		Map map = null;
		try {
			map = this.getJdbcTemplate().queryForMap(sql, objects);
		} catch (EmptyResultDataAccessException e) {

		} catch (Exception e) {
			logger.error(e.toString());
			logger.error(str.getSql(sql, objects));
		}
		if (map == null)
			map = new HashMap();
		return map;
	}

	public Map queryForMap(String sql) {
		Map map = null;
		try {
			map = this.getJdbcTemplate().queryForMap(sql, null);
		} catch (EmptyResultDataAccessException e) {

		} catch (Exception e) {
			logger.error(e.toString());
			logger.error(sql);
		}
		if (map == null)
			map = new HashMap();
		return map;
	}

	public String queryForString(String sql, Object[] args) {
		StringHelper str = new StringHelper();
		try {
			return str.notEmpty(this.getJdbcTemplate().queryForObject(sql,
					args, String.class));
		} catch (Exception ex) {
			logger.error(ex.toString());
			logger.error(str.getSql(sql, args));
			return "";
		}
	}

	public String queryForString(String sql) {
		StringHelper str = new StringHelper();
		try {
			return str.notEmpty(this.getJdbcTemplate().queryForObject(sql,
					null, String.class));
		} catch (Exception ex) {
			logger.error(sql);
			return "";
		}
	}

	/**
	 * 返回一条记录剔除null值
	 * 
	 * @param sql
	 *            传入的sql语句: select *from table where user_id=?
	 * @param objects
	 * @return
	 */
	public Map queryForMapNotNull(String sql, Object[] objects) {
		Map map = null;
		Map temp = new HashMap();
		StringHelper str = new StringHelper();
		try {
			map = this.getJdbcTemplate().queryForMap(sql, objects);
			if (map != null) {
				Set s = map.keySet();
				for (Iterator iter = s.iterator(); iter.hasNext();) {
					String key = str.notEmpty(iter.next()).toString();
					String value = str.notEmpty(map.get(key)).toString();
					temp.put(key, value);
				}
				map.clear();
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return temp;
	}
	/**
	 * 返回相应sequence的下一个值
	 * 
	 * @param sequenceName
	 *            sequence名称
	 * @return 下个值
	 */
	public String getNextSequenceValue(String sequenceName) {
		Map map = null;
		String nextVal = "";
		try {
			map = this.getJdbcTemplate().queryForMap(
					"select " + sequenceName + ".NEXTVAL SEQ from dual");
			nextVal = map.get("SEQ").toString();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return nextVal;
	}
	
	public String getSysdate(String time_formate) {
		Map map = null;
		String sysdate = "";
		try {
			map = this.getJdbcTemplate().queryForMap(
					"select to_char(sysdate,'"+time_formate+"') date_time from dual");
			sysdate = map.get("DATE_TIME").toString();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return sysdate;
	}

	/**
	 * 返回一条记录
	 * 
	 * @param sql
	 *            传入的sql语句: select *from table where user_id=?
	 * @param objects
	 * @return
	 */
	public List queryForList(String sql, Object[] objects) {
		List list = null;
		try {
			list = this.getJdbcTemplate().queryForList(sql, objects);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		if (list == null)
			list = new ArrayList();
		return list;
	}

	/**
	 * 查询条件不确定时
	 * 
	 * @param sql
	 *            sql_where拼接 sql="select * from table where name='"+v_name+"'";
	 * @param objects
	 * @return
	 */
	public List queryForList(String sql) {
		List list = null;
		try {
			list = this.getJdbcTemplate().queryForList(sql);
		} catch (Exception e) {
			logger.error(e.toString());
			logger.error(sql);
		}
		return list;
	}

	/**
	 * insert,update,delete 操作
	 * 
	 * @param sql
	 *            传入的语句 sql="insert into tables values(?,?)";
	 * @param objects
	 * @return 0:失败 1:成功
	 */
	public int update(String sql, Object[] objects) {
		int exc = 1;
		try {
			this.getJdbcTemplate().update(sql, objects);
		} catch (Exception e) {
			exc = 0;
			logger.error(e.toString());
			logger.error(str.getSql(sql, objects));
		}
		return exc;
	}

	public int update(String sql) {
		int exc = 1;
		try {
			this.getJdbcTemplate().update(sql);
		} catch (Exception e) {
			exc = 0;
			logger.error(sql);
			logger.error(e.toString());
		}
		return exc;
	}

	/**
	 * 返还记录数
	 * 
	 * @param sql
	 *            传入的sql语句 select count(*) from table where name=?
	 * @param objects
	 *            参数值
	 * @return -1:数据库异常
	 */
	public int queryForInt(String sql, Object[] objects) {
		int exc = -1;
		try {
			exc = this.getJdbcTemplate().queryForObject(sql,  new Object[] { objects }, Integer.class);
		} catch (Exception e) {
			exc = -1;
			logger.error(e.toString());
		}
		return exc;
	}

	/**
	 * 返还记录数
	 * 
	 * @param sql
	 *            传入的sql语句直接拼接好 sql="select count(*) from table where
	 *            name='"+mike+"'"
	 * @return
	 */
	public int queryForInt(String sql) {
		
		return this.getJdbcTemplate().queryForObject(sql, Integer.class);
	}

	/**
	 * 返还记录数--返回记录数超出int范围
	 * 
	 * @param sql
	 *            传入的sql语句直接拼接好 sql="select count(*) from table where
	 *            name='"+mike+"'"
	 * @return
	 */
	public Long queryForLong(String sql) {
		return this.getJdbcTemplate().queryForObject(sql, Long.class);
	}

	/**
	 * displayTag 数据库分页
	 * 
	 * @param sql
	 * @param pageSize
	 * @param request
	 * @return
	 */
//	public List getForList(String sql, int pageSize, HttpServletRequest request) {
//		// 页码标识的参数名
//		String pageIndexName = new org.displaytag.util.ParamEncoder("row")
//				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
//		// 自动取得当前页码标识,开始默认为0
//		int pageIndex = GenericValidator.isBlankOrNull(request
//				.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request
//				.getParameter(pageIndexName)) - 1);
//		// beginIndex与size在displayTag显示时调用
//		// beginIndex为当前开始序列
//		// size为总数,displayTag根据size与pageSize自动计算总页码
//		request.setAttribute("beginIndex", pageSize * pageIndex);
//		int size = this.queryForInt("select count(1) from (" + sql + ")");
//		request.setAttribute("size", new Integer(size));
//		int first = pageIndex * pageSize + 1;
//		int last = first + pageSize;
//		if (request.getParameter("6578706f7274") == null) {
//			sql = "SELECT * FROM (SELECT AA.*, rownum rr  FROM (" + sql
//					+ " )AA  )BB where rr<" + last + " and rr>=" + first;
//		}
//		return this.queryForList(sql);
//	}
	
	/**
	 * 非displaytag标签数据库分页
	 * @param sql sql语句
	 * @param pageSize 分页条数
	 * @param request
	 * @return
	 */
//	public List getForListPage(String sql, int pageSize, HttpServletRequest request) {
//		// 自动取得当前页码标识,开始默认为0
//		int pageIndex = GenericValidator.isBlankOrNull(request
//				.getParameter("pageIndex")) ? 0 : (Integer.parseInt(request
//				.getParameter("pageIndex")) - 1);
//		//求出数据总条数
//		int size = this.queryForInt("select count(1) from (" + sql + ")");
//		request.setAttribute("size", new Integer(size));
//		//从first开始取数据
//		int first = pageIndex * pageSize + 1;
//		//数据取至last结束
//		int last = first + pageSize;
//		sql = "SELECT * FROM (SELECT AA.*, rownum rr  FROM (" + sql
//				+ " )AA  )BB where rr<" + last + " and rr>=" + first;
//		System.out.println("数据库分页语句："+sql);
//		return this.queryForList(sql);
//	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * 事务处理
	 * 
	 * @param batchSqls
	 * @return
	 */
	public int doInTransaction(final BatchSql batch) {
		int exc = 1;
		if (batch == null) {
			exc = 0;
		}
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(
						TransactionStatus status) {
					List sqlList = batch.getSqlList();
					for (int i = 0; i < sqlList.size(); i++) {
						Map sqlMap = (Map) sqlList.get(i);
						String sql = (String) sqlMap.get("sql");
						Object[] objects = (Object[]) sqlMap.get("objects");
						String sql_type = (String) sqlMap.get("sql_type");
						// 如果包括大字段操作
						if (sql_type != null && sql_type.equals("clob")) {
							int[] colIndex = (int[]) sqlMap.get("clob_index");
							updateClob(sql, objects, colIndex);
						} else {
							getJdbcTemplate().update(sql, objects);
						}
					}
				}
			});
		} catch (Exception e) {
			exc = 0;
			logger.error(e.toString());
		}
		return exc;
	}

	/**
	 * 注意
	 * 
	 * @param sql
	 * @param inParam
	 * @param out
	 * @return
	 */
	public ProcHelper getProcHelper(String sql) {
		ProcHelper proc = null;
		try {
			proc = new ProcHelper(this.getDataSource(), sql);
			proc.setSql(sql);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return proc;
	}

	/**
	 * 
	 * @param sql
	 *            要执行的sql
	 * @param objects
	 *            参数数组
	 * @param clobIndex
	 *            指定大字段的参数序号
	 * @return
	 */
	public int updateClob(final String sql, final Object[] objects,
			final int[] clobIndex) {
		int exc = 1;
		try {
			getJdbcTemplate()
					.execute(
							sql,
							new AbstractLobCreatingPreparedStatementCallback(
									lobHandler) {
								protected void setValues(PreparedStatement ps,
										LobCreator lobCreator)
										throws SQLException {
									boolean clobFlag = false;
									for (int i = 1; i <= objects.length; i++) {
										Object param = objects[i - 1];
										clobFlag = false;
										for (int j = 0; j < clobIndex.length; j++) {

											if (i == clobIndex[j]) {
												clobFlag = true;
												break;
											}
										}
										if (clobFlag == true) {
											lobCreator.setClobAsString(ps, i,
													(String) param);
										} else {
											ps.setObject(i, param);
										}

									}
								}
							});

		} catch (Exception e) {
			exc = 0;
			logger.error(e.toString());
		}
		exc = 1;
		return exc;

	}
	/*
	 /**
		 * 直接读取包括大字段的记录
		 * 
		 * @param sql
		 * @param objects
		 * @return
		 */
	/*
	 * public Map queryForClobMap(String sql,Object[] objects) { Map map=null;
	 * map = (Map) getJdbcTemplate().query(sql, objects, new
	 * ResultSetExtractor() { public Object extractData(ResultSet rs) throws
	 * SQLException, DataAccessException { // TODO Auto-generated method stub
	 * ResultSetMetaData rsmd = rs.getMetaData(); int num =
	 * rsmd.getColumnCount(); Map hashMap=new HashMap(); if (rs.next()) { for
	 * (int i = 1; i <= num; i++) { String key = rsmd.getColumnName(i); String
	 * colType=rsmd.getColumnTypeName(i); System.out.println("字段数据类型："+colType);
	 * String value=""; if(colType.equals("CLOB")) {
	 * value=lobHandler.getClobAsString(rs, i); } else { value =
	 * rs.getString(i); } if (value == null) value = ""; hashMap.put(key,
	 * value); } } return hashMap; } }); return map; }
	 */

	/**
	 * 返回排序子语句
	 */
//	public String addSortString(HttpServletRequest request,
//			String otherSortColumn) {
//		String sort = "";
//		// String pageIndexName = new org.displaytag.util.ParamEncoder("row")
//		// .encodeParameterName(org.displaytag.tags.TableTagParameters.);
//		String sortParameterName = new ParamEncoder("row")
//				.encodeParameterName(TableTagParameters.PARAMETER_SORT);
//		String sortColumnName = request.getParameter(sortParameterName);
//		String orderParameter = new ParamEncoder("row")
//				.encodeParameterName(TableTagParameters.PARAMETER_ORDER);
//		String orderType = request.getParameter(orderParameter);
//
//		if (sortColumnName != null && !sortColumnName.equals("")) {
//			sort = " order by " + sortColumnName;
//			if (orderType != null && !orderType.equals("")) {
//				sort += orderType.equals("1") ? " asc " : " desc";
//			}
//			if (!otherSortColumn.equals(""))
//				sort += "," + otherSortColumn;
//		} else if (!otherSortColumn.equals("")) {
//			sort += " order by " + otherSortColumn;
//		}
//		return sort;
//
//	}
}
