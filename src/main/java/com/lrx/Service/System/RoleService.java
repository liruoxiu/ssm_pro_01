package com.lrx.Service.System;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lrx.Model.System.TDepartment;
import com.lrx.Model.System.TRole;
import com.lrx.Model.System.TUsers;
/***
 * 部门业务接口
 * @author 张鹏
 *
 */
public interface RoleService {
	/***
	 * 获取用户角色
	 * @param id
	 * @return
	 */
	public Object GetRoles(long id);
	public Object SaveRole(TRole role);
	public Object UpdateRole(TRole role);
	public Object DeleteRole(long id);
	
	/*
	 * 删除用户角色Id
	 */
	public Object DeleteUserRole(long id);
	
	/***
	 * 取得已授权角色的用户
	 * @param id
	 * @return
	 */
	public Object GetUserRole(long roleID,String queryKey,String queryCond, int page, int rows);
	
	/***
	 * 取得部门用户树 
	 * @param id     部门ID
	 * @param roleId 角色ID
	 * @author gaishm
	 */
	public Object GetDepartUser(long id,long roleId);
	
	/***
	 * 取得模块树
	 * @param roleId 用于左侧树节点选择的角色Id
	 */
	public List<Map<String, Object>> getModuleTree(long roleId);
	
	/**
	 * 取得全部模块树
	 * @return 全部模块树
	 */
	public List<Map<String, Object>> getAllModuleTree();
	
	/***
	 * 取得角色模块对应关系表
	 * @param roleId 角色Id
	 */
	public Object getRoleModuleList( long roleId);
	
	/**
	 * 根据角色Id得到该角色的模块信息
	 * @param roleId 角色Id
	 * @param key    模块检索方式
	 * @param search 检索内容
	 * @param page   页码
	 * @param rows   每页行数
	 * @return 模块信息列表
	 */
	
	public Object getRoleModule(long roleId,String key,String search,int page,int rows );
	
	/***
	 * 保存授权用户
	 * @param selectUserID
	 * @param RoleID
	 * @return
	 */
	public Object SaveDepartUser(long[] selectUserID, long RoleID, HttpSession session);
	
	/**
	 * 保存选择的角色模块
	 * @param selectModuleID 选择的的模块ID
	 * @param RoleID 角色Id
	 * @param session
	 * @return
	 */
	public Object saveRoleModule(String[] selectModuleID,long roleID, HttpSession session);
	
	/**
	 * 根据角色Id删除模块关系表
	 * @param roleID  角色Id
	 * @param session session
	 * @return
	 */
	public Object deleteRoleModule(long roleID, HttpSession session);
	
	//add by gaishm 2014/07/21 begin
	/**
	 * 根据画面上选择的角色Ids     
	 * @param selectRoleIds 选择的用户Id
	 * @return 角色列表
	 */
	public Object getRoleListByRoleIds(String selectRoleIds);
	
	/**
	 * 根据画面上选择的角色Id
	 * @param selectRoleIds 选择的角色Id
	 * @return 用户列表
	 */
	public Object getUserListByRoleIds(String selectRoleIds);
	
	
	/**
	 * 返回全部角色和已经选择的角色
	 * @param selectRoleIds 选择的角色Ids
	 * @return 
	 */
	public List<Map<String, Object>> getAllRoleList(String selectRoleIds);
	//add by gaishm 2014/07/21 end
	public Object getRoleFlowByRoleIds(String selectRoleIds);
	public Object getUserByRoleIds(String selectRoleIds);
}