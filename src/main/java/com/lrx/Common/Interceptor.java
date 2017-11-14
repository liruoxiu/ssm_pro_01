package com.lrx.Common;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lrx.Common.Constant;



public class Interceptor implements HandlerInterceptor{
	private static Logger logger = LogManager.getLogger(Interceptor.class);  
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse rseponse, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg1, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub		
		/*for(Object val : request.getParameterMap().keySet())
			System.out.println(request.getParameterMap().get(val));*/
		
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		boolean result = true;
		HttpSession session = request.getSession();
		Object userId = session.getAttribute(Constant.SESSION_USERID);
		Object userName = session.getAttribute(Constant.SESSION_USERNAME);		
		if (userId == null || userId == "null")
		{
			//response.setStatus(404);
			response.sendError(401, "请求失败了！跳转登陆页面。");
			//response.sendRedirect( request.getContextPath() + "/Login.action");
			//System.out.println("Session会话超时，进入拦截器！");

			/*response.getOutputStream().println("<script>bjhnd.role.show();</script>");
			logger.debug("被拦截了！");
			System.out.println("被拦截了！");*/
			///String URL = request.getContextPath() + "/Login.action";
			///response.sendRedirect(URL);
			result = false;
		}
		return result;
	}

}
