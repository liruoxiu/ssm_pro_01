package com.lrx.Dao.System;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.lrx.Model.System.VUserRole;
import com.lrx.Model.System.VUserRoleExample;

public interface VUserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.V_USER_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int countByExample(VUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.V_USER_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int deleteByExample(VUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.V_USER_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int insert(VUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.V_USER_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int insertSelective(VUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.V_USER_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    List<VUserRole> selectByExample(VUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.V_USER_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int updateByExampleSelective(@Param("record") VUserRole record, @Param("example") VUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.V_USER_ROLE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int updateByExample(@Param("record") VUserRole record, @Param("example") VUserRoleExample example);
}