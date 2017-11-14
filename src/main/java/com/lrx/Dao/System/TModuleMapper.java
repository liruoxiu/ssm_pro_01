package com.lrx.Dao.System;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.lrx.Model.System.TModule;
import com.lrx.Model.System.TModuleExample;

public interface TModuleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int countByExample(TModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int deleteByExample(TModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int insert(TModule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int insertSelective(TModule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    List<TModule> selectByExample(TModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int updateByExampleSelective(@Param("record") TModule record, @Param("example") TModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    int updateByExample(@Param("record") TModule record, @Param("example") TModuleExample example);
    
    int updateByPrimaryKeySelective(TModule record);
    int updateByPrimaryKey(TModule record);
    int deleteByPrimaryKey(Long id);
    /**
     * 
     * @param id 模块ID
     * @return 模块及父节点名字等信息
     */
    public List<TModule> getModuleById(long id);
    TModule selectByPrimaryKey(Long id);
}