package com.lrx.Service.System;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lrx.Model.System.TModule;

public interface SystemService {
	/***
	 * 取得部门信息列表
	 * 
	 * @author 张鹏
	 */
	public Map<String, Object> systemInitialization(String UserID);
	
	public void GetCaptcha(HttpSession session, HttpServletResponse response, String code);

	public Object CheckLogin(String userId, String password, String Captcha,
			HttpSession session);

	/**
	 * 用户修改密码
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @param userId      用户Id
	 * @return 修改成功结果
	 */
	public Object modifyPwd(String oldPassword,String newPassword,String userId);
	/**
	 * 用户锁定
	 * @param newPassword 新密码
	 * @param userId 用户Id
	 * @return
	 */
	public Object lock(String newPassword,String userId);
	
	public Map<String, Object> saveUserOption(String theme, String layout, HttpSession session);
	/**
	 * 上传文件并保存到指定位置
	 * @param request
	 * @param fileDomName
	 * @param targetFileName
	 * @throws Exception
	 */
	public void uploadFile(HttpServletRequest request, String fileDomName, String targetFileName) throws Exception;
	/**
	 * 上传文件，返回inputStream流
	 * @param request
	 * @param fileDomName
	 * @return
	 * @throws Exception
	 */
	public InputStream uploadFile(HttpServletRequest request, String fileDomName) throws Exception;
}
