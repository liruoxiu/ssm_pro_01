package com.lrx.Model.System;

public class RoleUsers {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJHND_SYSTEM.T_USERS.ID
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJHND_SYSTEM.T_USERS.NAME
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJHND_SYSTEM.T_USERS.MOBILE_PHONE
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    private String mobilePhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJHND_SYSTEM.T_USERS.USER_ID
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJHND_SYSTEM.T_USERS.USER_PASSWORD
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    private String userPassword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJHND_SYSTEM.T_USERS.ADDRESS
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJHND_SYSTEM.T_USERS.DEPARTMENT_ID
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    private Long departmentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJHND_SYSTEM.T_USERS.HISORY_PASSWORD
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    private String hisoryPassword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJHND_SYSTEM.T_USERS.ACTIVE_STATE
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    private Short activeState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJHND_SYSTEM.T_USERS.POST
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    private String post;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJHND_SYSTEM.T_USERS.OFFICE_PHONE
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    private String officePhone;
    private String fullPathName;
    public String getFullPathName() {
		return fullPathName;
	}

	public void setFullPathName(String fullPathName) {
		this.fullPathName = fullPathName;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJHND_SYSTEM.T_USERS.SEX
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    private String sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJHND_SYSTEM.T_USERS.SIGN_IMAGE
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    private byte[] signImage;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJHND_SYSTEM.T_USERS.ID
     *
     * @return the value of BJHND_SYSTEM.T_USERS.ID
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJHND_SYSTEM.T_USERS.ID
     *
     * @param id the value for BJHND_SYSTEM.T_USERS.ID
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJHND_SYSTEM.T_USERS.NAME
     *
     * @return the value of BJHND_SYSTEM.T_USERS.NAME
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJHND_SYSTEM.T_USERS.NAME
     *
     * @param name the value for BJHND_SYSTEM.T_USERS.NAME
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJHND_SYSTEM.T_USERS.MOBILE_PHONE
     *
     * @return the value of BJHND_SYSTEM.T_USERS.MOBILE_PHONE
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJHND_SYSTEM.T_USERS.MOBILE_PHONE
     *
     * @param mobilePhone the value for BJHND_SYSTEM.T_USERS.MOBILE_PHONE
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJHND_SYSTEM.T_USERS.USER_ID
     *
     * @return the value of BJHND_SYSTEM.T_USERS.USER_ID
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJHND_SYSTEM.T_USERS.USER_ID
     *
     * @param userId the value for BJHND_SYSTEM.T_USERS.USER_ID
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJHND_SYSTEM.T_USERS.USER_PASSWORD
     *
     * @return the value of BJHND_SYSTEM.T_USERS.USER_PASSWORD
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJHND_SYSTEM.T_USERS.USER_PASSWORD
     *
     * @param userPassword the value for BJHND_SYSTEM.T_USERS.USER_PASSWORD
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJHND_SYSTEM.T_USERS.ADDRESS
     *
     * @return the value of BJHND_SYSTEM.T_USERS.ADDRESS
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJHND_SYSTEM.T_USERS.ADDRESS
     *
     * @param address the value for BJHND_SYSTEM.T_USERS.ADDRESS
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJHND_SYSTEM.T_USERS.DEPARTMENT_ID
     *
     * @return the value of BJHND_SYSTEM.T_USERS.DEPARTMENT_ID
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJHND_SYSTEM.T_USERS.DEPARTMENT_ID
     *
     * @param departmentId the value for BJHND_SYSTEM.T_USERS.DEPARTMENT_ID
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJHND_SYSTEM.T_USERS.HISORY_PASSWORD
     *
     * @return the value of BJHND_SYSTEM.T_USERS.HISORY_PASSWORD
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public String getHisoryPassword() {
        return hisoryPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJHND_SYSTEM.T_USERS.HISORY_PASSWORD
     *
     * @param hisoryPassword the value for BJHND_SYSTEM.T_USERS.HISORY_PASSWORD
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public void setHisoryPassword(String hisoryPassword) {
        this.hisoryPassword = hisoryPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJHND_SYSTEM.T_USERS.ACTIVE_STATE
     *
     * @return the value of BJHND_SYSTEM.T_USERS.ACTIVE_STATE
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public Short getActiveState() {
        return activeState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJHND_SYSTEM.T_USERS.ACTIVE_STATE
     *
     * @param activeState the value for BJHND_SYSTEM.T_USERS.ACTIVE_STATE
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public void setActiveState(Short activeState) {
        this.activeState = activeState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJHND_SYSTEM.T_USERS.POST
     *
     * @return the value of BJHND_SYSTEM.T_USERS.POST
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public String getPost() {
        return post;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJHND_SYSTEM.T_USERS.POST
     *
     * @param post the value for BJHND_SYSTEM.T_USERS.POST
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJHND_SYSTEM.T_USERS.OFFICE_PHONE
     *
     * @return the value of BJHND_SYSTEM.T_USERS.OFFICE_PHONE
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public String getOfficePhone() {
        return officePhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJHND_SYSTEM.T_USERS.OFFICE_PHONE
     *
     * @param officePhone the value for BJHND_SYSTEM.T_USERS.OFFICE_PHONE
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJHND_SYSTEM.T_USERS.SEX
     *
     * @return the value of BJHND_SYSTEM.T_USERS.SEX
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJHND_SYSTEM.T_USERS.SEX
     *
     * @param sex the value for BJHND_SYSTEM.T_USERS.SEX
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJHND_SYSTEM.T_USERS.SIGN_IMAGE
     *
     * @return the value of BJHND_SYSTEM.T_USERS.SIGN_IMAGE
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public byte[] getSignImage() {
        return signImage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJHND_SYSTEM.T_USERS.SIGN_IMAGE
     *
     * @param signImage the value for BJHND_SYSTEM.T_USERS.SIGN_IMAGE
     *
     * @mbggenerated Thu Mar 06 10:48:21 CST 2014
     */
    public void setSignImage(byte[] signImage) {
        this.signImage = signImage;
    }
}