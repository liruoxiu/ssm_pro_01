bjhnd.role = {};
bjhnd.role.role_Tree_obj = $("#Role_Tree_Role");
bjhnd.role.role_Dialog_obj = $("#Role_Dialog_Role");
bjhnd.role.role_Form_obj = $("#Role_Form_Role");

bjhnd.role.role_Datagrid_userObj = $("#Role_Datagrid_user");

bjhnd.role.role_datagrid_moduleObj=$("#Role_Datagrid_module");

bjhnd.role.userAccredit_dialog_obj = $("#Role_dialog_userAccredit");

bjhnd.role.Role_tree_deparUser_obj = $("#Role_tree_deparUser");

bjhnd.role.Role_form_userAccredit_obj = $("#Role_form_userAccredit");

bjhnd.role.Role_SelectNode_obj = undefined;

// add by gaishm 20140610 begin
bjhnd.role.Role_form_moduleAccredit_obj= $("#Role_form_moduleAccredit");

bjhnd.role.Role_tree_ruleModule_obj=$("#Role_tree_roleModule");

bjhnd.role.moduleAccredit_dialog_obj=$("#Role_dialog_moduleAccredit");
// add by gaishm 20140610 end

bjhnd.role.RoleTreePopmenu = function(e, node)
{
	e.preventDefault();
	var popmenu_obj = $('#Role_Popmenu');
	var item = popmenu_obj.menu("findItem", "添加");
	popmenu_obj.menu("enableItem", item.target);
	if (node.attributes.type === 0)
	{
		popmenu_obj.menu("disableItem", item.target);
	}
	
	bjhnd.role.role_Tree_obj.tree('select', node.target);
	
	popmenu_obj.menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};
//更新按工具栏状态
bjhnd.role.UpdateToolbar = function()
{
	
};
//用户授权表单 提交
bjhnd.role.userAccreditPost = function()
{
	var Nodes = bjhnd.role.Role_tree_deparUser_obj.tree("getChecked");
	if (Nodes.length <= 0)
	{
		$.messager.alert("提示","请选择授权用户！", "warning");
		return;
	}
	var data = new Array();
	$.each(Nodes, function(key,value)
	{
		data.push(value.attributes.id);
	});
	bjhnd.role.Role_form_userAccredit_obj.form('reset');
	//bjhnd.role.Role_form_userAccredit_obj.form('load',{selectUserID:data, RoleID:bjhnd.role.Role_SelectNode_obj.id});
	/* bjhnd.role.Role_form_userAccredit_obj.form('submit',
			{
				url : 'System/SaveDepartUser.action',
				onSubmit : function() {
					return $(this).form("validate");
				},
				success : function(result) {}
			}); */
	$.ajax({
		   type : 'post',
		   url: "System/SaveDepartUser.action",
		   dataType:'text',
		   data : {
			   selectUserID :  data, RoleID: bjhnd.role.Role_SelectNode_obj.id
		   },
		   success: function(data){
		           //alert(data);
			    $('#Role_dialog_userAccredit').dialog('close');
			    var personSearchKey=$("#personSearch").searchbox("getName");
			    var personSearchComment=$("#personSearch").searchbox("getValue");
			    var gridUrl='System/GetUserRole.action?roleID=' +  bjhnd.role.Role_SelectNode_obj.id+"&key="+personSearchKey+"&search="+encodeURI(encodeURI(personSearchComment));
			    //$('#Role_dialog_userAccredit').dialog('close');
			    bjhnd.role.role_Datagrid_userObj.datagrid({url : gridUrl});
		   }
		});
};



