package com.lrx.Common;


import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

//, RowBounds.class, ResultHandler.class 
@Intercepts(value = { 
		@Signature(args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}, method = "query", type = Executor.class),
		//@Signature(method = "prepare", type = StatementHandler.class, args = {Configuration.class})
		})

public final class Paginator implements Interceptor
{
	//private static Logger logger = LoggerFactory.getLogger(Paginator.class);
	private String databaseType;
	static int MAPPED_STATEMENT_INDEX = 0;
	static int PARAMETER_INDEX = 1;
	static int ROWBOUNDS_INDEX = 2;
	static int RESULT_HANDLER_INDEX = 3;
	static Logger logger = LogManager.getLogger(Paginator.class.getName());
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
		// TODO Auto-generated method stub
		logger.debug("进入拦截器");
		final Object[] queryArgs = invocation.getArgs();
		final MappedStatement ms = (MappedStatement)queryArgs[MAPPED_STATEMENT_INDEX];
        final Object parameter = queryArgs[PARAMETER_INDEX];
        final RowBounds rowBounds = (RowBounds)queryArgs[ROWBOUNDS_INDEX];
        //System.out.println(ms.getBoundSql(0));
        System.out.println(rowBounds.getLimit());
        System.out.println(rowBounds.getOffset());
        System.out.println(parameter);
        //final PageBounds pageBounds = new PageBounds(rowBounds);
		//System.out.println(ms.getBoundSql(invocation.getArgs()[1]).getSql());
//		RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget(); 
//		System.out.println(handler.getBoundSql());
	       //StatementHandler delegate = (StatementHandler)ReflectUtil.getFieldValue(handler, "delegate");    
	       //BoundSql boundSql = delegate.getBoundSql();  
	       //Object obj = boundSql.getParameterObject();  
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub		
		databaseType = properties.getProperty("databaseType");
		System.out.println("数据库类型=" + databaseType);
	}
}
