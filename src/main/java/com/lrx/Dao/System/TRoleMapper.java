package com.lrx.Dao.System;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lrx.Model.System.TRole;
import com.lrx.Model.System.TRoleExample;
import com.lrx.Model.System.TUsers;

public interface TRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int countByExample(TRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int deleteByExample(TRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int insert(TRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int insertSelective(TRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    List<TRole> selectByExample(TRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int updateByExampleSelective(@Param("record") TRole record, @Param("example") TRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int updateByExample(@Param("record") TRole record, @Param("example") TRoleExample example);
    
    int updateByPrimaryKeySelective(TRole record);
    int deleteByPrimaryKey(Long id);
    public List<TRole> getRoleListByRoleIds(List<String> selectRoleIds);
    public List<TUsers> getUserListByRoleIds(List<String> selectRoleIds);
    TRole selectByPrimaryKey(Long id);
}