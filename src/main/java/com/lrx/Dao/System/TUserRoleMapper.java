package com.lrx.Dao.System;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lrx.Model.System.TRoleModule;
import com.lrx.Model.System.TUserRole;
import com.lrx.Model.System.TUserRoleExample;

public interface TUserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_USER_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int countByExample(TUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_USER_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int deleteByExample(TUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_USER_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int insert(TUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_USER_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int insertSelective(TUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_USER_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    List<TUserRole> selectByExample(TUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_USER_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int updateByExampleSelective(@Param("record") TUserRole record, @Param("example") TUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_USER_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int updateByExample(@Param("record") TUserRole record, @Param("example") TUserRoleExample example);
    
	int deleteByPrimaryKey(Long id);
	/*
	 * 根据检索条件得到角色模块对应信息表
	 */
	List<TRoleModule> getRoleModuleByRoleId(TRoleModule cond);
	/*
	 * 根据角色ID删除模块对应关系
	 */
	int deleteRoleModuleByRoleId(long roleID);
	/*
	 * 插入角色模块信息
	 */
	int insertRoleModule(TRoleModule record);
}