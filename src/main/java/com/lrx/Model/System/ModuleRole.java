package com.lrx.Model.System;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ModuleRole {
	private long id;
	private String name;
	private Date time;
	private String userName;
	private long roleId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonFormat(pattern="yyyy年MM月dd日HH时mm分", timezone = "Asia/Shanghai")
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
}
