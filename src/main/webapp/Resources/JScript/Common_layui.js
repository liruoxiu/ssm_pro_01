var bjhnd={};//定义命名空间为bjhnd
var bjhdab={};

bjhnd.core = {};
bjhnd.core.maintabs = [];


layui.use(['layer','element','jquery','form'], function(){

	var layer = layui.layer
	,element=layui.element
	,form = layui.form
	,$ = layui.$
	,layedit = layui.layedit
	,laydate = layui.laydate;
	
	/*侧边栏伸缩*/
	var isShow = true;  //定义一个标志位
	$('.kit-side-fold').click(function(){
	    //选择出所有的span，并判断是不是hidden
	    $('.layui-nav-item cite').each(function(){
	        if($(this).is(':hidden')){
	            $(this).show();
	        }else{
	            $(this).hide();
	        }
	    });
	    //判断isshow的状态
	    if(isShow){
	        $('.layui-side.layui-bg-black').width(60); //设置宽度
	        $('.kit-side-fold i').css('margin-right', '70%');  //修改图标的位置
	        //将footer和body的宽度修改
	        $('.layui-body').css('left', 60+'px');
	        $('.layui-footer').css('left', 60+'px');
	        //将二级导航栏隐藏
	        $('dd cite').each(function(){
	            $(this).hide();
	        });
	        //修改标志位
	        isShow =false;
	    }else{
	        $('.layui-side.layui-bg-black').width(200);
	        $('.kit-side-fold i').css('margin-right', '10%');
	        $('.layui-body').css('left', 200+'px');
	        $('.layui-footer').css('left', 200+'px');
	        $('dd cite').each(function(){
	            $(this).show();
	        });
	        isShow =true;
	    }
	    setHeight();
	}); 
  
    /*easyui-layout 自适应宽高*/
	function setHeight(){
		var c = $('#easyui_layout');
		var p = c.layout('panel','center');	// get the center panel
		var oldHeight = p.panel('panel').outerHeight();
		p.panel('resize', {height:'auto'});
		var newHeight = p.panel('panel').outerHeight();
		c.layout('resize',{
			height: (c.height() + newHeight - oldHeight)
		});
	}
	
    /*监听侧边导航按钮*/
	element.on('nav(sidebar)',function($elem) {
		
		var $a = $elem.children("a");
		//获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
		var layid = $a.attr("id");
		var page_href=$a.data("url");
		var page_icon = $a.children('i:first').data('icon');		
		var page_title=$a.children("cite").text();
		
		console.info($a.text());

		/*easyui的加载*/
		$('.layui-body').children('.ibox').children('.ibox-title').html("<h5>"+page_title+"</h5>")
		$('#easyui_panel_main').panel({
		    href:page_href,
		    onLoad:function(result){
		         $result = $(result);
		         $result.find("script").appendTo('.easyui-layout');
		    }
		});
		
		/*纯div加载*/
/*		$('.layui-body').children('.ibox').children('.ibox-title').html("<h5>"+page_title+"</h5>");
		$("#pagecontainer").load(page_href,null,function(result){
	         //将被加载页的JavaScript加载到本页执行
	         $result = $(result); 
	         $result.find("script").appendTo('.easyui-layout');
		}); */
		
		
		
		/*layui-tab方式加载*/
	/*	if ($('[lay-id="' + layid+'"]').length === 0) {	
		
			var data = {
				url : $a.data("url"),
				title : $a.children("span").text(),
				//content: '选项卡的内容', //支持传入html
				id: $a.attr("id")
			}
			if (data.url != undefined) {
				element.tabAdd('demo',data);
				// frame高度自适应
				var $content = $('.layui-tab-content');
				$content.find('iframe').each(function() {
					$(this).height($content.height());
				});
				  
				element.tabChange('demo', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项
			}
		} else{
			element.tabChange('demo', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项
		}*/
	});
	
		/*
	  
	  //监听Tab切换，以改变地址hash值
	  element.on('tab(demo)', function(){
	    location.hash = 'demo='+ this.getAttribute('lay-id');
	  });
	  
	  element.tabAdd('demo', {
		  title: '选项卡的标题'
		  ,content: '选项卡的内容' //支持传入html
		  ,id: '选项卡标题的lay-id属性值'
		});  
	  //一些事件监听
	  element.on('tab(demo)', function(data){
	    console.log(data);
	  });*/
	
	  $.getJSON("System/systemInitialization.action", function(result) {
			// 生成一级主菜单
			$(document).attr("title", result.title);
			console.info(result);
			if (result.menu != null)
			{				
			    var staticItem = [{
						title: "主页",
						icon: 'fa fa-home fa-fw',
						href:'First.html',
					    spread:true				    
					},
					{
						title: "测试demo",
						icon: 'fa fa-paper-plane-o fa-fw',
						href:"",                  
						spread:false,
					    children:[
					        {
					        	title: "后台布局",
					        	icon: '',
					        	href:'demo/testEasyui.action'
					        },
					        {
					        	title: "测试弹出层",
					        	icon: '',
					        	href:'demo/testLayui.action'
					        },
					        {
					        	title: "测试grid常用操作",
					        	icon: '',
					        	href:'demo/testGridCtr.action'
					        },
					        {
					        	title: "activity",
					        	icon: '',
					        	href:'model/list.action'
					        }
					    ]
				}];
			    
			    /*合并静态菜单部分*/
/*			    $.each(result.menu, function(key, field) {
			    	staticItem.push(field);
			    });*/
			    
			    /*生成菜单*/
			    $('#left').html(getHtml(staticItem));
			    
			    /*重新渲染菜单*/
			    element.init();
			}
		});
	  
		InitMenu = function(data)
		{
			var menu_obj =$("#side-menu");
			var Item;
			var subItem;
			$.each(data, function(key, field) {
				// 创建一个A元素
				try {
					 Item=$("<li class=\"layui-nav-item  layui-this\"><a class=\"\" href=\"javascript:;\">" + field.name + "</a></li>");
					 subItem = initSubMenu(field.subMenu);
					 Item.append(subItem);
					 menu_obj.append(Item); 
					 //console.info(Item);
					 //加入主菜单					 
				} catch (e) {
					console.error(e);
				}
			});
		}
		
		initSubMenu= function(data)
		{
			var menuItem = $("<dl class=\"layui-nav-child\"></dl>");
			$.each(data, function(id, value)
			{
				var div = $("<dd><a href=\"javascript:;\" data-url=\""+value.attributes.url+"\" id=\"menuItem"+value.id+"\"><cite>" + value.text + "</cite></a></dd>")
				menuItem.append(div);
			});
			return menuItem;
		}	

		/**
		 * 获取html字符串
		 * @param {Object} data
		 */
	    function getHtml(data) {
	        //debugger;
	        var ulHtml = '<ul class="layui-nav layui-nav-tree" lay-shrink="all"  lay-filter="sidebar">';
	        for (var i = 0; i < data.length; i++) {
	            if (data[i].spread) {
	                ulHtml += '<li class="layui-nav-item layui-nav-itemed">';
	            } else {
	                ulHtml += '<li class="layui-nav-item">';
	            }
	            if (data[i].children !== undefined && data[i].children !== null && data[i].children.length > 0) {
	                ulHtml += '<a href="javascript:;">';
	                if (data[i].icon !== undefined && data[i].icon !== '') {
	                    if (data[i].icon.indexOf('fa-') !== -1) {
	                        ulHtml += '<i class="fa ' + data[i].icon + '" aria-hidden="true" data-icon="' + data[i].icon + '"></i>';
	                    } else {
	                        ulHtml += '<i class="layui-icon" data-icon="' + data[i].icon + '">' + data[i].icon + '</i>';
	                    }
	                }
	                ulHtml += '<cite>' + data[i].title + '</cite>'
	                ulHtml += '</a>';
	                ulHtml += '<dl class="layui-nav-child">'
	                for (var j = 0; j < data[i].children.length; j++) {
	                    ulHtml += '<dd title="' + data[i].children[j].title + '">';
	                    ulHtml += '<a href="javascript:;" data-url="' + data[i].children[j].href + '">';
	                    if (data[i].children[j].icon !== undefined && data[i].children[j].icon !== '') {
	                        if (data[i].children[j].icon.indexOf('fa-') !== -1) {
	                            ulHtml += '<i class="fa ' + data[i].children[j].icon + '" data-icon="' + data[i].children[j].icon + '" aria-hidden="true"></i>';
	                        } else {
	                            ulHtml += '<i class="layui-icon" data-icon="' + data[i].children[j].icon + '">' + data[i].children[j].icon + '</i>';
	                        }
	                    }
	                    ulHtml += '<cite>' + data[i].children[j].title + '</cite>';
	                    ulHtml += '</a>';
	                    ulHtml += '</dd>';
	                }
	                ulHtml += '</dl>';
	            } else {
	                var dataUrl = (data[i].href !== undefined && data[i].href !== '') ? 'data-url="' + data[i].href + '"' : '';
	                ulHtml += '<a href="javascript:;" ' + dataUrl + '>';
	                if (data[i].icon !== undefined && data[i].icon !== '') {
	                    if (data[i].icon.indexOf('fa-') !== -1) {
	                        ulHtml += '<i class="fa ' + data[i].icon + '" aria-hidden="true" data-icon="' + data[i].icon + '"></i>';
	                    } else {
	                        ulHtml += '<i class="layui-icon" data-icon="' + data[i].icon + '">' + data[i].icon + '</i>';
	                    }
	                }
	                ulHtml += '<cite>' + data[i].title + '</cite>'
	                ulHtml += '</a>';
	            }
	            ulHtml += '</li>';
	        }
	        ulHtml += '</ul>';

	        return ulHtml;
	    }
	
  layer.msg('欢迎登录后台管理系统');
});

