	bjhnd.department = {}; //定义department命名空间

		
	bjhnd.department.depart_Dialog_obj = $("#Department_Dialog_Depa");
	bjhnd.department.depart_Tree_obj = $("#Department_Tree_DepartList");
	bjhnd.department.depart_Form_obj = $('#Department_form_depart');
	bjhnd.department.user_DataGrid_obj = $("#Department_datagrid_users");
	bjhnd.department.user_Dialog_obj = $("#Department_Dialog_User");
	bjhnd.department.user_Form_obj = $("#Department_form_User");
	bjhnd.department.repwd_Dialog_obj = $("#Department_Dialog_ResetPwd");
	bjhnd.department.repwd_Form_obj = $("#Department_form_ResetPwd");
	//用户ID
	bjhnd.department.userID = undefined;
	//部门ID
	bjhnd.department.departmentID = undefined;
	
	bjhnd.department.userRowIndex = undefined;
	
	bjhnd.department.FormMode = ""; //表单处理模式，add,del,edit
	
	//部门树的点击事件。
	bjhnd.department.DepartmentTreeClick = function(node) {
		if (node.id != null || node.id != undefined)
			bjhnd.department.departmentID = node.id;
		bjhnd.department.user_DataGrid_obj.datagrid({
			url : 'System/GetUsers.action?departmentID=' + node.id
		});
		bjhnd.department.UpdateToolbarState();
	};
	bjhnd.department.DepartTreePopmenu = function(e, node) {
		e.preventDefault();
		$(this).tree('select', node.target);
		$('#Deprment_Popmenu_edit').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
	};
	//部门树左键菜单
	bjhnd.department.DepartPopmenu_Event = function(item) {
		var selectedNode = bjhnd.department.depart_Tree_obj.tree("getSelected");
		switch (item.name) {
		case "add": //添加部门
		{
			bjhnd.department.depart_Form_obj.form('clear');
			bjhnd.department.depart_Form_obj.form("load", {
				parentId : selectedNode.id,
				fullPathName : selectedNode.attributes.fullname,
				fullPathId : selectedNode.attributes.fullpath,
				mode : item.name
			});
			bjhnd.department.depart_Dialog_obj.dialog({
				iconCls : 'icon-department'
			});
			bjhnd.department.depart_Dialog_obj.dialog("open").dialog(
					"setTitle", "添加部门");
			break;
		}
		case "edit": //编辑部门
		{
			bjhnd.department.depart_Form_obj.form('clear');
			bjhnd.department.depart_Form_obj.form("load", {
				parentId : selectedNode.id,
				fullPathName : selectedNode.attributes.fullname,
				fullPathId : selectedNode.text,
				name : selectedNode.text,
				code : selectedNode.attributes.code,
				mode : item.name
			});
			bjhnd.department.depart_Dialog_obj.dialog({
				iconCls : 'icon-department'
			});
			bjhnd.department.depart_Dialog_obj.dialog("open").dialog(
					"setTitle", "编辑部门");
			break;
		}
		case "del"://删除部门
		{
			$.messager.confirm("确认",
					"你确定删除名称为<b style='color:red;font:bold;'>["
							+ selectedNode.text + "]</b>的部门吗？",
					function(result) {
						if (result) {
							bjhnd.department.depart_Form_obj.form('clear');
							bjhnd.department.depart_Form_obj.form("load", {
								name : selectedNode.text,
								code : selectedNode.attributes.code,
								parentId : selectedNode.id,
								mode : item.name
							});
							bjhnd.department.DepartmentFormPost();
						}
					});
			break;
		}
		}
	};
	//部门表单提交
	bjhnd.department.DepartmentFormPost = function() {
		var selectedNode = bjhnd.department.depart_Tree_obj.tree("getSelected");
		editMode = $("#Department_textbox_editmode").val();
		PostUrl = editMode == "add" ? "System/SaveDepartment.action"
				: editMode == "del" ? "System/DeleteDepartment.action"
						: "System/UpdateDepartment.action";
		bjhnd.department.depart_Form_obj
				.form(
						'submit',
						{
							url : PostUrl,
							onSubmit : function() {
								return $(this).form("validate");
							},
							success : function(result) {
								var resultJson = $.parseJSON(result);
								if (resultJson.success) {
									if (editMode == "add") {
										//如果当前节点关闭状态，侧展开节点，不做节点添加，因为展开节点会从数据库取数据
										//新增加的节点可以被加载出来。
										if (selectedNode.state == "closed")
										{
											bjhnd.department.depart_Tree_obj.tree("expand",selectedNode.target);
										    var tempNode = bjhnd.department.depart_Tree_obj.tree("find", resultJson.data.id);
										    bjhnd.department.depart_Tree_obj.tree('select', tempNode.target);
										}
										else
										//如当前节点是展开状态，则添加一个新的节点上去。
										{
											bjhnd.department.depart_Tree_obj.tree(
															"append",
															{
																parent : selectedNode.target,
																data : {
																	id : resultJson.data.id,
																	text : resultJson.data.name,
																	iconCls : resultJson.data.icon,
																	attributes : {
																		code : resultJson.data.code,
																		fullname : resultJson.data.fullPathName,
																		fullpath : resultJson.data.fullPathId
																	}
																}
															});
											var tempNode = bjhnd.department.depart_Tree_obj.tree("find", resultJson.data.id);
										    bjhnd.department.depart_Tree_obj.tree('select', tempNode.target);
										}
									} else if (editMode == "del") //删除
									{
										bjhnd.department.depart_Tree_obj.tree(
												"remove", selectedNode.target);
									} else //更新
									{
										bjhnd.department.depart_Tree_obj
												.tree(
														"update",
														{
															target : selectedNode.target,
															text : resultJson.data.name,
															attributes : {
																code : resultJson.data.code,
																fullname : resultJson.data.fullPathName,
																fullpath : selectedNode.attributes.fullpath
															}
														});
									}

									bjhnd.department.depart_Dialog_obj
											.dialog("close");
								} else {
									$.messager.alert("错误", resultJson.message,
											"error");
								}
							}
						});
	};
	//用户表单提交
	bjhnd.department.UserFormPost = function() {
		//var selectedNode = bjhnd.department.depart_Tree_obj.tree("getSelected");
		editMode = $("#Department_textbox_Usereditmode").val();
		PostUrl = editMode == "add" ? "System/SaveUser.action"
				: editMode == "del" ? "System/DeleteUser.action"
						: "System/UpdateUser.action";
		bjhnd.department.user_Form_obj
				.form(
						'submit',
						{
							url : PostUrl,
							onSubmit : function() {
								return $(this).form("validate");
							},
							success : function(result) {
								var resultJson = $.parseJSON(result);
								if (resultJson.success) {
										switch(editMode)
										{
										case "add":
											{
											//如果提交成功 则往 DataGrid中插入新的记录
												bjhnd.department.user_DataGrid_obj.datagrid("appendRow", resultJson.data);
												//添加完毕后，选中最后一行
												rowIndex = bjhnd.department.user_DataGrid_obj.datagrid("getRows").length -1;
												bjhnd.department.user_DataGrid_obj.datagrid("selectRow",rowIndex);
												bjhnd.department.user_Dialog_obj.dialog("close");
												break;
											}
										case "edit":
											{
												//如果修改成功，则更新界面数据
												bjhnd.department.user_DataGrid_obj.datagrid("updateRow", 
														{index:bjhnd.department.userRowIndex,row:
														{
															sex:resultJson.data.sex, post:resultJson.data.post,
															mobilePhone:resultJson.data.mobilePhone, officePhone:resultJson.data.officePhone,
															address:resultJson.data.address
														}});
												bjhnd.department.user_Dialog_obj.dialog("close");
												break;
											}
										case "del":
											{
												bjhnd.department.user_DataGrid_obj.datagrid("deleteRow",bjhnd.department.userRowIndex); 
												break;
											}
										}
									}
								else
								{
									$.messager.alert("误错", resultJson.message, "error");	
								}
							}
						});
	};
	//重置密码表单提交
	bjhnd.department.ResetPwdFormPost = function() {
		mode = $("#Department_TextBox_resPwdMode").val();
		PostUrl = mode == "resetpwd" ? "System/ResetUserPassword.action" : 
				  mode == "lock" ? "System/LockUser.action" : 
				  mode == "unlock" ? "System/UnLockUser.action" :"System/BackUserPassword.action";
		bjhnd.department.repwd_Form_obj
				.form(
						'submit',
						{
							url : PostUrl,
							dataType:'json',
							onSubmit : function() {
								return $(this).form("validate");
							},
							success : function(result) {
							    var resultJson = $.parseJSON(result);
								if (resultJson.success) {
									str = resultJson.message;
									str = str.replaceAll("&lt;","<");
									str = str.replaceAll("&gt;",">");
									$.messager.alert("成功", str, "info");
									switch(mode)
									{
										case "lock":
											{
												bjhnd.department.user_DataGrid_obj.datagrid("updateRow", 
												{index:bjhnd.department.userRowIndex,row:
												{
													activeState:1, StateImage:1
												}});
												bjhnd.department.UpdateToolbarState();
												break;
											}
										case "unlock":
										{
											bjhnd.department.user_DataGrid_obj.datagrid("updateRow", 
											{index:bjhnd.department.userRowIndex,row:
											{
												activeState:0, StateImage:0
											}});
											bjhnd.department.UpdateToolbarState();
											break;
										}
										default:
										{
											bjhnd.department.repwd_Dialog_obj.dialog("close");
											break;
										}
									}
								}
								else
								{
									$.messager.alert("误错", resultJson.message, "error");	
								}
							}
						});
	};
	//用户信息列表 状态格式化方法
	bjhnd.department.UserStateFormatter = function(value, row, index) {
		return row.activeState == 0 ? "<div title='正常可用' class='icon-unlock easyui-tooltip'>&nbsp;</div>"
				: "<div title='锁定不可用' class='icon-lock easyui-tooltip'>&nbsp;</div>";
	};
	//工具栏按钮事件
	bjhnd.department.UserListToolbarEvent = function(id) {
		var row = bjhnd.department.user_DataGrid_obj.datagrid("getSelected");
		switch(id)
		{
		case "add":
			{
				
				$("#Department_TextBox_userName").removeAttr("disabled");
				$("#Department_TextBox_userId").removeAttr("disabled");
				$("#Department_TextBox_userPassword").removeAttr("disabled");
				$("#Department_TextBox_confirmPassword").removeAttr("disabled");
				bjhnd.department.user_Form_obj.form("reset");
				bjhnd.department.user_Form_obj.form("load", {mode:id, departmentId: bjhnd.department.departmentID});
				bjhnd.department.user_Dialog_obj.dialog("open").dialog("setTitle", "添加新用户");
				break;
			}
		case "edit":
			{
				//只允许修改 人员信息，帐户信息不允许做变更 禁掉所有帐户信息框
				bjhnd.department.user_Form_obj.form("reset");
				bjhnd.department.user_Form_obj.form("load", {mode:id, userPassword:'********', rePassword:'********',departmentId:row.id,
															name:row.name, userId:row.userId, sex:row.sex, post:row.post,
															mobilePhone:row.mobilePhone, officePhone:row.officePhone, address:row.address});
				$("#Department_TextBox_userName").attr("disabled", "disabled");
				$("#Department_TextBox_userId").attr("disabled", "disabled");
				$("#Department_TextBox_userPassword").attr("disabled", "disabled");
				$("#Department_TextBox_confirmPassword").attr("disabled", "disabled");
				bjhnd.department.user_Dialog_obj.dialog("open").dialog("setTitle", "修改<b style='color:red;'>[" + row.name + "]</b>用户信息");
				break;
			}
		case "del":
			{
				$.messager.confirm("提示", "你确定删除姓名为<b style='color:red;font:bold;'>["	+ row.name + "]</b>的用户吗？",function(r)
				{
					if (r)
					{
						bjhnd.department.user_Form_obj.form("reset");
						bjhnd.department.user_Form_obj.form("load", {mode:id, userPassword:'********', rePassword:'********',departmentId:row.id,
																	 name:'********', userId:'********'});
						bjhnd.department.UserFormPost();	
					}
				});
				break;
			}
		case "resetpwd":
			{
				bjhnd.department.repwd_Form_obj.form("reset");
				bjhnd.department.repwd_Dialog_obj.dialog("open").dialog("setTitle", "重置<b style='color:red;'>[" + row.name + "]</b>用户密码");
				bjhnd.department.repwd_Form_obj.form("load", {id:row.id, userId:row.userId, mode:id});
				break;
			}
		case "backpwd":
			{
				bjhnd.department.repwd_Form_obj.form("load", {id:row.id, userId:row.userId, mode:id, userPassword:'********',rePassword:'********'});
				$.messager.confirm("提示","你确定将<b style='color:red;'>[" + row.name + "]</b>用户密码置为最后一次使用的密码吗？", function(r)
				{
					if (r)
						bjhnd.department.ResetPwdFormPost();
				});
				break;
			}
		case "lock":
			{
				bjhnd.department.repwd_Form_obj.form("load", {id:row.id, userId:row.userId, mode:id, userPassword:'********',rePassword:'********'});
				$.messager.confirm("提示","你确定将<b style='color:red;'>[" + row.userId + "]</b>用户锁定吗？", function(r)
				{
					if (r)
						bjhnd.department.ResetPwdFormPost();
				});
				break;
			}
		case "unlock":
		{
			bjhnd.department.repwd_Form_obj.form("load", {id:row.id, userId:row.userId, mode:id, userPassword:'********',rePassword:'********'});
			$.messager.confirm("提示","你确定将<b style='color:red;'>[" + row.userId + "]</b>解除锁定状态吗？", function(r)
			{
				if (r)
					bjhnd.department.ResetPwdFormPost();
			});
			break;
		}
		}
	};
	//用户DBgrid单击
	bjhnd.department.UserDataGridClick = function(rowIndex, rowData)
	{
		bjhnd.department.userRowIndex = rowIndex;
		if (rowData.id != null || rowData.id != undefined)
			bjhnd.department.userID = rowData.id;
		else
			bjhnd.department.userID = undefined;
		bjhnd.department.UpdateToolbarState();
	};
	bjhnd.department.userDatagridLoadSuccess = function(data)
	{
		//默认选中第一行 并且激发单击事件
		if (data.total > 0)
		{
			bjhnd.department.user_DataGrid_obj.datagrid("selectRow", 0);
		}
		else
			bjhnd.department.UserDataGridClick(0, data);
	};
	//更新工具栏按钮状态
	bjhnd.department.UpdateToolbarState = function()
	{
		var row = bjhnd.department.user_DataGrid_obj.datagrid("getSelected");
		button_add = $("#department_linkbutton_add"); //工具栏添加按钮
		button_edit = $("#department_linkbutton_edit"); //工具栏编辑按钮
		button_del = $("#department_linkbutton_del"); //工具栏删除按钮
		button_resetpwd = $("#department_linkbutton_resetpwd"); //工具栏删除按钮
		button_lock = $("#department_linkbutton_lock");//工具栏锁定按钮
		button_unlock = $("#department_linkbutton_unlock");//工具栏解锁按钮
		//如果部门ID为空 侧添加按钮为禁用否则为可用
		if (bjhnd.department.departmentID == undefined || bjhnd.department.departmentID == null)
			button_add.linkbutton('disable');
		else
			button_add.linkbutton('enable');
		if (bjhnd.department.userID == undefined || bjhnd.department.userID == null)
		{
			button_edit.linkbutton('disable');
			button_del.linkbutton('disable');
			button_resetpwd.splitbutton('disable');
		}
		else
		{
			button_edit.linkbutton('enable');
			button_del.linkbutton('enable');
			button_resetpwd.splitbutton('enable');
		}
		if ((row != null || row != undefined) && (row.activeState != null || row.activeState != undefined))
		{
			if (row.activeState == 0)
			{
				button_lock.linkbutton("enable");
				button_unlock.linkbutton("disable");
			}
			else
			if (row.activeState == 1)
			{
				button_lock.linkbutton("disable");
				button_unlock.linkbutton("enable");
			}
		}
	};
	$(document).ready(function()
	{
//		if ($("#Department_Dialog_Depa").length>0){
//			alert(11);
//			$("#Department_Dialog_Depa").remove();
//		}
//		
//		if ($("#Department_Dialog_User").length>0){
//			alert(22);
//			$("#Department_Dialog_User").remove();
//		}
//		
//		if ($("#Department_Dialog_ResetPwd").length>0){
//			alert(33);
//			$("#Department_Dialog_ResetPwd").remove();
//		}
		$.extend($.fn.validatebox.defaults.rules, 
		{
	        /*两次输入密码验证*/
	        equalTo: { validator: function (value, param) { return $(param[0]).val() == value; }, message: '字段不匹配' }
	    });
	});