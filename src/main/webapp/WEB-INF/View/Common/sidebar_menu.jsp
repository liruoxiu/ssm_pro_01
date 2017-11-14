<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="tags.jsp" %>

<!-- Left side column. contains the logo and sidebar -->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" class="Resources/hplus/img-circle" src="Resources/hplus/img/profile_small.jpg" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">Beaut-zihan</strong></span>
                                <span class="text-muted text-xs block">超级管理员<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="J_menuItem" href="Resources/hplus/form_avatar.html">修改头像</a>
                                </li>
                                <li><a class="J_menuItem" href="Resources/hplus/profile.html">个人资料</a>
                                </li>
                                <li><a class="J_menuItem" href="Resources/hplus/contacts.html">联系我们</a>
                                </li>
                                <li><a class="J_menuItem" href="Resources/hplus/mailbox.html">信箱</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="Resources/hplus/login.html">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">H+
                        </div>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">主页</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="Resources/hplus/index_v1.html" data-index="0">主页示例一</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="Resources/hplus/index_v2.html">主页示例二</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="Resources/hplus/index_v3.html">主页示例三</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="Resources/hplus/index_v4.html">主页示例四</a>
                            </li>
                            <li>
                                <a href="Resources/hplus/index_v5.html" target="_blank">主页示例五</a>
                            </li>
                     		<li>
                                <a class="J_menuItem" href="SystemManager/Department.html">主页示例1四</a>
                            </li>                            
                            
                        </ul>
                    </li>


                </ul>
            </div>
        </nav>
  
<!-- jQuery 3 -->
<%-- <script src="${ctx }/resource/adminlte/bower_components/jquery/dist/jquery.min.js"></script> --%>
  <!-- 动态控制菜单的active -->
 <script type="text/javascript">

 </script>
 	<script type="text/javascript">

		Initialization = function(){
			$.getJSON("System/systemInitialization.action", function(result) {
				// 生成一级主菜单
				$(document).attr("title", result.title);
				$("#SystemTitle").text(result.title);
				if (result.menu != null)
				{
					InitMenu(result.menu);
					
				}
			});

		}

		InitMenu = function(data)
		{
			var menu_obj = $("#side-menu");
			var Item;
			var subItem;
			var body_obj = $("body");
			$.each(data, function(key, field) {
				// 创建一个A元素
				try {				
					 Item = $("<li><a href=\"#\"><i class=\""+field.icon+"\"></i><span class=\"nav-label\">" + field.name + "</span><span class=\"fa arrow\"></span></a></li>");
					 subItem = initSubMenu(field.subMenu);
					 Item.append(subItem);
					 menu_obj.append(Item); 
					 //console.info(data);
					 //加入主菜单					 
				} catch (e) {
					console.error(e);
				}
			});
		}
		initSubMenu= function(data)
		{
			var menuItem = $("<ul class=\"nav nav-second-level \"></ul>");
			$.each(data, function(id, value)
			{
				var div = $("<li><a class=\"J_menuItem\" href=\""+value.attributes.url+"\" data-index=\""+value.id+"\">" + value.text + "</a></li>")
				menuItem.append(div);
			});
			return menuItem;
		}
 		$(document).ready(function(){
			//Initialization();
			//$.parser.parse();

/* 			$("#side-menu").metisMenu();
  			$("#side-menu>li").click(function(){$("body").hasClass("mini-navbar")&&NavToggle()});
			$("#side-menu>li li a").click(function(){$(window).width()<769&&NavToggle()});
			$(".nav-close").click(NavToggle);   */
			
/*  			$.getScript("Resources/hplus/js/bootstrap.min.js");
			$.getScript("Resources/hplus/js/plugins/metisMenu/jquery.metisMenu.js");
			$.getScript("Resources/hplus/js/plugins/slimscroll/jquery.slimscroll.min.js");
			$.getScript("Resources/hplus/js/plugins/layer/layer.min.js");
			$.getScript("Resources/hplus/js/hplus.min.js");
			$.getScript("Resources/hplus/js/contabs.min.js");
			$.getScript("Resources/hplus/js/plugins/pace/pace.min.js");  */
			
		}); 

	</script> 
