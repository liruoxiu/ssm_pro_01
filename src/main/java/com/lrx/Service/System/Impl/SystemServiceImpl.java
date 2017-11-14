package com.lrx.Service.System.Impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lrx.Common.Captcha;
import com.lrx.Common.Constant;
import com.lrx.Dao.System.SystemMapper;
import com.lrx.Dao.System.TModuleMapper;
import com.lrx.Dao.System.TUserRoleMapper;
import com.lrx.Dao.System.TUsersMapper;
import com.lrx.Model.System.ModuleRole;
import com.lrx.Model.System.TModule;
import com.lrx.Model.System.TModuleExample;
import com.lrx.Model.System.TUsers;
import com.lrx.Model.System.TUsersExample;
import com.lrx.Service.System.SystemService;

@Service
public class SystemServiceImpl implements SystemService {
	@Autowired(required = false)
	private TModuleMapper moduleMapper;
	@Autowired(required = false)
	private TUsersMapper usersMapper;
	@Autowired(required = false)
	private TUserRoleMapper userRoleMapper;
	@Autowired(required=false)
	private SystemMapper systemMapper;

	/***
	 * 取得系统模块菜单列表
	 */
	@Override
	public Map<String, Object> systemInitialization(String userId) {
		final TModuleExample ModuleExample;
		final Map<String, Object> rootMap;
		List<Map<String, Object>> childMap;
		// 从模块树中取出 根节点 Where parent_id = -1;
		ModuleExample = new TModuleExample();
		rootMap = new HashMap<String, Object>();
		ModuleExample.createCriteria().andParentIdEqualTo(-1L);
		List<TModule> moduleList = moduleMapper.selectByExample(ModuleExample);
		for (TModule module : moduleList) {
			// 生成系统标题
			rootMap.put("title", module.getName());
			childMap = getSysteMenu(module.getId(), userId);
			// 生成系统菜单，当Module表中有系统菜单数据时否侧生成空null
			rootMap.put("menu", childMap.size() > 0 ? childMap : null);
		}
		return rootMap;
	}
	private List<Map<String, Object>> getSysteMenu(long id, String userId) {
		final List<TModule> moduleList;
		Map<String, Object> MenuItem;
		final List<Map<String, Object>> childMap;
		childMap = new ArrayList<Map<String, Object>>();
		moduleList = this.systemMapper.selectSysteMenu(id, "1");
		//取得对应用户授权的模块ids
		List<ModuleRole> roleList = systemMapper.selectRoleModuleIDS(userId);
		for (TModule module : moduleList) {
			MenuItem = new HashMap<String, Object>();
			MenuItem.put("name", module.getName());
			MenuItem.put("id", module.getId());
			MenuItem.put("url", module.getModuleUrl());
			MenuItem.put("icon", module.getIcon());
			MenuItem.put("description", module.getDescription());
			//生成子菜单
			List<Map<String, Object>> subMenu = this.getSubMenu(module.getId(), userId);
			if (subMenu.size() > 0) {
				MenuItem.put("subMenu", subMenu);
			}
			if (module.getAccessControl() == 1) {
				for (ModuleRole val : roleList)
				{
					
					if (val.getId() == module.getId())
					{						
						childMap.add(MenuItem);
						break;
					}
				}
			}
			else{
				childMap.add(MenuItem);
			}
		}
		return childMap;
	}

