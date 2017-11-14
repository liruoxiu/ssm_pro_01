package com.lrx.Dao.System;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lrx.Model.System.ModuleRole;
import com.lrx.Model.System.RoleUsers;
import com.lrx.Model.System.TModule;

public interface SystemMapper {
	List<ModuleRole> selectModuleRole(Long id);
	List<RoleUsers> selectRoleUser(Long id);
	List<TModule> selectSysteMenu(@Param("id")long id, @Param("type")String type);
	
	List<ModuleRole> selectRoleModuleIDS(String userId);
}
