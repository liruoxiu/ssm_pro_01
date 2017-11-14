
    bjhdab.truckenter = {};
    bjhdab.truckenter.grid_ruchang_index = 0;
    bjhdab.truckenter.grid_chuchang_index = 0;
    
    bjhdab.truckenter.form_ruchangpostmode = "";
    bjhdab.truckenter.form_chuchangpostmode = "";
    
    bjhdab.truckenter.datagrid_ruchang = $('#truckenter_datagrid_ruchang');
    bjhdab.truckenter.datagrid_chuchang = $('#truckenter_datagrid_chuchang');
    bjhdab.truckenter.dialog_addruchang = $("#truckenter_dialog_addruchang");    
    bjhdab.truckenter.dialog_editchuchang = $("#truckenter_dialog_editchuchang");
    
    bjhdab.truckenter.form_addruchang = $("#truckenter_form_addruchang");
    bjhdab.truckenter.form_editchuchang = $("#truckenter_form_editchuchang");
    
    //双击弹出窗体
    bjhdab.truckenter.dialog_doubleclick = $("#truckenter_dialog_doubleclick");
    bjhdab.truckenter.form_doubleclick = $("#truckenter_form_doubleclick");
    bjhdab.truckenter.table_doubleclick = ("#truckenter_table_doubleclick");
    
    //添加入场窗体
    bjhdab.truckenter.combobox_model = $("#truckenter_combobox_model");
    bjhdab.truckenter.combobox_numberplate =$('#addruchang_combobox_numberplate');
    //bjhdab.truckenter.form_editruchang
    
    //称重控制点
    bjhdab.truckenter.dialog_weigh = $("#truckenter_dialog_weigh");
    bjhdab.truckenter.form_weigh =$("#truckenter_form_weigh");
    bjhdab.truckenter.combobox_hweightname=$("#weigh_combobox_hweightname");
    bjhdab.truckenter.combobox_collieryname=$("#weigh_combobox_collieryname");
    bjhdab.truckenter.combobox_coaltypename=$("#weigh_combobox_coaltypename");
    bjhdab.truckenter.combobox_hweightuser=$("#weigh_combobox_hweightuser");
    bjhdab.truckenter.numberbox_fweigh=$("#weigh_numberbox_fweight");
    //采样控制点
    bjhdab.truckenter.dialog_sample= $("#truckenter_dialog_sample");
    bjhdab.truckenter.form_sample=$("#truckenter_form_sample");
    bjhdab.truckenter.combobox_changeType=$("#sample_combobox_changeType");
    bjhdab.truckenter.combobox_samname=$('#sample_combobox_samname');
    bjhdab.truckenter.combotree_samuser=$('#sample_combotree_samuser'); 
    bjhdab.truckenter.combobox_unloadplace=$('#sample_combobox_unloadplace');
    
    //回皮控制点
    bjhdab.truckenter.dialog_backskin= $("#truckenter_dialog_backskin");
    bjhdab.truckenter.form_backskin= $("#truckenter_form_backskin");
    bjhdab.truckenter.combobox_lweightname= $("#backskin_combobox_lweightname");
    bjhdab.truckenter.combotree_eweightuser=$('#backskin_combotree_eweightuser');
    bjhdab.truckenter.numberbox_eweigh=$('#backskin_numberbox_eweight');
    bjhdab.truckenter.numberbox_netweigh=$('#backskin_numberbox_netweight');
    
    //控制点变更窗体
    bjhdab.truckenter.dialog_changecontrolpoint = $("#truckenter_dialog_changecontrolpoint");
    bjhdab.truckenter.form_changecontrolpoint = $("#truckenter_form_changecontrolpoint");
    bjhdab.truckenter.combobox_changecontrolpoint = $("#truckenter_combobox_controlpoint");
    //未出厂查询条件
    bjhdab.truckenter.search_truckname1 = $('#truckenter_textbox_truckname1');
    bjhdab.truckenter.search_begintime1 = $('#truckenter_textbox_begintime1');
    bjhdab.truckenter.search_endtime1 = $('#truckenter_textbox_endtime1');
    bjhdab.truckenter.search_timetype1 = $('#truckenter_textbox_timetype1');
    bjhdab.truckenter.search_collieryname1 = $('#truckenter_textbox_collieryname1');
    //已出厂查询条件
    bjhdab.truckenter.search_truckname2 = $('#truckenter_textbox_truckname2');
    bjhdab.truckenter.search_begintime2 = $('#truckenter_textbox_begintime2');
    bjhdab.truckenter.search_endtime2 = $('#truckenter_textbox_endtime2');
    bjhdab.truckenter.search_timetype2 = $('#truckenter_textbox_timetype2');
    bjhdab.truckenter.search_collieryname2 = $('#truckenter_textbox_collieryname2');
   
    $(function(){  
    	var url = "BasicInformation/getSelectColliery.action";
    	$.getJSON(url, function(json) {
    		bjhdab.truckenter.search_collieryname1.combobox({
    			data:json,
    			panelHeight: 'auto'
    		});
    		bjhdab.truckenter.search_collieryname2.combobox({
    			data:json,
    			panelHeight: 'auto'
    		});    		
    	});
	
    		
    	
    	var ldate = new Date().format("yyyy-MM-dd hh:mm:ss");
    	bjhdab.truckenter.search_begintime1.val(ldate);
    	bjhdab.truckenter.search_endtime1.val(ldate);
    	bjhdab.truckenter.search_begintime2.val(ldate);
    	bjhdab.truckenter.search_endtime2.val(ldate); 
        var gridurl = "TruckCoalManage/getTruckEnter.action?truckName=&collieryName=&timeType=1&beginTime="+ldate+"&endTime="+ldate;
        bjhdab.truckenter.datagrid_ruchang.datagrid({url:gridurl});
    }); 
    
    //判断按钮跳转窗体
    bjhdab.truckenter.panduan_edit= function(){
    	var row = bjhdab.truckenter.datagrid_ruchang.datagrid("getSelected");    
    	if (row.fweighttime==null){
    		  
    		//验证表单（必填项与格式是否正确）

    		//根据GUID加载衡器
    		var url = "BasicInformation/getWeightMachine.action?guid="+"{01861139-9939-0000-0000-138011973113}";
        	$.getJSON(url, function(json) {
        		bjhdab.truckenter.combobox_hweightname.combobox({
        			data:json,
        			panelHeight: 'auto',
        			onSelect:function(data){
        			  	var row = bjhdab.truckenter.datagrid_ruchang.datagrid("getSelected");
        			  	bjhdab.truckenter.form_weigh.form("load", {newcpid:data.controlpointid,ip:data.ipaddress,
        			  		oldcpid:row.controlpointid,tagno:row.tagno});
                    }
        		});
        	});
        	//加载煤矿列表
        	var url = "BasicInformation/getSelectColliery.action";
        	$.getJSON(url, function(json) {
        		bjhdab.truckenter.combobox_collieryname.combobox({
        			data:json,
        			panelHeight: 'auto'
        		});        		
        	});
        	//加载煤种列表
	   		bjhdab.truckenter.combobox_coaltypename.combobox({
	   			url:'BasicInformation/getCoal.action',
	   			method:'post',
	   			valueField: 'id',
                textField: 'coalname',
                panelHeight: 'auto'
	 		});
        	bjhdab.truckenter.combobox_hweightuser.combotree({ 
        		url: 'System/GetDepartUser.action?roleId=-1',
                required: true,
                editable: false,
                panelHeight: 'auto',
                onLoadSuccess: function (data) {
                	bjhdab.truckenter.combobox_hweightuser.combotree('clear');
                },
                onSelect:function(node){
                	//返回树对象  
                    var tree = $(this).tree;  
                    //选中的节点是否为叶子节点,如果不是叶子节点,清除选中  
                    var isLeaf = tree('isLeaf', node.target);  
                    if (!isLeaf) {  
                        //清除选中  
                    	bjhdab.truckenter.combobox_hweightuser.combotree('clear');
                    }
                	if(node.id==-9999){
                		bjhdab.truckenter.form_weigh.form("load", {fweightuser:node.attributes.usercode});
                	}
                }
            });        	
        	bjhdab.truckenter.form_weigh.form("load", {id:row.id});
        	bjhdab.truckenter.dialog_weigh.dialog("open").dialog("setTitle", "称重控制点");
    	}else if (row.samtime==null){
    		bjhdab.truckenter.form_sample.form("clear");
    		//加载采样机列表
    		bjhdab.truckenter.combobox_samname.combobox({
    			url:"TruckCoalManage/getSammachineByTruckEnterId.action?id="+row.id,
	   			valueField: 'sammachineid',
                textField: 'samname',
                panelHeight: 'auto',
    			required:true,
    			editable:false,
	    		onLoadSuccess: function (data) {
	    			if (data) {
	                    for (var item in data) {
	                        if (row.controlpointid == data[item].controlpointid) {
	                            $(this).combobox("select", data[item].sammachineid);
	                        }
	                    }
	                	bjhdab.truckenter.form_sample.form("load", {controlpointid:row.controlpointid});
	                }
	            }
    		});
        	bjhdab.truckenter.combotree_samuser.combotree({ 
        		url: 'System/GetDepartUser.action?roleId=-1',
        		panelHeight: 'auto',
                required: true,
                editable: false,
                onLoadSuccess: function (data) {
                	bjhdab.truckenter.combotree_samuser.combotree('clear');
                },
                onSelect:function(node){
                    //返回树对象  
                    var tree = $(this).tree;  
                    //选中的节点是否为叶子节点,如果不是叶子节点,清除选中  
                    var isLeaf = tree('isLeaf', node.target);  
                    if (!isLeaf) {  
                        //清除选中  
                    	bjhdab.truckenter.combotree_samuser.combotree('clear');
                    }
                	if(node.id==-9999){
                		bjhdab.truckenter.form_sample.form("load", {fweightuser:node.attributes.usercode});
                	}
                }
            }); 
        	//获取煤场区域列表
        	bjhdab.truckenter.combobox_unloadplace.combobox({ 
        		url: 'BasicInformation/getSelectCoalYard.action',
	   			valueField: 'id',
                textField: 'name',
                panelHeight: 'auto',
                required: true,
                editable: false
            });
        	
    		bjhdab.truckenter.combobox_changeType.combobox({ 
    			data:[{'value':'xt','text':'分配小桶','selected':true},
    				  {'value':'cx','text':'重新填写'}],
     			valueField: 'value',
                textField: 'text',
                panelHeight: 'auto',
                required: true,
                onSuccess:function(){
                	if (record.value=='xt'){    					
    					$("#truckenter_table_sample tbody tr:eq(2)").show();
    					$("#truckenter_table_sample tbody tr:eq(3)").hide();
    					$("#truckenter_table_sample tbody tr:eq(4)").hide();
    					
    				}else if (record.value=='cx'){
    					$("#truckenter_table_sample tbody tr:eq(2)").hide();
    					$("#truckenter_table_sample tbody tr:eq(3)").show();
    					$("#truckenter_table_sample tbody tr:eq(4)").show();	
    				}
                	//bjhdab.truckenter.form_sample.form("load", {potname:row.potname});
                	alert(row.potname);
                },
    			onSelect : function(record) {
    				if (record.value=='xt'){    					
    					$("#truckenter_table_sample tbody tr:eq(2)").show();
    					$("#truckenter_table_sample tbody tr:eq(3)").hide();
    					$("#truckenter_table_sample tbody tr:eq(4)").hide();
    					
    				}else if (record.value=='cx'){
    					$("#truckenter_table_sample tbody tr:eq(2)").hide();
    					$("#truckenter_table_sample tbody tr:eq(3)").show();
    					$("#truckenter_table_sample tbody tr:eq(4)").show();	
    				}
                }
    		});
    		bjhdab.truckenter.dialog_sample.dialog("open").dialog("setTitle", "采样控制点");
    	}else if (row.eweighttime==null){
    		bjhdab.truckenter.form_backskin.form("clear");

    		//加载采样机列表
    		var url = "BasicInformation/getWeightMachine.action?guid="+"{01861139-9939-0000-0000-138011973113}";
        	$.getJSON(url, function(json) {
        		bjhdab.truckenter.combobox_lweightname.combobox({
        			//url:"BasicInformation/getWeightMachine.action?guid="+"{01861139-9939-0000-0000-138011973113}",
    	   			valueField: 'id',
                    textField: 'samname',
        			panelHeight: 'auto',
        			data:json,
    	    		onLoadSuccess: function (data) {
                        //默认选中
    	    			if (data) {
    	                    for (var item in data) {
    	                    	if (row.controlpointid == data[item].controlpointid) {
    	                            $(this).combobox("select", data[item].id);
    	                        }
    	                    }
    	                }
    	            },
        			onSelect:function(data){
        			  	bjhdab.truckenter.form_backskin.form("load", {newcpid:data.controlpointid,ip:data.ipaddress,controlpointid:row.controlpointid,tagno:row.tagno});
                    }
        		});
        	});
        	
        	//绑定input事件，计算净重
    		$("input",bjhdab.truckenter.numberbox_eweigh.next("span")).bind("input", function(e) {
    			bjhdab.truckenter.numberbox_netweigh.numberbox('setValue',row.fweight-this.value);   		   
    		}) 
    		
        	bjhdab.truckenter.combotree_eweightuser.combotree({ 
        		url: 'System/GetDepartUser.action?roleId=-1',
        		panelHeight: 'auto',
                required: true,
                editable: false,
                onLoadSuccess: function (data) {
                	bjhdab.truckenter.combotree_eweightuser.combotree('clear');
                },
                onSelect:function(node){
                    //返回树对象  
                    var tree = $(this).tree;  
                    //选中的节点是否为叶子节点,如果不是叶子节点,清除选中  
                    var isLeaf = tree('isLeaf', node.target);  
                    if (!isLeaf) {
                        //清除选中 
                    	bjhdab.truckenter.combotree_eweightuser.combotree('clear');
                    }
                	if(node.id==-9999){
                		bjhdab.truckenter.form_backskin.form("load", {eweightuser:node.attributes.usercode});
                	}
                }
            });
    		bjhdab.truckenter.dialog_backskin.dialog("open").dialog("setTitle", "回皮控制点");
    		
    	}else if (row.leavetime==null){
			$.messager.confirm('出厂控制点', '确认是否出厂?', function(r){
				if (r){
					alert('确认出厂: '+row.id);
				}
			});
    	}    	
    }
    
    //双击弹出窗体
    bjhdab.truckenter.doublclick_edit = function(row,type){
    	bjhdab.truckenter.form_doubleclick.form("clear");
    	$('#truckenter_doubleclick_entertime').html(row.entertime);
    	$('#truckenter_doubleclick_collieryname').html(row.collieryname);
    	$('#truckenter_doubleclick_coaltypename').html(row.coaltypename); 
    	$('#truckenter_doubleclick_numberplate').html(row.numberplate); 
    	$('#truckenter_doubleclick_unloadplace').html(row.unloadplace); 
		$('#truckenter_doubleclick_fweighttime').html(row.fweighttime);  
		$('#truckenter_doubleclick_hweightuser').html(row.hweightuser); 
		$('#truckenter_doubleclick_hweightname').html(row.hweightname);  
		$('#truckenter_doubleclick_fweight').html(row.fweight);
		$('#truckenter_doubleclick_samtime').html(row.samtime);
		$('#truckenter_doubleclick_caiyanguser').html(row.caiyanguser);
		$('#truckenter_doubleclick_samname').html(row.samname);
		$('#truckenter_doubleclick_eweighttime').html(row.eweighttime);
		$('#truckenter_doubleclick_lweightuser').html(row.lweithtuser);
		$('#truckenter_doubleclick_lweightname').html(row.lweightname);
		$('#truckenter_doubleclick_eweight').html(row.eweight);
		$('#truckenter_doubleclick_netweight').html(row.netweight);
		$('#truckenter_doubleclick_leavetime').html(row.leavetime);
		$('#truckenter_doubleclick_zhijianuser').html(row.zhijianuser);
		$('#truckenter_doubleclick_sdeductreason').html(row.sdeductreason);
		bjhdab.truckenter.dialog_doubleclick.dialog('open').dialog('setTitle', '查看来煤详细信息');
    }
    
    //未出厂违规信息编辑事件
    bjhdab.truckenter.ruchang_weigui = function(){
    	
    	var row = bjhdab.truckenter.datagrid_ruchang.datagrid("getSelected");
    	bjhdab.truckenter.panduan_edit(index);
    	
    }
    //未出厂添加来煤信息    
    bjhdab.truckenter.addruchangformpost = function(){
    	//bjhdab.truckenter.form_addruchang
    	//bjhdab.truckenter.dialog_addruchang
    	
    	var lurl = "TruckCoalManage/exchangeControlPoint.action?id="+row.id+"&oldControlPointId="+row.controlpointid+"&newControlPointId="+bjhdab.truckenter.combobox_changecontrolpoint.combobox("getValue");    	
    	bjhdab.truckenter.form_addruchang.form('submit', {
    		url:lurl,
    		dataType:'json',
     		onSubmit: function(){
     			var isValid = $(this).form('validate');
    			if (!isValid){
    			}
    			return isValid;	 
    		}, 
    		success: function(result){
    		    var resultJson = $.parseJSON(result);
    			if (resultJson.success) { 
   					bjhdab.truckenter.datagrid_ruchang.datagrid("updateRow", 
  						{index:bjhdab.truckenter.grid_ruchang_index,row:
  						{
  							controlpointid:resultJson.controlpointid,cpname:bjhdab.truckenter.combobox_changecontrolpoint.combobox("getText")				 
  						} });
   					bjhdab.truckenter.dialog_changecontrolpoint.dialog('close');
    			} else {
    				$.messager.alert("错误", resultJson.message, "error");
    			}
    		}
    	});
    }
    //未出厂控制点切换事件
    bjhdab.truckenter.ruchang_kongzhidian = function(index){
    	bjhdab.truckenter.form_changecontrolpoint.form("clear");
    	row = bjhdab.truckenter.datagrid_ruchang.datagrid('selectRow',index);
    	var row = bjhdab.truckenter.datagrid_ruchang.datagrid("getSelected");
    	var url = "TruckCoalManage/getControlPointByTruckEnterId.action?id="+row.id;
    	$.getJSON(url, function(json) {
    		bjhdab.truckenter.combobox_changecontrolpoint.combobox({
    			data:json,
    			valueField:"id",
    			textField:"cpname",
    			panelHeight:"auto",
    			required:true
    		}); 
    		bjhdab.truckenter.form_changecontrolpoint.form("load", {controlpointid:row.controlpointid});
    		bjhdab.truckenter.dialog_changecontrolpoint.dialog("open").dialog("setTitle", "切换控制点");
    	});
    }
    //未出厂控制点切换窗体提交
    bjhdab.truckenter.changecontrolpointformpost = function() {
    	var row = bjhdab.truckenter.datagrid_ruchang.datagrid("getSelected");
    	var lurl = "TruckCoalManage/exchangeControlPoint.action?id="+row.id+"&oldControlPointId="+row.controlpointid+"&newControlPointId="+bjhdab.truckenter.combobox_changecontrolpoint.combobox("getValue");    	
    	bjhdab.truckenter.form_changecontrolpoint.form('submit', {
    		url:lurl,
    		dataType:'json',
     		onSubmit: function(){
     			var isValid = $(this).form('validate');
    			if (!isValid){
    			}
    			return isValid;	 
    		}, 
    		success: function(result){
    		    var resultJson = $.parseJSON(result);
    			if (resultJson.success) { 
   					bjhdab.truckenter.datagrid_ruchang.datagrid("updateRow", 
  						{index:bjhdab.truckenter.grid_ruchang_index,row:
  						{
  							controlpointid:resultJson.controlpointid,cpname:bjhdab.truckenter.combobox_changecontrolpoint.combobox("getText")				 
  						} });
   					bjhdab.truckenter.dialog_changecontrolpoint.dialog('close');
    			} else {
    				$.messager.alert("错误", resultJson.message, "error");
    			}
    		}
    	});
    };
    //未出厂票重编辑事件
    bjhdab.truckenter.ruchang_piaozhong = function(index){
    	row = bjhdab.truckenter.datagrid_ruchang.datagrid('selectRow',index);
    	var row = bjhdab.truckenter.datagrid_ruchang.datagrid("getSelected");
    }
    //抓拍图片查看事件
    bjhdab.truckenter.zhuapaitupian = function(id){
		alert(id);
    }
    //煤矿编辑事件
    bjhdab.truckenter.meikuang = function(index,id){
		alert(index+'!'+id);
    	row = bjhdab.truckenter.datagrid_ruchang.datagrid('selectRow',index);
    	var row = bjhdab.truckenter.datagrid_ruchang.datagrid("getSelected");
    	alert(row.entertime);
    }   
    //卸煤地点编辑事件
    bjhdab.truckenter.ruchang_xiemeididian = function(index){
		alert(index);
    	row = bjhdab.truckenter.datagrid_ruchang.datagrid('selectRow',index);
    	var row = bjhdab.truckenter.datagrid_ruchang.datagrid("getSelected");
    }
    //未出厂grid工具条
    bjhdab.truckenter.toolbarevent_ruchang = function (type) {
        switch(type) {
            case "search":
             	var opts =bjhdab.truckenter.datagrid_ruchang.datagrid('options');   
                opts.pageNumber = 1; 
            	
                var truckName = bjhdab.truckenter.search_truckname1.val();
                var beginTime = bjhdab.truckenter.search_begintime1.datetimebox('getText'); 
                var endTime = bjhdab.truckenter.search_endtime1.datetimebox('getText'); 
                var timeType = bjhdab.truckenter.search_timetype1.combo('getValue');
                var collieryName = bjhdab.truckenter.search_collieryname1.combo('getText');
            	
                var gridurl = "TruckCoalManage/getTruckEnter.action?truckName="+truckName+"&collieryName="+collieryName+"&timeType="+timeType+"&beginTime="+beginTime+"&endTime="+endTime;
                bjhdab.truckenter.datagrid_ruchang.datagrid({url:gridurl});
                break;
            case "add":
            	bjhdab.truckenter.form_ruchangpostmode = "add";
        		bjhdab.truckenter.form_addruchang.form("clear");
        		bjhdab.truckenter.combobox_model.combobox('setValue','否');
            	//加载煤种列表
            	bjhdab.truckenter.combobox_numberplate.combobox({
            		url:'BasicInformation/getTruckAll.action?truckName=&truckOwnerName=&truckStatus=&truckTagStatus=1',
            		method:'post',
            		valueField: 'id',
                   	textField: 'numberplate',
            		onSelect:function(data){
            			bjhdab.truckenter.form_addruchang.form("load", {tagno:data.tagno});
                    }
            	});            	
        		bjhdab.truckenter.dialog_addruchang.dialog("open").dialog("setTitle", "添加来煤信息");
                break;
            case "edit":
            	var row = bjhdab.truckenter.datagrid_ruchang.datagrid("getSelected");
            	bjhdab.truckenter.form_ruchangpostmode = "edit";
        		//bjhdab.truckenter.form_editruchang.form("clear");
        		//bjhdab.truckenter.form_editruchang.form("load", {id:row.id,numberplate:row.numberplate,ownername:row.ownername,maxload:row.maxload,
				//		statare:row.statare,tarefloat:row.tarefloat,note:row.note,property:row.property});
        		//bjhdab.truckenter.dialog_editruchang.dialog("open").dialog("setTitle", "修改车辆信息");
        		bjhdab.truckenter.panduan_edit();
                break;
            case "del":
           		$.messager.confirm("提示", "确定删除序号为<b style='color:red'>[" + bjhdab.truckenter.grid_ruchang_id + "]</b>的车辆信息吗？<br/><br/>", 
           				function(r)
           				{
        					if (r) {
        						var row = bjhdab.truckenter.datagrid_ruchang.datagrid("getSelected");
        						var truckCoalIds = row.truckcoalids;
         						$.ajax({
        				             type: "POST",
        				             url: "TruckCoalManage/deleteTruck.action?id="+bjhdab.truckenter.grid_ruchang_id,
        				             dataType: "json",
        				             success: function(data)
        				             {
        				            	 if (data.success) {
        				            		 bjhdab.truckenter.datagrid_ruchang.datagrid("deleteRow",bjhdab.truckenter.grid_ruchang_index);
        				            		 if (bjhdab.truckenter.grid_ruchang_index > 0) {
         				            		 	bjhdab.truckenter.datagrid_ruchang.datagrid("selectRow",bjhdab.truckenter.grid_ruchang_index-1);
        				            		 } else {
        				            			 bjhdab.truckenter.grid_ruchang_index = 0; 
        				            			 bjhdab.truckenter.datagrid_ruchang.datagrid("selectRow",0); 
        				            		 }
        				            		 bjhdab.truckenter.update_ruchang_toolbarstate();
        				            	 }
        				            	 else
        				            		 $.messager.alert("错误", "删除失败！<br/>" + data.message, "error");
        				             }
        						}); 
        					}
           		});
                break;
        }
    };
    //车辆信息窗体提交
    bjhdab.truckenter.truckformpost = function() {
    	var urlstr = bjhdab.truckenter.form_ruchangpostmode == "add" ? "TruckCoalManage/saveTruck.action" : 
    				 bjhdab.truckenter.form_ruchangpostmode == "edit" ? "TruckCoalManage/updateTruck.action" : "";
    	
    	bjhdab.truckenter.form_editruchang.form('submit', {
    		url: urlstr,
    		dataType:'json',
     		onSubmit: function(){
     			var isValid = $(this).form('validate');
    			if (!isValid){
    			}
    			return isValid;	 
    		}, 
    		success: function(result){
    		    var resultJson = $.parseJSON(result);
    			if (resultJson.success) {
					switch(bjhdab.truckenter.form_ruchangpostmode)
					{
						case "add":
							resultJson.data.truckstatus = "正常";
							resultJson.data.modeldisplay = "填写";
							bjhdab.truckenter.datagrid_ruchang.datagrid("appendRow", resultJson.data);
							var rowIndex = bjhdab.truckenter.datagrid_ruchang.datagrid("getRows").length -1;
							bjhdab.truckenter.datagrid_ruchang.datagrid("selectRow",rowIndex);
							bjhdab.truckenter.dialog_editruchang.dialog("close");
							//bjhdab.truckenter.grid_ruchang_index = rowIndex;
				       		bjhdab.truckenter.update_ruchang_toolbarstate();
							break; 
						case "edit":
							var row = bjhdab.truckenter.datagrid_ruchang.datagrid("getSelected");
							if (resultJson.data.property == "是") {
								resultJson.data.modeldisplay = "特殊车型";
							} else if (row.modeldisplay == "特殊车型") {
								switch (row.model) {
								case 1:
									resultJson.data.modeldisplay = "半挂";
									break;
								case 2:
									resultJson.data.modeldisplay = "前浅后深式半挂";
									break;
								case 3:
									resultJson.data.modeldisplay = "挂车";
									break;
								}
							}
	      					bjhdab.truckenter.datagrid_ruchang.datagrid("updateRow", 
	     						{index:bjhdab.truckenter.grid_ruchang_index,row:
	     						{
	     							numberplate:resultJson.data.numberplate,ownername:resultJson.data.ownername,maxload:resultJson.data.maxload,property:resultJson.data.property,
	     							statare:resultJson.data.statare,tarefloat:resultJson.data.tarefloat,note:resultJson.data.note,modeldisplay:resultJson.data.modeldisplay				 
	     						} });
	      					bjhdab.truckenter.dialog_editruchang.dialog('close');
	      					bjhdab.truckenter.update_ruchang_toolbarstate();
	      					break;
    				}
    			} else {
    				$.messager.alert("错误", resultJson.message, "error");
    			}
    		}
    	});
    };
    
    //称重窗体提交
    bjhdab.truckenter.form_weighpost= function(){
        var row = bjhdab.truckenter.datagrid_ruchang.datagrid("getSelected");
    	var lurl = "TruckCoalManage/updateWeight.action";    	
    	bjhdab.truckenter.form_weigh.form('submit', {
    		url:lurl,
    		dataType:'json',
     		onSubmit: function(){
     			var isValid = $(this).form('validate');
    			if (!isValid){
    				var colliery=	bjhdab.truckenter.combobox_collieryname.combobox("getValue");
    				if (colliery==""){
    					alert("请选择煤矿");
    					return false;
    				}
    			}    			
    			return isValid;	 
    		}, 
    		success: function(result){
    		    var resultJson = $.parseJSON(result);
    			if (resultJson.success) { 
   					bjhdab.truckenter.datagrid_ruchang.datagrid("updateRow", 
  						{index:bjhdab.truckenter.grid_ruchang_index,row:
  						{
  							controlpointid:resultJson.controlpointid,
  							fweighttime:resultJson.fweighttime,
  							fweight:resultJson.fweight,
  							hweightname:bjhdab.truckenter.combobox_hweightname.combobox("getText"),
  							hweightuser:bjhdab.truckenter.combobox_hweightuser.combotree("getText"),
  							collieryname:bjhdab.truckenter.combobox_collieryname.combobox("getText"),
  							coaltypename:bjhdab.truckenter.combobox_coaltypename.combobox("getText"),
  						} });
   					bjhdab.truckenter.dialog_weigh.dialog('close');
    			} else {
    				$.messager.alert("错误", resultJson.message, "error");
    			}
    		}
    	});
    }
    //采样窗体提交事件
    bjhdab.truckenter.form_samplepost = function(){
    	
    	//分配小桶
    	
    	//验证表单
    	//1采样机不能为空
    	
    	//提交数据
    	//修改T_TRUCKENTER表字段SAMMACHINEID，CONTROLPOINTID
    	//调用CallEJBMethod('21','GETPOTNAME',TSBBF.mUpPassValue1+TAGNO,lRC)
		//if pos(lRC,'Error')>0 then
		//begin
		//  提示信息：lRC
		//end 
		//else
		//begin
		//  lsql:='select potname from '+mSystemID+'_fuel.t_pot where id='+lRC;
		//  提示信息：分配的采样机是：'+TSBBF.mUpPassValue2+',小桶号是:'+TSBBF.TSB_CDSGetFieldValue('pCDSName=mCDS_Common<TSB_PD>pFieldName=potname')
		//end; 
    	
    	//重新填写
    	//验证表单
    	//1采样机能为空，空时为手动采样
    	//2采样员不能为空！
    	//3卸煤地点不能为空！
    	//4由于采样机更改，小桶与采样机不一致，请重新分配小桶！
    	//if (TAGNO='') or (ip='') then
    	//begin 
    	//	提示信息：标签号或控制点不能为空，请检查！
    	//end;
    	//修改 lsqlall:='update '+mSystemID+'_FUEL.T_TRUCKENTER set CONTROLPOINTID='+mOwnerFrame.mUpPassValue9+' where id='+lID;
    	//判断选择后的controlpointid是否等于表格中的controlpointid
    	//如果不相等：修改排队车数
    	
    	//提交数据
    	//
    	//修改T_TRUCKENTER表字段SAMMACHINEID<TSB_FD>SAMTIME<TSB_FD>SAMUSER<TSB_FD>UNLOADPLACEID
    	//controlpointid
    	
//    	IF TSBBF.TSB_CallEJBMethod('21','NextControlPointInfo1','pSystemID=SDSHFDBPS<TSB_PD>pTagNumber='+TAGNO+'<TSB_PD>pIPAddress='+TSBBF.mUpPassValue10,lRC) then
//        begin
//          if pos('Err',lRC)>0 then
//          begin
//            TSBBF.TSB_MessageBox('pCaption='+lRC+'<TSB_PD>pDlgType=MB_OK');
//          end
//          else
//          begin
//            TSBBF.TSB_MessageBox('pCaption=提示<TSB_PD>pMsg=采样成功！<TSB_PD>pDlgType=MB_OK');
//          end;
//        end;

    	
    	var row = bjhdab.truckenter.datagrid_ruchang.datagrid("getSelected");
      	var lurl = "TruckCoalManage/updateSample.action";    	
      	bjhdab.truckenter.form_sample.form('submit', {
      		url:lurl,
      		dataType:'json',
       		onSubmit: function(){
       			var isValid = $(this).form('validate');
      			if (!isValid){
      				var colliery=	bjhdab.truckenter.combobox_collieryname.combobox("getValue");
      				if (colliery==""){
      					alert("请选择煤矿");
      					return false;
      				}
      			}    			
      			return isValid;	 
      		}, 
      		success: function(result){
      		    var resultJson = $.parseJSON(result);
      			if (resultJson.success) { 
     					bjhdab.truckenter.datagrid_ruchang.datagrid("updateRow", 
    						{index:bjhdab.truckenter.grid_ruchang_index,row:
    						{
    							controlpointid:resultJson.controlpointid,
    							fweighttime:resultJson.fweighttime,
    							fweight:resultJson.fweight,
    							hweightname:bjhdab.truckenter.combobox_hweightname.combobox("getText"),
    							hweightuser:bjhdab.truckenter.combobox_hweightuser.combotree("getText"),
    							collieryname:bjhdab.truckenter.combobox_collieryname.combobox("getText"),
    							coaltypename:bjhdab.truckenter.combobox_coaltypename.combobox("getText"),
    						} });
     					bjhdab.truckenter.dialog_weigh.dialog('close');
      			} else {
      				$.messager.alert("错误", resultJson.message, "error");
      			}
      		}
      	});
    }

    //出厂grid工具条
    bjhdab.truckenter.toolbarevent_chuchang  = function (type) {
        switch(type) {
            case "search":
             	var opts =bjhdab.truckenter.datagrid_chuchang.datagrid('options');   
                opts.pageNumber = 1;                 
                var truckName = bjhdab.truckenter.search_truckname2.val();
                var beginTime = bjhdab.truckenter.search_begintime2.datetimebox('getText'); 
                var endTime = bjhdab.truckenter.search_endtime2.datetimebox('getText'); 
                var timeType = bjhdab.truckenter.search_timetype2.combo('getValue');
                var collieryName = bjhdab.truckenter.search_collieryname2.combo('getText');
                var gridurl = "TruckCoalManage/getTruckEnterOver.action?truckName="+truckName+"&collieryName="+collieryName+"&timeType="+timeType+"&beginTime="+beginTime+"&endTime="+endTime;
                bjhdab.truckenter.datagrid_chuchang.datagrid({url:gridurl});               
                break;
            case "edit":
            	var row = bjhdab.truckenter.datagrid_chuchang.datagrid("getSelected"); 
            	bjhdab.truckenter.form_chuchangpostmode = "edit";
        		bjhdab.truckenter.form_editchuchang.form("clear");
       			bjhdab.truckenter.form_editchuchang.form("load", {id:row.id,tagno:row.tagno});
       			bjhdab.truckenter.dialog_editchuchang.dialog("open").dialog("setTitle", "设置标签为无效");
                break;
        }
    };
    //出厂编辑窗体提交
    bjhdab.truckenter.chuchangformpost = function() {
    	var urlstr = bjhdab.truckenter.form_chuchangpostmode == "edit" ? "TruckCoalManage/updatechuchang.action" : "";
    	bjhdab.truckenter.form_editchuchang.form('submit', {
    		url: urlstr,
    		dataType:'json',
     		onSubmit: function(){
     			var isValid = $(this).form('validate');
    			if (!isValid){
    			}
    			return isValid;	 
    		}, 
    		success: function(result){
    		    var resultJson = $.parseJSON(result);
    			if (resultJson.success) {
					switch(bjhdab.truckenter.form_chuchangpostmode)
					{
						case "edit":  
	      					bjhdab.truckenter.datagrid_chuchang.datagrid("updateRow", 
	     						{index:bjhdab.truckenter.grid_chuchang_index,row:
	     						{
	     							status:resultJson.data.status,statusdisplay:"无效",logorchangedate:resultJson.data.logorchangedate,operator:resultJson.data.operator,note:resultJson.data.note				 
	     						} });
	      					bjhdab.truckenter.dialog_editchuchang.dialog('close');
	      					bjhdab.truckenter.update_chuchang_toolbarstate();
	      					break;
    				}
    			} else {
    				$.messager.alert("错误", resultJson.message, "error");
    			}
    		}
    	});
    };
   	//未出厂信息工具条状态
     bjhdab.truckenter.update_ruchang_toolbarstate = function()
     {
     	var row = bjhdab.truckenter.datagrid_ruchang.datagrid("getSelected");
     	if (row != null && row != undefined)
     	{
			$('#truckenter_toolbar_ruchang_edit').linkbutton('enable');
			$('#truckenter_toolbar_ruchang_del').linkbutton('enable');
		     	} else {
			$('#truckenter_toolbar_ruchang_edit').linkbutton('disable');
			$('#truckenter_toolbar_ruchang_del').linkbutton('disable');
     	}
     };
    //已出厂信息工具条状态
     bjhdab.truckenter.update_chuchang_toolbarstate = function()
     {
     	var row = bjhdab.truckenter.datagrid_chuchang.datagrid("getSelected");
     	if (row != null && row != undefined)
     	{
     		if (row.status == "0") {
     			$('#truckenter_toolbar_chuchang_edit').linkbutton('enable');
     		} else {
     			$('#truckenter_toolbar_chuchang_edit').linkbutton('disable');
     		}
		} else {
			$('#truckenter_toolbar_chuchang_edit').linkbutton('disable');
     	}
     };