bjhnd.role.moduleAccreditPost= function()
{
	var Nodes = bjhnd.role.Role_tree_ruleModule_obj.tree("getChecked", ['checked','indeterminate']);
	var data = new Array();
	//可以不进行选择
	if (Nodes.length <= 0)
	{
		
		//$.messager.alert("提示","请选择授权用户！", "warning");
		//return;
	}else{
		$.each(Nodes, function(key,value)
		{
			data.push(value.id);
		});
	}
	bjhnd.role.Role_form_moduleAccredit_obj.form('reset');
	$.ajax({
		   type : 'post',
		   url: "System/saveRoleModule.action",
		   dataType:'text',
		   data : {
			   selectModuleID :  data, roleID: bjhnd.role.Role_SelectNode_obj.id
		   },
		   success: function(data){
			    $('#Role_dialog_moduleAccredit').dialog('close');
			    var personSearchKey=$("#moduleSearch").searchbox("getName");
			    var personSearchComment=$("#moduleSearch").searchbox("getValue");
			    var gridUrl='System/getRoleModule.action?roleId=' +  bjhnd.role.Role_SelectNode_obj.id+"&key="+personSearchKey+"&search="+encodeURI(encodeURI(personSearchComment));
			    bjhnd.role.role_datagrid_moduleObj.datagrid({url : gridUrl});
		   }
		});
	
};

//更新授权用户的状态
bjhnd.role.personUpdateToolbarState=function(){
	var btn_user_Role=$("#role_linkbutton_user_Role");
	var btn_user_unRole=$("#role_linkbutton_user_unRole");	
	if (bjhnd.role.Role_SelectNode_obj== undefined || bjhnd.role.Role_SelectNode_obj == null){
		btn_user_Role.linkbutton('disable');
		btn_user_unRole.linkbutton('disable');
	}else{
		btn_user_Role.linkbutton('enable');
		btn_user_unRole.linkbutton('enable');
	}
};

//更新授权模块的状态
bjhnd.role.moduleUpdateToolbarState=function(){
	var btn_module_Role=$("#role_linkbutton_module_Role");
	var btn_module_unRole= $("#role_linkbutton_module_unRole");
	if (bjhnd.role.Role_SelectNode_obj== undefined || bjhnd.role.Role_SelectNode_obj == null){
		btn_module_Role.linkbutton('disable');
		btn_module_unRole.linkbutton('disable');
	}else{
		btn_module_Role.linkbutton('enable');
		btn_module_unRole.linkbutton('enable');
	}
};
//更新状态菜单
bjhnd.role.allState=function(){
	bjhnd.role.personUpdateToolbarState();
	bjhnd.role.moduleUpdateToolbarState();
};

//用户角色按检索按钮进行提交
bjhnd.role.userSearch = function(){
    $('#personSearch').searchbox({
        searcher:function(value,name){
//        	if (value==""){
//        		$.messager.alert('提示',
//        				"请输入检索条件",
//						'info');
//        		return ;
//        	}
        	var gridUrl='System/GetUserRole.action?roleID=' + bjhnd.role.Role_SelectNode_obj.id+"&key="+name+"&search="+encodeURI(encodeURI(value));
			bjhnd.role.role_Datagrid_userObj.datagrid({url : gridUrl});
        }
    });
};
//功能模块检索按钮进行提交
bjhnd.role.moduleSearch = function(){
    $('#moduleSearch').searchbox({
        searcher:function(value,name){
//        	if (value==""){
//        		
//        		$.messager.alert('提示',
//        				"请输入检索条件",
//						'info');
//        		return ;
//        	}
        	
        	var gridUrl='System/getRoleModule.action?roleId=' + bjhnd.role.Role_SelectNode_obj.id+"&key="+name+"&search="+encodeURI(encodeURI(value));
			//alert("gridUrl:"+gridUrl);
			bjhnd.role.role_datagrid_moduleObj.datagrid({url : gridUrl});
        }
    });
};

