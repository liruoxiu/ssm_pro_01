package com.lrx.Common;

public interface Constant {
	String SESSION_USERID = "userId";//
	String SESSION_USERNAME = "userName";
	/**
	 * 用户所在部门，主键ID
	 */
	String SESSION_DEPARTMENTID = "userDepId"; 
	String SESSION_USERPASSWORD = "userPassword";
	String SESSION_USER_ID="user_Id"; 
	String SESSION_THEME = "theme";
	String SESSION_LAYOUT = "layout";
	
	/***
	 * 验证码 Session id
	 */
	String SESSION_CHECKCODE = "randCheckCode";
	
	String SUCCESS = "success";
	String MESSAGE = "message";
	String JSON_DATA = "data";
	
	/***
	 * 验证码基本字符串
	 */
	//String CHECKCODE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String CHECKCODE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	/***
	 * 验证码随机数产生范围。
	 */
	int CHECKCODE_RANDOM_MAX = 24;
}
