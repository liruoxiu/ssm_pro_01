package com.lrx.Actions.System;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.lrx.Common.Constant;
import com.lrx.QX.RequestToMethodItem;
import com.lrx.Service.System.SystemService;

@Controller
public class SystemAction {
	@Autowired
	private SystemService systemService;

    private RequestMappingHandlerMapping handlerMapping;
 
    @RequestMapping("/index1")
    public void index(Long id, HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
 
        Map<RequestMappingInfo, HandlerMethod> map =  this.handlerMapping.getHandlerMethods();
        Iterator<?> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            
            System.out.println(entry.getKey() +"\n" + entry.getValue());
        }
        //请求url和处理方法的映射       
        List<RequestToMethodItem> requestToMethodItemList = new ArrayList<RequestToMethodItem>();
        
        //本项目只需要RequestMappingHandlerMapping中的URL映射           
		if (handlerMapping instanceof RequestMappingHandlerMapping) {
			RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
			Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
			for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()) {
				RequestMappingInfo  requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
				HandlerMethod mappingInfoValue = requestMappingInfoHandlerMethodEntry.getValue();
				
				RequestMethodsRequestCondition methodCondition = requestMappingInfo.getMethodsCondition();
				
				//String s = SortedArraySet<methodCondition.getMethods()>.;
				
				String requestType = SetUtils.orderedSet(methodCondition.getMethods()).toString();;
				PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
				String requestUrl = SetUtils.orderedSet(patternsCondition.getPatterns()).toString();
				String controllerName = mappingInfoValue.getBeanType().toString();
				String requestMethodName = mappingInfoValue.getMethod().getName();
				Class<?>[] methodParamTypes = mappingInfoValue.getMethod().getParameterTypes();
				RequestToMethodItem item = new RequestToMethodItem(requestUrl, requestType, controllerName,requestMethodName, methodParamTypes);
				requestToMethodItemList.add(item);
			}
		}
    }	


	@RequestMapping("/Main")
	public String GetMain() {
		return "Main";
	}

	@RequestMapping("/Login")
	public String GetLogin() {
		return "Login";
	}

	@RequestMapping("/System/modifyPwd")
	@ResponseBody
	public Object modifyPwd(String oldPassword, String newPassword,
			HttpSession session) {
		Object userID = session.getAttribute(Constant.SESSION_USERID);
		return systemService.modifyPwd(oldPassword, newPassword,
				String.valueOf(userID));
	}

	@RequestMapping("/System/lock")
	@ResponseBody
	public Object lock(String newPassword, HttpSession session) {
		Object userID = session.getAttribute(Constant.SESSION_USERID);
		return systemService.lock(newPassword, String.valueOf(userID));
	}

	/**
	 * 系统退出
	 * 
	 * @param session
	 * @return 会话跳转
	 */
	@RequestMapping("/systemQuit")
	public String systemQuit(HttpSession session) {
		session.removeAttribute(Constant.SESSION_CHECKCODE);
		session.removeAttribute(Constant.SESSION_USERID);
		session.removeAttribute(Constant.SESSION_USERNAME);
		session.removeAttribute(Constant.SESSION_DEPARTMENTID);
		session.removeAttribute(Constant.SESSION_USER_ID);
		session.removeAttribute(Constant.SESSION_THEME);
		session.removeAttribute(Constant.SESSION_LAYOUT);
		
		return "Login";
	}

	@RequestMapping("/System/systemInitialization")
	@ResponseBody
	public Object systemInitialization(HttpSession session) {
		Object userID = session.getAttribute(Constant.SESSION_USERID);
		return systemService.systemInitialization(String.valueOf(userID));
	}
	@RequestMapping("/GetCaptcha")
	public void GetCaptcha(HttpSession session, HttpServletResponse response, String code) {
		systemService.GetCaptcha(session, response, code);
	}

	@RequestMapping("/CheckLogin")
	@ResponseBody
	public Object CheckLogin(String userId, String password, String Captcha,HttpSession session) {
		return systemService.CheckLogin(userId, password, Captcha,session);
	}
	@RequestMapping("/System/saveUserOption")
	@ResponseBody
	public Object saveUserOption(String theme, String layout, HttpSession session)
	{
		return systemService.saveUserOption(theme, layout, session);
	}
	/*@RequestMapping("/System/uploadFile")
	@ResponseBody
	public Object uploadFile(HttpServletRequest request)
	{
		try {
			systemService.uploadFile(request, "sourceFile", "d://aab.png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // 获得输入流：   
		return "";
	}*/
	
    /*获取UserID值*/
	@RequestMapping("/System/getSessionUserId")
	@ResponseBody
	public Object getSessionUserId(HttpSession session){
		return session.getAttribute(Constant.SESSION_USER_ID);
	}
	/*url跳转，权限验证*/
	
	
}