package com.lrx.Actions.System;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lrx.Model.System.TRole;
import com.lrx.Service.System.RoleService;

@Controller
@RequestMapping("/System")
public class RoleAction {
	@Autowired
	RoleService roleService;

	@RequestMapping("/Role")
	public String GetPageRole() {
		return "SystemManager/Role";
	}	
	
	
	@RequestMapping(value = "/GetRoles", method = RequestMethod.POST)
	@ResponseBody
	public Object GetRoles(@RequestParam(defaultValue = "-1") long id) {
		return roleService.GetRoles(id);
	}

	@RequestMapping(value = "/SaveRole", method = RequestMethod.POST)
	@ResponseBody
	public Object SaveRole(TRole role) {
		return roleService.SaveRole(role);
	}

	@RequestMapping(value = "/UpdateRole", method = RequestMethod.POST)
	@ResponseBody
	public Object UpdateRole(TRole role) {
		return roleService.UpdateRole(role);
	}

	@RequestMapping(value = "/DeleteRole", method = RequestMethod.POST)
	@ResponseBody
	public Object DeleteRole(long parentId) {
		return roleService.DeleteRole(parentId);
	}
	
	@RequestMapping(value = "/DeleteUserRole", method = RequestMethod.POST)
	@ResponseBody
	public Object DeleteUserRole(long id){
		return roleService.DeleteUserRole(id);
	}
	
	@RequestMapping(value = "/GetUserRole", method = RequestMethod.POST)
	@ResponseBody
	public Object GetUserRole(@RequestParam(defaultValue = "-1") long roleID,
			String key, String search, int page, int rows,HttpServletRequest request) {
		String queryCond="";
		
		if (search != null && !"".equals(search)) {
			try {
				queryCond=java.net.URLDecoder.decode(search, "UTF-8");
			} catch (Exception e) {

			}
		}
		
		return roleService.GetUserRole(roleID,key,queryCond, page, rows);
	}

	//add by gaishm 20140610 begin
	@RequestMapping(value = "/getRoleModule", method = RequestMethod.POST)
	@ResponseBody
	public Object getRoleModule(@RequestParam(defaultValue = "-1") long roleId,
			String key,String search,int page,int rows ){
		String queryCond="";
		
		if (search != null && !"".equals(search)) {
			try {
				queryCond=java.net.URLDecoder.decode(search, "UTF-8");
			} catch (Exception e) {

			}
		}
		return roleService.getRoleModule(roleId,key,queryCond, page, rows);
	}
	
	
	@RequestMapping(value = "/getRoleModuleList", method = RequestMethod.POST)
	@ResponseBody
	public Object getRoleModuleList(@RequestParam(defaultValue = "-1") long roleId){
		return null;
	}
	
	@RequestMapping(value = "/getModuleTree", method = RequestMethod.POST)
	@ResponseBody
	public Object getModuleTree(@RequestParam(defaultValue = "0") long roleId){
		return roleService.getModuleTree(roleId);
	}
	
	/**
	 * 取得全部模块树
	 * @return 全部模块树
	 */
	@RequestMapping(value = "/getAllModuleTree", method = RequestMethod.POST)
	@ResponseBody
	public Object getAllModuleTree(){
		return roleService.getAllModuleTree();
	}
	
	
	@RequestMapping(value = "/saveRoleModule", method = RequestMethod.POST)
	@ResponseBody
	public Object saveRoleModule(
			@RequestParam(value = "selectModuleID[]") String[] selectModuleID,
			long roleID, HttpSession session){
		
		    return roleService.saveRoleModule(selectModuleID, roleID, session);
	}
	
	@RequestMapping(value = "/deleteRoleModule", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteRoleModule(long roleID, HttpSession session){
		 return roleService.deleteRoleModule( roleID, session);
	}
	
	//add by gaishm 20140610 end
	
	@RequestMapping(value = "/GetDepartUser", method = RequestMethod.POST)
	@ResponseBody
	public Object GetDepartUser(@RequestParam(defaultValue = "-1") long id,@RequestParam(defaultValue = "0") long roleId) {
		return roleService.GetDepartUser(id,roleId);
	}

	@RequestMapping(value = "/SaveDepartUser", method = RequestMethod.POST)
	@ResponseBody
	public Object SaveDepartUser(
			@RequestParam(value = "selectUserID[]") long[] selectUserID,
			long RoleID, HttpSession session) {
		return roleService.SaveDepartUser(selectUserID, RoleID,
				session);
	}
	
	
	/**
	 * 根据画面上选择的角色Ids     
	 * @param selectRoleIds 选择的角色Id
	 * @return 角色列表
	 */
	@RequestMapping(value = "/getRoleListByRoleIds", method = RequestMethod.POST)
	@ResponseBody
	public Object getRoleListByRoleIds(String selectRoleIds){
		
		return roleService.getRoleListByRoleIds(selectRoleIds);
	}
	
	/**
	 * 根据画面上选择的角色Id
	 * @param selectRoleIds 选择的角色Id
	 * @return 用户列表
	 */
	
	@RequestMapping(value = "/getUserListByRoleIds", method = RequestMethod.POST)
	@ResponseBody
	public Object getUserListByRoleIds(String selectRoleIds){
		return roleService.getUserListByRoleIds(selectRoleIds);
	}
	
	/**
	 * 
	 * @param selectRoleIds 选择的角色Id
	 * @return
	 */
	@RequestMapping(value = "/getAllRoleList", method = RequestMethod.POST)
	@ResponseBody
	public Object getAllRoleList(String selectRoleIds){
		return roleService.getAllRoleList(selectRoleIds);
	}
	/***
	 * 根据角色显示用户列表，返回值如：张鹏/ZHANGPENG
	 * @param selectRoleIds
	 * @return
	 */
	@RequestMapping(value = "/getUserByRoleIds", method = RequestMethod.POST)
	@ResponseBody
	public Object getUserByRoleIds(String selectRoleIds){
		return roleService.getUserByRoleIds(selectRoleIds);
	}
	
	
	@RequestMapping(value = "/getRoleFlowByRoleIds")
	@ResponseBody
	public Object getRoleFlowByRoleIds(String selectRoleIds){		
		return roleService.getRoleFlowByRoleIds(selectRoleIds);
	}
}
