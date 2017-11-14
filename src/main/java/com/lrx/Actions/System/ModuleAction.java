package com.lrx.Actions.System;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lrx.Common.Constant;
import com.lrx.Model.System.TModule;
import com.lrx.Service.System.ModuleService;

@Controller
public class ModuleAction {
	@Autowired
	private ModuleService moduleService;
	/***
	 * 
	 * @return
	 */
	@RequestMapping(value = "/System/getModule", method = RequestMethod.POST)
	@ResponseBody
	public Object getModule() {
		return moduleService.getModule(-1L);
	}
	
	@RequestMapping(value="/System/saveModule", method = RequestMethod.POST)
	@ResponseBody
	public Object saveModule(TModule module,HttpSession session)
	{
		String userName=session.getAttribute(Constant.SESSION_USERNAME).toString();
		module.setPublished(new Date());
		module.setReleaseUserId(userName);
		module.setType(2L);
		module.setIcon("icon-app");
		module.setVersion("1.0");
		return moduleService.saveModule(module,userName);
	}
	@RequestMapping(value="/System/saveSystem", method = RequestMethod.POST)
	@ResponseBody
	public Object saveSystem(TModule module,HttpSession session)
	{
		String userName=session.getAttribute(Constant.SESSION_USERNAME).toString();
		module.setPublished(new Date());
		module.setReleaseUserId(userName);
		module.setType(1L);
		module.setIcon("icon-apps");
		return moduleService.saveModule(module,userName);
	}
	@RequestMapping(value="/System/updateSystem", method = RequestMethod.POST)
	@ResponseBody
	public Object updateSystem(TModule module)
	{
		return moduleService.updateSystem(module);
	}
	@RequestMapping(value="/System/updateModule", method = RequestMethod.POST)
	@ResponseBody
	public Object updateModule(TModule module)
	{
		return moduleService.updateSystem(module);
	}
	@RequestMapping(value="/System/deleteModule", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteModule(long id) {
		return moduleService.deleteModule(id);
	}
	
	@RequestMapping(value="/System/getModuleById", method = RequestMethod.POST)
	@ResponseBody
	public Object getModuleById(long id){
		Object moduleInfo= moduleService.getModuleById(id);
		return moduleInfo;
	}
	@RequestMapping(value="/System/getModuleRole", method = RequestMethod.POST)
	@ResponseBody
	public Object getModuleRole(long id){
		return moduleService.getModuleRole(id);
	}
	@RequestMapping(value="/System/getRoleUsers", method = RequestMethod.POST)
	@ResponseBody
	public Object getRoleUsers(long id){
		return moduleService.getRoleUsers(id);
	}
	@RequestMapping(value="/System/updateAccessCotrol", method = RequestMethod.POST)
	@ResponseBody
	public Object updateAccessCotrol(long id){
		return moduleService.updateAccessCotrol(id);
	}
}
