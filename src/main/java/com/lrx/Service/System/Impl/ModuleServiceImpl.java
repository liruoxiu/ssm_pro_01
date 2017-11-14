package com.lrx.Service.System.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lrx.Common.Constant;
import com.lrx.Dao.System.SystemMapper;
import com.lrx.Dao.System.TModuleMapper;
import com.lrx.Dao.System.VUserRoleMapper;
import com.lrx.Model.System.TModule;
import com.lrx.Model.System.TModuleExample;
import com.lrx.Service.System.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {
	@Autowired(required=false)
	private TModuleMapper moduleMapper;
	@Autowired(required=false)
	private SystemMapper systemMapper;

	@Override
	public List<Map<String, Object>> getModule(long id) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> jsonData = new ArrayList<Map<String, Object>>();
		TModuleExample moduleExample = new TModuleExample();
		moduleExample.setOrderByClause("DISPLAY_INDEX");
		moduleExample.createCriteria().andParentIdEqualTo(id);

		List<TModule> moduleList = moduleMapper.selectByExample(
				moduleExample);
		for (TModule moduleInfo : moduleList) {
			Map<String, Object> moduleMap = new HashMap<String, Object>();
			moduleMap.put("id", moduleInfo.getId());
			moduleMap.put("text", moduleInfo.getName());
			moduleMap.put("iconCls", moduleInfo.getIcon());
			Map<String, Object> attrib = new HashMap<String, Object>();
			attrib.put("moduleUrl", moduleInfo.getModuleUrl());
			attrib.put("version", moduleInfo.getVersion());
			attrib.put("description", moduleInfo.getDescription());
			attrib.put("icon", moduleInfo.getIcon());
			attrib.put("releaseUserId", moduleInfo.getReleaseUserId());
			attrib.put("displayIndex", moduleInfo.getDisplayIndex());
			attrib.put("parentId", moduleInfo.getParentId());
			attrib.put("control",  moduleInfo.getAccessControl() == 0 ? false : true);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
			attrib.put("published", sdf.format(moduleInfo.getPublished()));
			attrib.put("type", moduleInfo.getType());
			moduleMap.put("attributes", attrib);
			moduleExample.clear();
			moduleExample.setOrderByClause("DISPLAY_INDEX");
			moduleExample.createCriteria().andParentIdEqualTo(
					moduleInfo.getId());
			jsonData.add(moduleMap);
			long recordCount = moduleMapper.countByExample(moduleExample);
			if (recordCount > 0) {
				List<Map<String, Object>> temp = getModule(moduleInfo.getId());
				moduleMap.put("children", temp);
			}
		}
		return jsonData;
	}

	@Override
	public Object saveModule(TModule module, String UserName) {
		// TODO Auto-generated method stub
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (module.getId() == null) {
			try {

				if (moduleMapper.insertSelective(module) > 0) {
					dataMap.put("success", true);
					dataMap.put("data", module);
				}
				return dataMap;
			} catch (Exception e) {
				e.printStackTrace();
				dataMap.put("success", false);
				dataMap.put("data", "添加失败！");
				return dataMap;
			}
		} else {
			try {
				if (moduleMapper.updateByPrimaryKey(module) > 0) {
					dataMap.put("success", true);
					dataMap.put("data", module);
				}
				return dataMap;
			} catch (Exception e) {
				e.printStackTrace();
				dataMap.put("success", false);
				dataMap.put("data", "更新失败！");
				return dataMap;
			}
		}
	}

	@Override
	public Object deleteModule(long id) {
		Map<String, Object> data = new HashMap<String, Object>();
		// 先检查是否有已经受权的用户。

		if (id <= 1) {
			data.put("success", false);
			data.put("message", "不允许删除根节点！");
			return data;
		}

		TModuleExample moduleExample = new TModuleExample();
		moduleExample.createCriteria().andParentIdEqualTo(id);
		if (moduleMapper.countByExample(moduleExample) > 0) {
			data.put("success", false);
			data.put("message", "当前模块存在子模块节点，请逐个删除！");
			return data;
		}

		// 检查是否有授权的功能模块。
		try {
			if (moduleMapper.deleteByPrimaryKey(id) > 0)
				data.put(Constant.SUCCESS, true);
			else {
				data.put(Constant.SUCCESS, false);
				data.put(Constant.MESSAGE, "删除失败，请联系管理员！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			data.put(Constant.SUCCESS, false);
			data.put(Constant.MESSAGE, "模块被关联使用,删除失败，请联系管理员！");
		}
		return data;
	}

	@Override
	public TModule getModuleById(long id) {
		List<TModule> moduleList = moduleMapper.getModuleById(id);
		if (moduleList != null && moduleList.size() > 0) {
			return moduleList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Map<String, Object> updateSystem(TModule module) {
		// TODO Auto-generated method stub
		Map<String, Object> message = new HashMap<String, Object>();
		module.setId(module.getParentId());
		module.setParentId(null);
		if (moduleMapper.updateByPrimaryKeySelective(module) > 0) {
			message.put(Constant.SUCCESS, true);
			message.put(Constant.JSON_DATA, module);
		} else {
			message.put(Constant.SUCCESS, false);
			message.put(Constant.MESSAGE, "更新失败，请联系管理员！");
		}
		return message;
	}

	@Override
	public Object getModuleRole(long id) {
		// TODO Auto-generated method stub
		return systemMapper.selectModuleRole(id);
	}

	@Override
	public Object getRoleUsers(long id) {
		// TODO Auto-generated method stub
		return systemMapper.selectRoleUser(id);
	}

	@Override
	@Transactional
	public Object updateAccessCotrol(long id) {
		TModule row = moduleMapper.selectByPrimaryKey(id);
		Map<String, Object> message = new HashMap<String, Object>();
		if (row.getAccessControl() == 0)
		{
			//未启用访问控制
			row.setAccessControl(1L);
			if (moduleMapper.updateByPrimaryKeySelective(row) > 0)
			{
				message.put(Constant.SUCCESS, true);
				//检查是否已经配置过访问权限
				if (this.systemMapper.selectModuleRole(id).size() <= 0)
					message.put(Constant.MESSAGE, "访问控制已经启用，该功能没有配置访问角色！");
				else
					message.put(Constant.MESSAGE, "访问控制已经启用！");
			}
			else
			{
				message.put(Constant.SUCCESS, false);
				message.put(Constant.MESSAGE, "访问控制启动失败，请联系管理员！");
			}
		}
		else
		{
			//启用了访问控制
			row.setAccessControl(0L);
			if (moduleMapper.updateByPrimaryKeySelective(row) > 0)
			{
				message.put(Constant.SUCCESS, true);
				//检查是否已经配置过访问权限
				message.put(Constant.MESSAGE, "访问控制已经停用");
			}
			else
			{
				message.put(Constant.SUCCESS, false);
				message.put(Constant.MESSAGE, "访问控制停用失败，请联系管理员！");
			}
		}
		return message;
	}
}
