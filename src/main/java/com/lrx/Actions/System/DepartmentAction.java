package com.lrx.Actions.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lrx.Model.System.TDepartment;
import com.lrx.Model.System.TUsers;
import com.lrx.Service.System.DepartmentService;

@Controller
public class DepartmentAction {
	@Autowired
	DepartmentService departmentService;

	@RequestMapping("/System/GetDepartments")
	@ResponseBody
	public Object GetDepartments(@RequestParam(defaultValue = "-1") long id) {
		// org.springframework.security.providers.encoding.Md5PasswordEncoder
		return departmentService.GetDepartments(id);
	}

	@RequestMapping(value = "/System/SaveDepartment", method = RequestMethod.POST)
	@ResponseBody
	public Object SaveDepartment(TDepartment department) {
		return departmentService.SaveDepartment(department);
	}

	@RequestMapping(value = "/System/UpdateDepartment", method = RequestMethod.POST)
	@ResponseBody
	public Object UpdateDepartment(TDepartment department) {
		return departmentService.UpdateDepartment(department);
	}

	@RequestMapping(value = "/System/DeleteDepartment", method = RequestMethod.POST)
	@ResponseBody
	public Object DeleteDepartment(long parentId) {
		return departmentService.DeleteDepartment(parentId);
	}

	@RequestMapping(value = "/System/GetUsers", method = RequestMethod.POST)
	@ResponseBody
	public Object GetUsers(long departmentID, int page, int rows) {
		return departmentService.GetUsers(departmentID, page, rows);
	}

	@RequestMapping(value = "/System/SaveUser", method = RequestMethod.POST)
	@ResponseBody
	public Object SaveUser(TUsers users) {
		return departmentService.SaveUser(users);
	}

	@RequestMapping(value = "/System/UpdateUser", method = RequestMethod.POST)
	@ResponseBody
	public Object UpdateUser(TUsers users) {
		return departmentService.UpdateUser(users);
	}

	@RequestMapping(value = "/System/DeleteUser", method = RequestMethod.POST)
	@ResponseBody
	public Object DeleteUser(long departmentId) {
		return departmentService.DeleteUser(departmentId);
	}

	@RequestMapping(value = "/System/ResetUserPassword", method = RequestMethod.POST)
	@ResponseBody
	public Object ResetUserPassword(TUsers users) {
		return departmentService.ResetUserPassword(users);
	}

	@RequestMapping(value = "/System/BackUserPassword", method = RequestMethod.POST)
	@ResponseBody
	public Object BackUserPassword(long id) {
		return departmentService.BackUserPassword(id);
	}

	@RequestMapping(value = "/System/LockUser", method = RequestMethod.POST)
	@ResponseBody
	public Object LockUser(long id, String userId) {
		return departmentService.LockUser(id, userId);
	}

	@RequestMapping(value = "/System/UnLockUser", method = RequestMethod.POST)
	@ResponseBody
	public Object UnLockUser(long id, String userId) {
		return departmentService.UnLockUser(id, userId);
	}
}