bjhnd.role.RoleTreeSelect = function(node)
{
	bjhnd.role.userSearch();
	bjhnd.role.moduleSearch();
	
	//显示已授权的用户列表
	//bjhnd.role.UpdateToolbar();
	//alert($("#personSearch").searchbox("getValue"));
	if (bjhnd.role.Role_SelectNode_obj!=undefined){
		if (bjhnd.role.Role_SelectNode_obj.id==node.id){
			return;
		}else{
			//还原右侧的检索条件和检索内容class="l-btn-text"
			$("#personSearch").searchbox("setValue","");
			$("#moduleSearch").searchbox("setValue","");
			//包括已授权用户和已授权模块功能
		}
	}
	bjhnd.role.Role_SelectNode_obj = node;
	//所有的角色不能进行授权和反授权
	//var roleId=bjhnd.role.Role_SelectNode_obj.id;
	//设置授权的状态
	bjhnd.role.allState();
	//设置授权用户角色
	var personSearchKey=$("#personSearch").searchbox("getName");
	var personSearchComment=$("#personSearch").searchbox("getValue");
	var gridUrl='System/GetUserRole.action?roleID=' + node.id+"&key="+personSearchKey+"&search="+encodeURI(encodeURI(personSearchComment));
	bjhnd.role.role_Datagrid_userObj.datagrid({url : gridUrl});
	//设置授权功能模块
	var moduleSearchKey=$("#moduleSearch").searchbox("getName");
	var moduleSearchComment=$("#moduleSearch").searchbox("getValue");
	var moduleUrl='System/getRoleModule.action?roleId=' + node.id+"&key="+moduleSearchKey+"&search="+encodeURI(encodeURI(moduleSearchComment));
	bjhnd.role.role_datagrid_moduleObj.datagrid({url : moduleUrl});
	
};
//角色树菜单 处理
bjhnd.role.RolePopmenu_Event = function(item)
{
	var selectedNode = bjhnd.role.role_Tree_obj.tree("getSelected");
	bjhnd.role.role_Form_obj.form("reset");
	switch(item.name)
	{
		case "addGroup":
			{
				bjhnd.role.FormMode = "add";
				bjhnd.role.role_Dialog_obj.dialog({iconCls:item.iconCls});
				bjhnd.role.role_Form_obj.form("load", {type:'1', parentId:selectedNode.id});
				bjhnd.role.role_Dialog_obj.dialog("open").dialog("setTitle","添加角色组");			
				break;
			}
		case "addRole":
			{
				bjhnd.role.FormMode = "add";
				bjhnd.role.role_Dialog_obj.dialog({iconCls:item.iconCls});
				bjhnd.role.role_Form_obj.form("load", {type:'0', parentId:selectedNode.id});
				bjhnd.role.role_Dialog_obj.dialog("open").dialog("setTitle","添加角色");			
				break;
			}
		case "edit":
		{
			bjhnd.role.FormMode = "edit";
			bjhnd.role.role_Dialog_obj.dialog({iconCls:'icon-role'});
			bjhnd.role.role_Form_obj.form("load", {parentId:selectedNode.id, 
														 name:selectedNode.text, code:selectedNode.attributes.code});
			bjhnd.role.role_Dialog_obj.dialog("open").dialog("setTitle","编辑<b style='color:red'>[" + selectedNode.text + "]</b>信息");			
			break;
		}
		case "del":
		{
			$.messager.confirm("删除", "你确定删除<b style='color:red;'>[" + selectedNode.text +"]</b>节点吗？", function(e)
					{
						if (e){
							
							$.ajax({
								   type : 'post',
								   url: "System/DeleteRole.action",
								   dataType:'text',
								   data : {
									   parentId :  selectedNode.id
								   },
								   success: function(data){
									   var resultJson = $.parseJSON(data);
										if (resultJson.success)
										{
											bjhnd.role.role_Tree_obj.tree(
													"remove", selectedNode.target);
										}else{
											 $.messager.alert("错误", resultJson.message, "error");
										}	
											
//									    $('#Role_dialog_moduleAccredit').dialog('close');
//									    var personSearchKey=$("#personSearch").searchbox("getName");
//									    var personSearchComment=$("#personSearch").searchbox("getValue");
//									    var gridUrl='System/GetUserRole.action?roleID=' +  bjhnd.role.Role_SelectNode_obj.id+"&key="+personSearchKey+"&search="+encodeURI(encodeURI(personSearchComment));
//									    bjhnd.role.role_Datagrid_userObj.datagrid({url : gridUrl});
								   }
								});
							
							
							
//							bjhnd.role.FormMode="del";
//							bjhnd.role.role_Form_obj.form('clear');
//							bjhnd.role.role_Form_obj.form("load", {
//								parentId : selectedNode.id,
//								mode : item.name
//							});
//							bjhnd.role.RoleFormPost();	
						}
					});
			break;
		}
	}
};
//角色表单提交
bjhnd.role.RoleFormPost = function()
{
	var PostUrl=""; 
	var selectedNode = bjhnd.role.role_Tree_obj.tree("getSelected");
	switch(bjhnd.role.FormMode)
	{
		case "add":  {PostUrl = "System/SaveRole.action";break;}
		case "del":  {PostUrl = "System/DeleteRole.action";break;}
		case "edit": {PostUrl = "System/UpdateRole.action";break;}
	}
	bjhnd.role.role_Form_obj.form(
			'submit',
			{
				url : PostUrl,
				onSubmit : function() {
					return $(this).form("validate");
				},
				success : function(result) 
				{
					var resultJson = $.parseJSON(result);
					if (resultJson.success)
					{
						if (bjhnd.role.FormMode == "add")
						{
							if (selectedNode.state == "closed")
								bjhnd.role.role_Tree_obj
										.tree("expand",
												selectedNode.target);
							else
							//如当前节点是展开状态，则添加一个新的节点上去。
							{
								bjhnd.role.role_Tree_obj
										.tree(
												"append",
												{
													parent : selectedNode.target,
													data : {
														id : resultJson.data.id,
														text : resultJson.data.name,
														iconCls : resultJson.data.type == 0 ? "icon-role" : "icon-group",
														attributes :{code : resultJson.data.code, type : resultJson.data.type}
													}
												});
							}
						} else if (bjhnd.role.FormMode == "edit")
						{
							bjhnd.role.role_Tree_obj
							.tree("update",
									{
										target : selectedNode.target,
										text : resultJson.data.name,
										attributes :{code : resultJson.data.code}
									});
						} else if (bjhnd.role.FormMode == "del")
						{
							bjhnd.role.role_Tree_obj.tree(
									"remove", selectedNode.target);
						}
						$('#Role_Dialog_Role').dialog('close');
					}else{
					  $.messager.alert("错误", resultJson.message, "error");
					}
				}
			});
};

