package com.lrx.Service.System.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lrx.Common.Constant;
import com.lrx.Dao.System.TDepartmentMapper;
import com.lrx.Dao.System.TRoleMapper;
import com.lrx.Dao.System.TUserRoleMapper;
import com.lrx.Dao.System.TUsersMapper;
import com.lrx.Model.System.TDepartment;
import com.lrx.Model.System.TDepartmentExample;
import com.lrx.Model.System.TRole;
import com.lrx.Model.System.TRoleExample;
import com.lrx.Model.System.TUserRoleExample;
import com.lrx.Model.System.TUsers;
import com.lrx.Model.System.TUsersExample;
import com.lrx.Service.System.DepartmentService;


//org.springframework.security.providers.encoding.Md5PasswordEncoder
@Service
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired(required=false)
	private TDepartmentMapper departmentMapper;
	@Autowired(required=false)
	private TUsersMapper userMapper;
	@Autowired(required=false)
	private TRoleMapper roleMapper;
	@Autowired(required=false)
	private TUserRoleMapper userRoleMapper;
 	
	@Override
	public List<Map<String, Object>> GetDepartments(long id) {
		final TDepartmentExample DepartmentExample;
		final List<TDepartment> depList;
		final List<Map<String, Object>> DepMap;
		DepartmentExample = new TDepartmentExample();		
		DepartmentExample.createCriteria().andParentIdEqualTo(id);
		DepartmentExample.setOrderByClause("DISPLAY_INDEX");
		depList = departmentMapper.selectByExample(DepartmentExample);
		// TODO Auto-generated method stub
		DepMap = new ArrayList<Map<String, Object>>();
		for(TDepartment value : depList)
		{
			Map<String, Object> depItemMap = new HashMap<String, Object>();
			depItemMap.put("id", value.getId());
			depItemMap.put("text", value.getName());
			DepartmentExample.clear();
			DepartmentExample.createCriteria().andParentIdEqualTo(value.getId());
			long rowCount = departmentMapper.countByExample(DepartmentExample);
			depItemMap.put("state", rowCount > 0 ? "closed" : "open");
			//图标CSS
			depItemMap.put("iconCls", value.getIcon());
			//生成全名称路径和全id路径 属性
			Map<String, Object> attrib = new HashMap<String, Object>(); 
			attrib.put("fullname", value.getFullPathName());
			attrib.put("fullpath", value.getFullPathId());
			attrib.put("code", value.getCode());
			depItemMap.put("attributes", attrib);
			DepMap.add(depItemMap);
		}
		return DepMap;
	}
	@Override
	public Object SaveDepartment(TDepartment department) {
		// TODO Auto-generated method stub
		Map<Object, Object> messagMap = new HashMap<Object, Object>();
		department.setFullPathName(department.getFullPathName() + department.getName() + "/");
		department.setIcon("icon-department");
		//先检查编码是否重复
		TDepartmentExample departmentExample = new TDepartmentExample();
		departmentExample.createCriteria().andCodeEqualTo(department.getCode());
		
		if (departmentMapper.countByExample(departmentExample) > 0)
		{
			messagMap.put("success", false);
			messagMap.put("message", "编码重复，请使用其它编码！");
			return messagMap;
		}
		if (departmentMapper.insertSelective(department) > 0)
		{
			department.setFullPathId(department.getFullPathId() + department.getId() + "/");
			departmentMapper.updateByPrimaryKeySelective(department);
			messagMap.put("success", true);
			messagMap.put("data", department);
		}
		else
		{
			messagMap.put("success", false);
			messagMap.put("message", "添加失败，请联系系统管理员！");
		}
		
		return messagMap;
	}
	/***
	 * 更新部门信息
	 */
	@Override
	public Object UpdateDepartment(TDepartment department) {
		// TODO Auto-generated method stub
		Map<Object, Object> messagMap = new HashMap<Object, Object>();
		//表单传进了 ParetnID 做为更新主键的 ID;
		department.setId(department.getParentId());
		//表单把原部门名称 传递到 fullPathID中，然后使用字符串替换，修改部门全路径。
		department.setFullPathName(department.getFullPathName().replace(department.getFullPathId(), department.getName()));
		//清空部门全路径，不做更新。
		department.setParentId(null);
		department.setIcon(null);
		department.setFullPathId(null);
		//先检查编码是否重复
		TDepartmentExample departmentExample = new TDepartmentExample();
		departmentExample.createCriteria().andCodeEqualTo(department.getCode()).andIdNotEqualTo(department.getId());
		if (departmentMapper.countByExample(departmentExample) > 0)
		{
			messagMap.put("success", false);
			messagMap.put("message", "编码重复，请使用其它编码！");
			return messagMap;
		}
		if (departmentMapper.updateByPrimaryKeySelective(department) > 0)
		{
			messagMap.put("success", true);
			messagMap.put("data", department);
		}
		else
		{
			messagMap.put("success", false);
			messagMap.put("message", "添加失败，请联系管理员！");
		}
		return messagMap;
	}
	/***
	 * 删除部门人员信息
	 */
	@Override
	public Object DeleteDepartment(long id) {
		// TODO Auto-generated method stub
		Map<Object, Object> messagMap = new HashMap<Object, Object>();
		TDepartmentExample departmentExample = new TDepartmentExample();
		departmentExample.createCriteria().andParentIdEqualTo(id);
		if (id <= 0)
		{
			messagMap.put("success", false);
			messagMap.put("message", "不允许删除根节点！");
			return messagMap;
		}
		if (departmentMapper.countByExample(departmentExample) > 0)
		{
			messagMap.put("success", false);
			messagMap.put("message", "当前部门存在子部门节点，请逐个删除！");
			return messagMap;
		}
		try{
			if (departmentMapper.deleteByPrimaryKey(id) > 0)
				messagMap.put("success", true);
			else
			{
				messagMap.put("success", false);
				messagMap.put("message", "删除失败，请联系管理员！");
			}
		}catch(Exception e){
			e.printStackTrace();
			messagMap.put("success", false);
			messagMap.put("message", "该角色已经被使用，删除失败，请联系管理员！");
		}
		return messagMap;
	}
	@Override
	public Object GetUsers(long departmentID, long page, long rows) {
		// TODO Auto-generated method stub
		long Total = 0;
		Map<String, Object> userMap = new HashMap<String, Object>();
		TUsersExample usersExample = new TUsersExample();
		usersExample.createCriteria().andDepartmentIdEqualTo(departmentID);
		usersExample.setOrderByClause("ID");
		Total = userMapper.countByExample(usersExample);
		//创建一个分页类 并设置页数及每页数量个数。
		usersExample.setPage(page, rows);
		List<TUsers> userData = userMapper.selectByExample(usersExample);
		userMap.put("total", Total);
		userMap.put("rows", userData);
		return userMap;
	}
	@Override
	public Object SaveUser(TUsers users) {
		// TODO Auto-generated method stub
		Map<Object, Object> messagMap = new HashMap<Object, Object>();
		users.setActiveState(Short.valueOf("0"));
		try
		{
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			//将用户密码转成MD5加密字符串
			users.setUserPassword(md5.encodePassword(users.getUserPassword(), users.getUserId()));
			users.setHisoryPassword(users.getUserPassword());
			TUsersExample usersExample = new TUsersExample();
			usersExample.createCriteria().andUserIdEqualTo(users.getUserId());
			if (userMapper.countByExample(usersExample) > 0)
			{
				messagMap.put("success", false);
				messagMap.put("message", "用户编码重复！");
				return messagMap;
			}
			
			if (userMapper.insertSelective(users) > 0)
			{
				messagMap.put("success", true);
				messagMap.put("data", users);
			}
		}
		catch(Exception error)
		{
			messagMap.put("success", false);
			messagMap.put("message", "添加新用户失败，请联系管理员！错误信息为" + error.getMessage());
		}
		return messagMap;
	}
	@Override
	public Object UpdateUser(TUsers users) {
		// TODO Auto-generated method stub
		//表单以部门ID传入的 用户主键ID 所以将ID设置成 departmentid
		users.setId(users.getDepartmentId());
		users.setName(null);
		users.setUserId(null);
		users.setUserPassword(null);
		users.setDepartmentId(null);
		users.setActiveState(null);
		Map<Object, Object> messagMap = new HashMap<Object, Object>();
		try
		{
			if (userMapper.updateByPrimaryKeySelective(users) > 0)
			{
				messagMap.put("success", true);
				messagMap.put("data", users);
			}
		}
		catch(Exception error)
		{
			messagMap.put("success", false);
			messagMap.put("message", "更新用户信息失败！，请联系管理员！错误信息为" + error.getMessage());
		}
		return messagMap;
	}
	@Override
	public Object DeleteUser(long id) {
		// TODO Auto-generated method stub
		Map<Object, Object> messagMap = new HashMap<Object, Object>();
		try
		{
			if (userMapper.deleteByPrimaryKey(id) > 0)
				messagMap.put("success", true);
		}
		catch(Exception error)
		{
			messagMap.put("success", false);
			messagMap.put("message", "删除用户失败！，请联系管理员！错误信息为" + error.getMessage());
		}
		return messagMap;
	}
	@Override
	public Object ResetUserPassword(TUsers users) {
		// TODO Auto-generated method stub
		Map<Object, Object> messagMap = new HashMap<Object, Object>();
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		//将用户密码转成MD5加密字符串
		users.setUserPassword(md5.encodePassword(users.getUserPassword(), users.getUserId()));
		users.setHisoryPassword(users.getUserPassword());
		try
		{
			if (userMapper.updateByPrimaryKeySelective(users) > 0)
			{
				messagMap.put("success", true);
				messagMap.put("message", "帐户&lt;b style='color:red;'&gt;[" + users.getUserId() + "]&lt;/b&gt;&lt;br/&gt;密码重置成功，请妥善保管好新的密码！");
			}
		}
		catch(Exception error)
		{
			messagMap.put("success", false);
			messagMap.put("message", "重置用户密码失败，请联系管理员！错误信息为：" + error.getMessage());
		}
		return messagMap;
	}
	@Override
	public Object BackUserPassword(long id) {
		Map<Object, Object> messagMap = new HashMap<Object, Object>();
		try
		{
			TUsers user = userMapper.selectByPrimaryKey(id);
			if (user != null && user.getHisoryPassword() != null)
			{
				TUsers record = new TUsers();
				record.setUserPassword(user.getHisoryPassword());
				record.setId(id);
				if (userMapper.updateByPrimaryKeySelective(record) > 0)
				{
					messagMap.put("success", true);
					messagMap.put("message", "帐号&lt;b style='color:red;'&gt;[" + user.getUserId() + "]&lt;/b&gt;已经重置为最后一次使用的密码!");
				}
			}
		}
		catch(Exception error)
		{
			messagMap.put("success", false);
			messagMap.put("message", "重置用户密码失败，请联系管理员！错误信息为：" + error.getMessage());
		}
		return messagMap;
	}
	@Override
	public Object LockUser(long id, String userId) {
		// TODO Auto-generated method stub
		Map<Object, Object> messagMap = new HashMap<Object, Object>();
		try
		{
			TUsers record = new TUsers();
			record.setId(id);
			record.setActiveState(Short.valueOf("1"));
			if (userMapper.updateByPrimaryKeySelective(record) > 0)
			{
				messagMap.put("success", true);
				messagMap.put("message", "帐号&lt;b style='color:red;'&gt;[" + userId + "]&lt;/b&gt;锁定成功，该用户将不允许登陆本系统！");
				//messagMap.put("message", "锁定成功，该用户将不允许登陆本系统！");
			}
		}
		catch(Exception error)
		{
			messagMap.put("success", true);
			messagMap.put("message", "帐号&lt;b style='color:red;'&gt;[" + userId + "]&lt;/b&gt;锁定失败，请联系管理员！错误代码为：" + error.getMessage());
		}
		return messagMap;
	}
	@Override
	public Object UnLockUser(long id, String userId) {
		// TODO Auto-generated method stub
		Map<Object, Object> messagMap = new HashMap<Object, Object>();
		try
		{
			TUsers record = new TUsers();
			record.setId(id);
			record.setActiveState(Short.valueOf("0"));
			if (userMapper.updateByPrimaryKeySelective(record) > 0)
			{
				messagMap.put("success", true);
				messagMap.put("message", "帐号&lt;b style='color:red;'&gt;[" + userId + "]&lt;/b&gt;解锁成功，该用户可正常登陆本系统！");
			}
		}
		catch(Exception error)
		{
			messagMap.put("success", true);
			messagMap.put("message", "帐号&lt;b style='color:red;'&gt;[" + userId + "]&lt;/b&gt;解锁失败，请联系管理员！错误代码为：" + error.getMessage());
		}
		return messagMap;
	}
	@Override
	public Object GetRoles(long id) {
		List<Map<String, Object>> dataMap = new ArrayList<Map<String,Object>>();
		List<TRole> roleList = new ArrayList<TRole>();
		// TODO Auto-generated method stub
		TRoleExample roleExample = new TRoleExample();
		roleExample.createCriteria().andParentIdEqualTo(id);
		
		roleList =roleMapper.selectByExample(roleExample); 
		for(TRole value : roleList)
		{
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("text", value.getName());
			temp.put("id", value.getId());
			temp.put("iconCls", value.getType() == 0 ? "icon-role" : "icon-group");
			roleExample.clear();
			roleExample.createCriteria().andParentIdEqualTo(value.getId());
			if (roleMapper.countByExample(roleExample) > 0)
				temp.put("state", "closed");
			Map<String, Object> attrib = new HashMap<String, Object>();
			attrib.put("code", value.getCode());
			attrib.put("type", value.getType());
			temp.put("attributes", attrib);
			dataMap.add(temp);
		}
		return dataMap;
	}
	@Override
	public Object SaveRole(TRole role) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>();
		try
		{
			if (roleMapper.insertSelective(role) > 0)
			{
				//插入成功
				data.put(Constant.SUCCESS, true);
				data.put(Constant.JSON_DATA, role);
			}
			else
			{
				//插入成功
				data.put(Constant.SUCCESS, false);
				data.put(Constant.MESSAGE, "添加失败！");
			}
		}
		catch(Exception error)
		{
			data.put(Constant.SUCCESS, false);
			data.put(Constant.MESSAGE, "添加失败！错误代码为：" + error.getMessage());
		}
		return data;
	}
	@Override
	public Object UpdateRole(TRole role) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>();
		role.setId(role.getParentId());
		role.setParentId(null);
		role.setType(null);
		try
		{
			if (roleMapper.updateByPrimaryKeySelective(role) > 0)
			{
				//插入成功
				data.put(Constant.SUCCESS, true);
				data.put(Constant.JSON_DATA, role);
			}
			else
			{
				//插入成功
				data.put(Constant.SUCCESS, false);
				data.put(Constant.MESSAGE, "更新失败！");
			}
		}
		catch(Exception error)
		{
			data.put(Constant.SUCCESS, false);
			data.put(Constant.MESSAGE, "更新失败！错误代码为：" + error.getMessage());
		}
		return data;
	}
	@Override
	public Object DeleteRole(long id) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>();
		//先检查是否有已经受权的用户。
		
		TUserRoleExample userRoleExample = new TUserRoleExample();
		userRoleExample.createCriteria().andRoleIdEqualTo(id);
		if (userRoleMapper.countByExample(userRoleExample) > 0)
		{
			
		}
		//检查是否有授权的功能模块。
		if (roleMapper.deleteByPrimaryKey(id) > 0)
			data.put(Constant.SUCCESS, true);
		else
		{
			data.put(Constant.SUCCESS, false);
			data.put(Constant.MESSAGE, "删除失败，请联系管理员！");
		}
		return null;
	}
}

