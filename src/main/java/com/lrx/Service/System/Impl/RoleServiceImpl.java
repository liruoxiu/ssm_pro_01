package com.lrx.Service.System.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lrx.Common.Constant;
import com.lrx.Dao.System.TModuleMapper;
import com.lrx.Dao.System.TRoleMapper;
import com.lrx.Dao.System.TUserRoleMapper;
import com.lrx.Dao.System.VDepartmentUserMapper;
import com.lrx.Dao.System.VUserRoleMapper;
import com.lrx.Model.System.TDepartMentRole;
import com.lrx.Model.System.TModule;
import com.lrx.Model.System.TModuleExample;
import com.lrx.Model.System.TRole;
import com.lrx.Model.System.TRoleExample;
import com.lrx.Model.System.TRoleModule;
import com.lrx.Model.System.TUserRole;
import com.lrx.Model.System.TUsers;
import com.lrx.Model.System.VDepartmentUser;
import com.lrx.Model.System.VDepartmentUserExample;
import com.lrx.Model.System.VUserRole;
import com.lrx.Model.System.VUserRoleExample;
import com.lrx.Model.System.VUserRoleExample.Criteria;
import com.lrx.Service.System.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired(required = false)
	private TRoleMapper roleMapper;
	@Autowired(required = false)
	private VUserRoleMapper vuserRoleMapper;
	@Autowired(required = false)
	private TUserRoleMapper userRoleMapper;
	@Autowired(required = false)
	private TModuleMapper moduleMapper;
	@Autowired(required = false)
	private VDepartmentUserMapper vdepartUserMapper;

	@Override
	public Object GetRoles(long id) {
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		List<TRole> roleList = new ArrayList<TRole>();
		// TODO Auto-generated method stub
		TRoleExample roleExample = new TRoleExample();
		roleExample.createCriteria().andParentIdEqualTo(id);

		roleList = roleMapper.selectByExample(roleExample);
		for (TRole value : roleList) {
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("text", value.getName());
			temp.put("id", value.getId());
			temp.put("iconCls", value.getType() == 0 ? "icon-role"
					: "icon-group");
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
		try {
			if (roleMapper.insertSelective(role) > 0) {
				// 插入成功
				data.put(Constant.SUCCESS, true);
				data.put(Constant.JSON_DATA, role);
			} else {
				// 插入成功
				data.put(Constant.SUCCESS, false);
				data.put(Constant.MESSAGE, "添加失败！");
			}
		} catch (Exception error) {
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
		try {
			if (roleMapper.updateByPrimaryKeySelective(role) > 0) {
				// 插入成功
				data.put(Constant.SUCCESS, true);
				data.put(Constant.JSON_DATA, role);
			} else {
				// 插入成功
				data.put(Constant.SUCCESS, false);
				data.put(Constant.MESSAGE, "更新失败！");
			}
		} catch (Exception error) {
			data.put(Constant.SUCCESS, false);
			data.put(Constant.MESSAGE, "更新失败！错误代码为：" + error.getMessage());
		}
		return data;
	}

	@Override
	public Object DeleteRole(long id) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>();
		// 先检查是否有已经受权的用户。

		if (id <= 0) {
			data.put("success", false);
			data.put("message", "不允许删除根节点！");
			return data;
		}
		TRoleExample roleExample = new TRoleExample();
		roleExample.createCriteria().andParentIdEqualTo(id);
		if (roleMapper.countByExample(roleExample) > 0) {
			data.put("success", false);
			data.put("message", "当前角色存在子角色节点，请逐个删除！");
			return data;
		}

		// 检查是否有授权的功能模块。
		try {
			if (roleMapper.deleteByPrimaryKey(id) > 0)
				data.put(Constant.SUCCESS, true);
			else {
				data.put(Constant.SUCCESS, false);
				data.put(Constant.MESSAGE, "删除失败，请联系管理员！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			data.put(Constant.SUCCESS, false);
			data.put(Constant.MESSAGE, "角色被其他用户使用,删除失败，请联系管理员！");
		}
		return data;
	}

	@Override
	public Object DeleteUserRole(long id) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>();
		// 检查是否有授权的功能模块。
		if (userRoleMapper.deleteByPrimaryKey(id) > 0)
			data.put(Constant.SUCCESS, true);
		else {
			data.put(Constant.SUCCESS, false);
			data.put(Constant.MESSAGE, "删除失败，请联系管理员！");
		}
		return null;
	}

	@Override
	public Object GetUserRole(long roleID, String queryKey, String queryCond,
			int page, int rows) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>();
		VUserRoleExample userRoleExample = new VUserRoleExample();
		Criteria linkWhere = userRoleExample.createCriteria();
		linkWhere.andRoleIdEqualTo(roleID);
		if (queryCond != null && !"".equals(queryCond)) {
			if (queryKey != null) {
				if ("name".equals(queryKey)) {
					linkWhere.andUserNameLike(queryCond);
				} else if ("code".equals(queryKey)) {
					linkWhere
							.andUserIdLike(queryCond);
				}
			}
		}
		List<VUserRole> list = vuserRoleMapper.selectByExample(userRoleExample);

		data.put("total", list.size());
		data.put("rows", list);
		return list;
	}

	@Override
	public Object GetDepartUser(long id, long roleId) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		VDepartmentUserExample vdepartUserExample = new VDepartmentUserExample();
		vdepartUserExample.createCriteria().andParentIdEqualTo(id);
		TDepartMentRole cond = new TDepartMentRole();
		cond.setDepartId(id);
		cond.setRoleId(roleId);
		List<VDepartmentUser> treeInfo = vdepartUserMapper
				.getDepartMentUser(cond);
		for (VDepartmentUser val : treeInfo) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", val.getId());
			data.put("text", val.getName());
			data.put("iconCls", val.getIcon());
			// vdepartUserExample.clear();
			// vdepartUserExample.createCriteria().andParentIdEqualTo(
			// (long) val.getId());
			if (val.getId() > -1) {
				data.put("state", "closed");
				dataMap.add(data);
			} else {// 如果ID小于-1 表示为 用户节点，
				if (val.getParentId().equals(id)) {
					Map<String, Object> attrib = new HashMap<String, Object>();
					attrib.put("id", val.getUserId());
					attrib.put("name", val.getName());
					attrib.put("code", val.getUserId());
					attrib.put("userid", val.getUserId());
					attrib.put("usercode", val.getUserCode());
					data.put("attributes", attrib);
					dataMap.add(data);
				}
			}

		}
		return dataMap;
	}

	@Override
	public Object SaveDepartUser(long[] selectUserID, long RoleID,
			HttpSession session) {
		// TODO Auto-generated method stub
		Map<String, Object> message = new HashMap<String, Object>();
		TUserRole value = new TUserRole();
		for (long val : selectUserID) {
			try {
				value.setRoleId(RoleID);
				value.setTime(new Date());
				value.setUserId(val);
				value.setUserName(session.getAttribute(
						Constant.SESSION_USERNAME).toString());
				userRoleMapper.insertSelective(value);
			} catch (Exception error) {

			}
		}
		return message;
	}

	// add by gaishm 20140709 begin

	/**
	 * 取得全部模块树
	 * 
	 * @return 全部模块树
	 */
	public List<Map<String, Object>> getAllModuleTree() {
		List<Map<String, Object>> treeList = GetAllModule(1L);
		return treeList;
	}

	private List<Map<String, Object>> GetAllModule(long id) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> jsonData = new ArrayList<Map<String, Object>>();
		TModuleExample moduleExample = new TModuleExample();
		moduleExample.setOrderByClause("DISPLAY_INDEX");
		moduleExample.createCriteria().andParentIdEqualTo(id);

		List<TModule> moduleList = moduleMapper.selectByExample(moduleExample);
		for (TModule val : moduleList) {
			Map<String, Object> moduleMap = new HashMap<String, Object>();
			moduleMap.put("id", val.getId());
			moduleMap.put("text", val.getName());
			moduleMap.put("moduleUrl", val.getModuleUrl());
			moduleMap.put("workflowKey", val.getWorkflowKey());
			moduleMap.put("version", val.getVersion());
			moduleExample.clear();
			moduleExample.createCriteria().andParentIdEqualTo(val.getId());
			jsonData.add(moduleMap);
			long recordCount = moduleMapper.countByExample(moduleExample);
			if (recordCount > 0) {
				List<Map<String, Object>> temp = GetAllModule(val.getId());
				moduleMap.put("children", temp);
			}
		}
		return jsonData;
	}

	// add by gaishm 20140709 end

	/***
	 * 取得模块树
	 */
	@Override
	public List<Map<String, Object>> getModuleTree(long roleId) {
		//取得该角色下的模块ID
		TRoleModule cond = new TRoleModule();
		cond.setRoleId(roleId);
		List<TRoleModule> moduleExistList = userRoleMapper.getRoleModuleByRoleId(cond);
		Long p = 1L;
		List<Map<String, Object>> treeList = GetModule(p, moduleExistList);
		return treeList;
	}

	private List<Map<String, Object>> GetModule(long id,
			List<TRoleModule> moduleExistList) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> jsonData = new ArrayList<Map<String, Object>>();
		TModuleExample moduleExample = new TModuleExample();
		moduleExample.setOrderByClause("DISPLAY_INDEX");
		moduleExample.createCriteria().andParentIdEqualTo(id);

		List<TModule> moduleList = moduleMapper.selectByExample(moduleExample);
		for (TModule val : moduleList) {
			Map<String, Object> moduleMap = new HashMap<String, Object>();
			moduleMap.put("id", val.getId());
			moduleMap.put("text", val.getName());
			moduleMap.put("moduleUrl", val.getModuleUrl());
			moduleMap.put("version", val.getVersion());
			if (moduleExistList != null && moduleExistList.size() > 0) {
				for (int i = 0; i < moduleExistList.size(); i++) {
					if (moduleExistList.get(i).getModuleId() == val.getId()) {
						if ("1".equals(moduleExistList.get(i).getSelectFlag().toString())) {
							moduleMap.put("checked", true);
						}
						break;
					}
				}
			}
			moduleExample.clear();
			moduleExample.createCriteria().andParentIdEqualTo(val.getId());
			jsonData.add(moduleMap);
			long recordCount = moduleMapper.countByExample(moduleExample);
			if (recordCount > 0) {
				List<Map<String, Object>> temp = GetModule(val.getId(),
						moduleExistList);
				moduleMap.put("children", temp);
			}
		}
		return jsonData;
	}

	/**
	 * 保存选择的角色模块
	 * 
	 * @param selectModuleID
	 *            选择的的模块ID
	 * @param RoleID
	 *            角色Id
	 * @param session
	 * @return
	 */
	@Override
	public Object saveRoleModule(String[] selectModuleID, long roleID,
			HttpSession session) {
		Map<String, Object> message = new HashMap<String, Object>();
		TRoleModule value = new TRoleModule();

		// 删除该角色对应的权限
		userRoleMapper.deleteRoleModuleByRoleId(roleID);

		for (String val : selectModuleID) {
			try {
				String[] valDim = val.split(",");
				value.setRoleId(roleID);
				value.setTime(new Date());
				value.setModuleId(Long.parseLong(valDim[0]));
				value.setSelectFlag(Long.parseLong(valDim[1]));
				value.setUserName(session.getAttribute(
						Constant.SESSION_USERNAME).toString());
				userRoleMapper.insertRoleModule(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return message;
		// return null;
	}

	/**
	 * 根据角色Id删除模块关系表
	 * 
	 * @param roleID
	 *            角色Id
	 * @param session
	 *            session
	 * @return
	 */
	public Object deleteRoleModule(long roleID, HttpSession session) {
		Map<String, Object> message = new HashMap<String, Object>();
		// 删除该角色对应的权限
		userRoleMapper.deleteRoleModuleByRoleId(roleID);
		return message;
	}

	/***
	 * 取得角色模块对应关系表
	 * 
	 * @param roleId
	 *            角色Id
	 */
	@Override
	public Object getRoleModuleList(long roleId) {
		return null;
	}

	/**
	 * 根据角色Id得到该角色的模块信息
	 * 
	 * @param roleId
	 *            角色Id
	 * @param key
	 *            模块检索方式
	 * @param search
	 *            检索内容
	 * @param page
	 *            页码
	 * @param rows
	 *            每页行数
	 * @return 模块信息列表
	 */
	public Object getRoleModule(long roleId, String queryKey, String queryCond,
			int page, int rows) {

		Map<String, Object> data = new HashMap<String, Object>();
		TRoleModule cond = new TRoleModule();
		cond.setRoleId(roleId);
		if (queryCond != null && !"".equals(queryCond)) {
			if (queryKey != null) {
				if ("name".equals(queryKey)) {
					cond.setModuleName(queryCond);
				} else if ("desc".equals(queryKey)) {
					cond.setDescription(queryCond);
				}
			}
		}
		List<TRoleModule> list = userRoleMapper.getRoleModuleByRoleId(cond);

		data.put("total", list.size());
		data.put("rows", list);
		return list;

	}

	/**
	 * 根据画面上选择的角色Ids
	 * 
	 * @param selectRoleIds
	 *            选择的用户Id
	 * @return 角色列表
	 */
	public Object getRoleListByRoleIds(String selectRoleIds) {
		List<String> roleL = new ArrayList<String>();
		String[] dimStr = selectRoleIds.split(",");
		if (dimStr.length>0){
			for (int i = 0; i < dimStr.length; i++) {
				if (!dimStr[i].equals("")) {
					roleL.add(dimStr[i]);
				}
			}
		}else{
			//给个没有用过的值
			roleL.add("-12345");
		}
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		List<TRole> roleList = roleMapper.getRoleListByRoleIds(roleL);
		for (TRole role : roleList) {
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("text", role.getName());
			temp.put("id", role.getId());
			temp.put("iconCls", "icon-role");
			dataMap.add(temp);
		}
		return dataMap;
	}

	/**
	 * 根据画面上选择的角色Id
	 * 
	 * @param selectRoleIds
	 *            选择的角色Id
	 * @return 用户列表
	 */
	public Object getUserListByRoleIds(String selectRoleIds) {
		List<String> roleL = new ArrayList<String>();
		String[] dimStr = selectRoleIds.split(",");
		if (dimStr.length>1){
			for (int i = 0; i < dimStr.length; i++) {
				if (!dimStr[i].equals("")) {
					roleL.add(dimStr[i]);
				}
			}
		}else{
			//给个没有用过的值
			roleL.add("-12345");
		}
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		List<TUsers> roleList = roleMapper.getUserListByRoleIds(roleL);
		for (TUsers user : roleList) {
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("text", user.getName());
			temp.put("id", user.getId());
			temp.put("iconCls", "icon-role");
			Map<String, Object> attrib = new HashMap<String, Object>();
			attrib.put("userId", user.getUserId());
			attrib.put("departmentId", user.getDepartmentId());
			temp.put("attributes", attrib);
			
			dataMap.add(temp);
		}
		return dataMap;
	}
	
	/**
	 * 返回全部角色和已经选择的角色
	 * @param selectRoleIds 选择的角色Ids
	 * @return 
	 */
	public List<Map<String, Object>> getAllRoleList(String selectRoleIds){
		String[] selDim=selectRoleIds.split(",");
		Long p = -1L;
		List<Map<String, Object>> treeList = GetAllAndSelRole(p, selDim);

		return treeList;
	}
	
	private List<Map<String, Object>> GetAllAndSelRole(long id,
			String[] selDim) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> jsonData = new ArrayList<Map<String, Object>>();
		TRoleExample roleExample = new TRoleExample();
		roleExample.setOrderByClause("DISPLAY_INDEX");
		
		
		List<Long> ids = new ArrayList<Long>(); 
		if (selDim != null && selDim.length > 0) {
			for (String val : selDim) {
				if (val.length() > 0)
					ids.add(Long.valueOf(val));
			}
		}
		TRoleExample.Criteria c = roleExample.createCriteria().andParentIdEqualTo(id);
		if (ids.size() > 0)
			c.andIdNotIn(ids);
		List<TRole> roleList = roleMapper.selectByExample(roleExample);
		for (TRole val : roleList) {
			Map<String, Object> roleMap = new HashMap<String, Object>();
			roleMap.put("id", val.getId());
			roleMap.put("text", val.getName());
			roleMap.put("description", val.getDescription());
			roleMap.put("type", val.getType());
			roleMap.put("iconCls", val.getType() == 0 ? "icon-role"
					: "icon-group");
			
			roleExample.clear();
			roleExample.createCriteria().andParentIdEqualTo(val.getId());
			jsonData.add(roleMap);
			long recordCount = roleMapper.countByExample(roleExample);
			if (recordCount > 0) {
				List<Map<String, Object>> temp = GetAllAndSelRole(val.getId(),
						selDim);
				roleMap.put("children", temp);
			}
		}
		return jsonData;
	}

	@Override
	public Object getUserByRoleIds(String selectRoleIds) {
		List<String> roleL = new ArrayList<String>();
		String[] dimStr = selectRoleIds.split(",");
		if (dimStr.length>1){
			for (int i = 0; i < dimStr.length; i++) {
				if (!dimStr[i].equals("")) {
					roleL.add(dimStr[i]);
				}
			}
		}else{
			//给个没有用过的值
			roleL.add("-12345");
		}
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		List<TUsers> roleList = roleMapper.getUserListByRoleIds(roleL);
		for (TUsers user : roleList) {
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("text", user.getName()+"/"+user.getUserId());
			temp.put("id", user.getId());
			temp.put("iconCls", "icon-role");
			Map<String, Object> attrib = new HashMap<String, Object>();
			attrib.put("userId", user.getUserId());
			attrib.put("departmentId", user.getDepartmentId());
			temp.put("attributes", attrib);
			
			dataMap.add(temp);
		}
		return dataMap;
	}

	@Override
	public Object getRoleFlowByRoleIds(String selectRoleIds) {
		List<String> roleL = new ArrayList<String>();
		String[] dimStr = selectRoleIds.split(",");
		String lrole ="";
		Boolean lroletag = true;
		Long roleid;
		if (dimStr.length>0){
			for (int i = 0; i < dimStr.length; i++) {
				if (!dimStr[i].equals("")) {
					roleL.add(dimStr[i]);
				}
			}
		}else{
			//给个没有用过的值
			roleL.add("-12345");
		}
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		List<TRole> roleList = roleMapper.getRoleListByRoleIds(roleL);
		for (TRole role : roleList) {
			Map<String, Object> temp = new HashMap<String, Object>();
			lroletag = true;
			lrole = role.getName();	
			roleid = role.getParentId();
			while (lroletag){
				if (roleid != 0){
					TRole parentRole = roleMapper.selectByPrimaryKey(roleid);
					roleid = parentRole.getParentId();
					lrole = parentRole.getName()+"/"+lrole;
				}else {
					lroletag = false;
				}			  	
			}
			temp.put("text", lrole);
			temp.put("id", role.getId());
			temp.put("iconCls", "icon-role");
			dataMap.add(temp);
		}
		return dataMap;
	}
}
