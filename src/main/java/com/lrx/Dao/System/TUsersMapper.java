package com.lrx.Dao.System;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.lrx.Model.System.TUsers;
import com.lrx.Model.System.TUsersExample;

public interface TUsersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_USERS
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int countByExample(TUsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_USERS
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int deleteByExample(TUsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_USERS
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int insert(TUsers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_USERS
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int insertSelective(TUsers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_USERS
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    List<TUsers> selectByExample(TUsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_USERS
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int updateByExampleSelective(@Param("record") TUsers record, @Param("example") TUsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_USERS
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int updateByExample(@Param("record") TUsers record, @Param("example") TUsersExample example);
    
    int updateByPrimaryKeySelective(TUsers record);
    int deleteByPrimaryKey(Long id);
    TUsers selectByPrimaryKey(Long id);
    int updatePwd(TUsers record);
}