	@Override
	public void GetCaptcha(HttpSession session, HttpServletResponse response, String code) {
		Captcha captcha = new Captcha(80, 30);
		try {
			String CodeStr = "";
			CodeStr = getCaptchaCode(session);
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "No-cache");
			response.setDateHeader("Expires", 0);
			ImageIO.write(captcha.GetCaptcha(CodeStr), "png",
					response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String getCaptchaCode(HttpSession session)
	{
		String tempStr = "";
		Random ro = new Random();
		for (int i = 0; i < 4; i++) {
			int strI = ro.nextInt(Constant.CHECKCODE_RANDOM_MAX);
			tempStr += Constant.CHECKCODE_STRING.charAt(strI);
		}
		session.setAttribute(Constant.SESSION_CHECKCODE, tempStr);
		return tempStr;
	}
	@Override
	public Object CheckLogin(String userId, String password, String Captcha,
			HttpSession session) {
		Map<String, Object> message = new HashMap<String, Object>();
		/*String checkCode = session.getAttribute(Constant.SESSION_CHECKCODE)
				.toString();*/
		String pwdStr = "";
		String userIdStr = userId.trim().toUpperCase();
		System.out.println(userIdStr);
		TUsersExample userExample = new TUsersExample();
		userExample.createCriteria().andUserIdEqualTo(userIdStr);
		// TODO Auto-generated method stub
		//重新生成验证码
		
		/*if (!Captcha.equalsIgnoreCase(checkCode)) {
			getCaptchaCode(session);
			message.put(Constant.SUCCESS, false);
			message.put(Constant.MESSAGE, "请输入正确的验证码！");
			return message;
		}*/
		List<TUsers> userList = usersMapper.selectByExample(userExample);
		if (userList.size() > 0) {
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			pwdStr = md5.encodePassword(password, userIdStr);
			
			
			
			TUsers userRecord = userList.get(0);Boolean b = md5.isPasswordValid(userRecord.getUserPassword(), password, userIdStr);
			// 锁定用户判定
			if (!userRecord.getActiveState().equals(Short.parseShort("0"))) {
				getCaptchaCode(session);
				message.put(Constant.SUCCESS, false);
				message.put(Constant.MESSAGE, "用户被锁定！");
				return message;
			}

			if (userRecord.getUserId().equals(userIdStr)
					&& userRecord.getUserPassword().equals(pwdStr)) {
				message.put(Constant.SUCCESS, true);
				// 移除验证码
				session.removeAttribute(Constant.SESSION_CHECKCODE);
				session.setAttribute(Constant.SESSION_USERID, userIdStr);
				session.setAttribute(Constant.SESSION_USERNAME,userRecord.getName());
				session.setAttribute(Constant.SESSION_DEPARTMENTID, userRecord.getDepartmentId());
				session.setAttribute(Constant.SESSION_USER_ID, userRecord.getId());
				session.setAttribute(Constant.SESSION_THEME, userRecord.getTheme()==null?"ui-pepper-grinder":userRecord.getTheme());
				session.setAttribute(Constant.SESSION_LAYOUT, userRecord.getLayout()==null?"LEFT":userRecord.getLayout());
				return message;
			} else {
				getCaptchaCode(session);
				message.put(Constant.SUCCESS, false);
				message.put(Constant.MESSAGE, "用户密码不正确！");
				return message;
			}
		} else {
			getCaptchaCode(session);
			message.put(Constant.SUCCESS, false);
			message.put(Constant.MESSAGE, "用户帐户不存在！");
			return message;
		}
	}

	public Object modifyPwd(String oldPassword, String newPassword,
			String userId) {
		Map<String, Object> message = new HashMap<String, Object>();
		// 根据userId取得用户对象
		try {
			TUsersExample cond = new TUsersExample();
			cond.createCriteria().andUserIdEqualTo(userId);
			List<TUsers> userList = usersMapper.selectByExample(cond);
			if (userList == null || userList.size() == 0) {
				message.put(Constant.SUCCESS, false);
				message.put(Constant.MESSAGE, "用户帐户不存在！");
				return message;
			}
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			String userIdStr = userId.trim().toUpperCase();
			String pwdStr = md5.encodePassword(oldPassword, userIdStr);
			String newPwdStr = md5.encodePassword(newPassword, userIdStr);
			TUsers userRecord = userList.get(0);
			if (userRecord.getUserId().equals(userIdStr)
					&& userRecord.getUserPassword().equals(pwdStr)) {
				// 更新数据表
				Long id = userRecord.getId();
				TUsers tuser = new TUsers();
				tuser.setId(id);
				tuser.setUserPassword(newPwdStr);
				tuser.setHisoryPassword(pwdStr);
				usersMapper.updatePwd(tuser);
				message.put(Constant.SUCCESS, true);
				message.put(Constant.MESSAGE, "用户密码修改成功！");
				return message;
			} else {
				message.put(Constant.SUCCESS, false);
				message.put(Constant.MESSAGE, "用户旧密码不正确！");
				return message;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 密码比较
			message.put(Constant.SUCCESS, false);
			message.put(Constant.MESSAGE, "用户密码修改失败，请联系系统管理员！");
			return message;
		}
	}

	public Object lock(String newPassword, String userId) {
		Map<String, Object> message = new HashMap<String, Object>();
		// 根据userId取得用户对象
		try {
			TUsersExample cond = new TUsersExample();
			cond.createCriteria().andUserIdEqualTo(userId);
			List<TUsers> userList = usersMapper.selectByExample(cond);
			if (userList == null || userList.size() == 0) {
				message.put(Constant.SUCCESS, false);
				message.put(Constant.MESSAGE, "用户帐户不存在！");
				return message;
			}
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			String userIdStr = userId.trim().toUpperCase();
			String pwdStr = md5.encodePassword(newPassword, userIdStr);
			TUsers userRecord = userList.get(0);
			if (userRecord.getUserId().equals(userIdStr)
					&& userRecord.getUserPassword().equals(pwdStr)) {
				// 更新数据表
				message.put(Constant.SUCCESS, true);
				message.put(Constant.MESSAGE, "用户解锁成功！");
				return message;
			} else {
				message.put(Constant.SUCCESS, false);
				message.put(Constant.MESSAGE, "用户解锁失败！");
				return message;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 密码比较
			message.put(Constant.SUCCESS, false);
			message.put(Constant.MESSAGE, "用户解锁失败，请联系系统管理员！");
			return message;
		}
	}
	private List<Map<String, Object>> getSubMenu(long id, String userId) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> message = new ArrayList<Map<String, Object>>();
		List<TModule> rows = this.systemMapper.selectSysteMenu(id, null);
		List<ModuleRole> roleList = systemMapper.selectRoleModuleIDS(userId);
		for(TModule row : rows)
		{
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", row.getId());
			item.put("text", row.getName());
			item.put("iconCls", row.getIcon());
			Map<String, Object> attri = new HashMap<String, Object>();
			attri.put("url", row.getModuleUrl());
			attri.put("description", row.getDescription());
			item.put("attributes", attri);
			List<Map<String, Object>> data = getSubMenu(row.getId(), userId);
			if (data.size() > 0)
			{
				item.put("state", "closed");
				item.put("children", data);
			}
			if (row.getAccessControl() == 1)
			{
				for(ModuleRole val : roleList)
				{
					if (val.getId() == row.getId())
					{
						message.add(item);
						break;
					}
				}
			}
			else
				message.add(item);
		}
		return message;
	}
	@Override
	public Map<String, Object> saveUserOption(String theme, String layout, HttpSession session) {
		// TODO Auto-generated method stub
		Map<String, Object> message = new HashMap<String, Object>();
		String userID = session.getAttribute(Constant.SESSION_USERID).toString();
		TUsersExample example = new TUsersExample();
		example.createCriteria().andUserIdEqualTo(userID);
		TUsers record = new TUsers();
		record.setTheme(theme);
		record.setLayout(layout);
		if (usersMapper.updateByExampleSelective(record, example) > 0)
		{
			message.put(Constant.SUCCESS, true);
		}
		else
		{
			message.put(Constant.SUCCESS, true);
			message.put(Constant.MESSAGE, "更新失败！");
		}
		return message;
	}
	private final MultipartFile uploadFileBase(HttpServletRequest request, String fileDomName)
	{
		MultipartHttpServletRequest multiRequest = null;
		MultipartFile file = null;
		try
		{
			multiRequest = (MultipartHttpServletRequest) request;
			file = multiRequest.getFile(fileDomName);
		}catch(Exception e){}
        return file;
	}
	@Override
	public void uploadFile(HttpServletRequest request, String fileDomName,
			String targetFileName) throws Exception {
		// TODO Auto-generated method stub
		MultipartFile file = uploadFileBase(request, fileDomName); 
        // 获得文件名：   
        String filename = file.getOriginalFilename();  
        try {
        	File outFile = new File(targetFileName);
			file.transferTo(outFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
	}
	@Override
	public InputStream uploadFile(HttpServletRequest request, String fileDomName)
			throws Exception {
		// TODO Auto-generated method stub
		MultipartFile file = uploadFileBase(request, fileDomName); 
		InputStream is = null;
		 try {
			 is = file.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new Exception(e.getMessage());
			}
		return is;
	}
}