//取消授权用户
bjhnd.role.UserToolbarEvent = function(mode) {
	var row = bjhnd.role.role_Datagrid_userObj.datagrid("getSelected");
	if (row==null){
		$.messager.alert("错误", "请为角色选择用户！", "error");	
		return ;
	}
	switch(mode)
	{
		case "del":
			$.messager.confirm("提示", "你确定取消授权姓名为<b style='color:red;font:bold;'>["	+ row.name + "]</b>的用户吗？",function(r)
			{
				if (r)
				{
					$.ajax({
						   type : 'post',
						   url: "System/DeleteUserRole.action",
						   dataType:'text',
						   data : {
							   id :  row.id
						   },
						   success: function(data){
							    //页面刷新
							    var personSearchKey=$("#personSearch").searchbox("getName");
								var personSearchComment=$("#personSearch").searchbox("getValue");
								var gridUrl='System/GetUserRole.action?roleID=' +  bjhnd.role.Role_SelectNode_obj.id+"&key="+personSearchKey+"&search="+encodeURI(encodeURI(personSearchComment));
								bjhnd.role.role_Datagrid_userObj.datagrid({url : gridUrl});
						   }
						});
				}
			});
		break;
		
	}
	//console.debug(row);
	//alert(row);
};

//取消授权模块
bjhnd.role.ModuleToolbarEvent=function(id){
	//计划先不用
};


//授权功能模块
bjhnd.role.ModuleAccreditEvent=function(item){
	bjhnd.role.Role_form_moduleAccredit_obj.form("reset");
	bjhnd.role.Role_tree_ruleModule_obj.tree({data:[],url:'System/getModuleTree.action?roleId='+bjhnd.role.Role_SelectNode_obj.id});
	bjhnd.role.moduleAccredit_dialog_obj.dialog("open").dialog("setTitle", "将角色<b style='color:red'>["
	                                                                      + bjhnd.role.Role_SelectNode_obj.text +"]</b>授权给下列模块");

};

//授权用户
bjhnd.role.UserAccreditEvent =  function(item)
{	
	bjhnd.role.Role_form_userAccredit_obj.form("reset");
	bjhnd.role.Role_tree_deparUser_obj.tree({data:[],url:'System/GetDepartUser.action?roleId='+bjhnd.role.Role_SelectNode_obj.id});
	bjhnd.role.userAccredit_dialog_obj.dialog("open").dialog("setTitle", "将角色<b style='color:red'>["
	                                                                     + bjhnd.role.Role_SelectNode_obj.text +"]</b>授权给下列用户");
};