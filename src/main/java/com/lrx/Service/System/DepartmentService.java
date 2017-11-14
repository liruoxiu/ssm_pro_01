package com.lrx.Service.System;

import java.util.List;
import java.util.Map;

import com.lrx.Model.System.TDepartment;
import com.lrx.Model.System.TRole;
import com.lrx.Model.System.TUsers;
/***
 * 部门业务接口
 * @author 张鹏
 *
 */
public interface DepartmentService {
	public Object SaveDepartment(TDepartment department);
	public Object UpdateDepartment(TDepartment department);
	public Object DeleteDepartment(long id);
	public List<Map<String, Object>> GetDepartments(long id);
	/***
	 * 获取用户信息列表
	 * @param id 部门ID
	 * @return
	 */
	public Object GetUsers(long departmentID, long page, long rows);
	public Object SaveUser(TUsers users);
	public Object UpdateUser(TUsers users);
	public Object DeleteUser(long id);
	/***
	 * 重置用户密码
	 */
	public Object ResetUserPassword(TUsers users);
	/***
	 * 重置为最后一次使用的密码
	 */
	public Object BackUserPassword(long id);
	/***
	 * 锁定用户和解锁用户
	 */
	public Object LockUser(long id, String userId);
	public Object UnLockUser(long id, String userId);
	/***
	 * 获取用户角色
	 * @param id
	 * @return
	 */
	public Object GetRoles(long id);
	public Object SaveRole(TRole role);
	public Object UpdateRole(TRole role);
	public Object DeleteRole(long id);
}