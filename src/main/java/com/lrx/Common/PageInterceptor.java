package com.lrx.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;





@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })  
public class PageInterceptor implements Interceptor {  
	
	private static final Log logger = LogFactory.getLog(PageInterceptor.class);
	
    String defaultDialect = "mysql"; // 数据库类型(默认为mysql)
    private static String defaultPageSqlId = ".*Page$"; // 需要拦截的ID(正则匹配)
    private static String dialect = ""; // 数据库类型(默认为mysql)
    private static String pageSqlId = ".*Page.*"; // 需要拦截的ID(正则匹配)
    
	
    @Override  
    public Object intercept(Invocation invocation) throws Throwable {  
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();  
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);  
        BoundSql boundSql = statementHandler.getBoundSql();  
      
//        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
//        while (metaStatementHandler.hasGetter("h")) {
//            Object object = metaStatementHandler.getValue("h");
//            metaStatementHandler = MetaObject.forObject(object, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
//        }
//        // 分离最后一个代理对象的目标类
//        while (metaStatementHandler.hasGetter("target")) {
//            Object object = metaStatementHandler.getValue("target");
//            metaStatementHandler = MetaObject.forObject(object, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
//        }
//        
//        Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
//		// Page对象获取，“信使”到达拦截器！
//        Page page = searchPageWithXpath(boundSql.getParameterObject(), ".","page", "*/page");
//        pageSqlId = configuration.getVariables().getProperty("pageSqlId");
//        if (null == pageSqlId || "".equals(pageSqlId)) {
//            logger.warn("Property pageSqlId is not setted,use default '.*Page$' ");
//            pageSqlId = defaultPageSqlId;
//        }
        Page page = new Page();
        
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        String id = mappedStatement.getId();  
        logger.debug("sql语句的id : " + id);
        Object param = boundSql.getParameterObject();  
        
        if (mappedStatement.getId().matches(pageSqlId)) {
	        if (null != param && Map.class.isAssignableFrom(param.getClass())) {  
	            Map paramMap = (Map) param;  
	            Object _pageSize = paramMap.get("pageSize");  
	            Object _pageNo = paramMap.get("pageNo");
	            page =(Page)paramMap.get("Page");	            
	            //page对象存取
	            if (page!=null){
	                int pageSize = page.getPageSize();  
	                int pageNo = page.getPageNo();
	                int totalRecord=setTotalResult(boundSql, (Connection) invocation.getArgs()[0], metaStatementHandler, paramMap);  
	                page.setTotalRecord(totalRecord);	                
	                String sql = getPageSql(pageSize, pageNo, boundSql);  
	                metaStatementHandler.setValue("delegate.boundSql.sql", sql);  
	                metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);  
	                metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);  
	            }
	            //传递参数存取	            
//	            if (_pageNo != null && _pageSize != null) {  
//	                int pageSize = (Integer) _pageSize;  
//	                int pageNo = (Integer) _pageNo;
//	                int totalRecord=setTotalResult(boundSql, (Connection) invocation.getArgs()[0], metaStatementHandler, paramMap);  
//	                
//	                if (page!=null){
//	                	page.setTotalRecord(totalRecord);	
//	                }	                
//	                
//	                String sql = getPageSql(pageSize, pageNo, boundSql);  
//	                metaStatementHandler.setValue("delegate.boundSql.sql", sql);  
//	                metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);  
//	                metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);  
//	            }  
	        }  
        }
        return invocation.proceed();  
    }
  
        // 这个分页方法是借助于网上的  
    private String getPageSql(int pageSize, int pageNo, BoundSql boundSql) {  
        StringBuffer sql = new StringBuffer(boundSql.getSql());  
        sql.insert(0, "select u.*, rownum r from (").append(") u where rownum < ").append(pageSize * pageNo);  
        sql.insert(0, "select * from (").append(") where r >= ").append((pageSize - 1) * pageNo);  
        return sql.toString();  
    }  
    private int setTotalResult(BoundSql boundSql, Connection conn, MetaObject metaObject, Map param) throws SQLException {  
        int total=0;
    	String countSql = "select count(*) from ( " + boundSql.getSql() + " ) total";  
        PreparedStatement prepareStatement = conn.prepareStatement(countSql);  
        ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");  
        parameterHandler.setParameters(prepareStatement); // 给sql语句设置参数  
        ResultSet resultSet = prepareStatement.executeQuery();  
        if (resultSet.next()) {  
            param.put("total", resultSet.getObject(1));  
            total=resultSet.getRow();
        }  
        if (resultSet != null) {  
            resultSet.close();  
        }  
        if (prepareStatement != null) {  
            prepareStatement.close();  
        }  
        
        return total;
    } 
    
	/**
	 * 根据给定的xpath查询Page对象
	 */
	private Page searchPageWithXpath(Object o, String... xpaths) {
		JXPathContext context = JXPathContext.newContext(o);
		Object result;
		for (String xpath : xpaths) {
			try {
				result = context.selectSingleNode(xpath);
			} catch (Exception e) {
				continue;
			}
			if (result instanceof Page) {
				return (Page) result;
			}
		}
		return null;
	}
    
    @Override  
    public Object plugin(Object target) {  
        return Plugin.wrap(target, this);  
    }  
    @Override  
    public void setProperties(Properties properties) {  
  
    }  
}  