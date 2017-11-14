package com.lrx.Service.System;

import java.util.List;
import java.util.Map;

import com.lrx.Model.System.TModule;

public interface ModuleService {
	/***
	 * 取得系统功能 模块列表
	 * @param id 数据表中主键id
	 * @return
	 */
	public List<Map<String, Object>> getModule(long id);
	public Object saveModule(TModule module,String UserName);
	public Map<String, Object> updateSystem(TModule module);
	
	public Object deleteModule(long id);
	
	public TModule getModuleById(long id);
	/***
	 * 取得对应模块授权的所有角色
	 * @param id
	 * @return
	 */
	public Object getModuleRole(long id);
	/***
	 * 取得角色对应授权用户
	 * @param id <b>角色id</b>
	 * @return
	 */
	public Object getRoleUsers(long id);
	public Object updateAccessCotrol(long id);
	
}
