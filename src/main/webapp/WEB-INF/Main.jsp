<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%-- 标签库 --%>
<%@include file="Common1/tags.jsp"%>
<!DOCTYPE html>
<html>

<head>
	<%-- <base href="<%=basePath%>"> --%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>后台主页</title>

    <meta name="keywords" content="后台HTML,响应式后台">
    <meta name="description" content="后台HTML,响应式后台">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

	<%-- 页面样式表 引入 --%>
	<jsp:include page="Common1/theams.jsp" />
	<%-- ./页面样式表 引入 END --%>
</head>
<body  class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo"><h2>后台管理系统</h2></div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
<!--     <ul class="layui-nav layui-layout-left" lay-filter="sidebar">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
          <dd><a href="javascript:;" data-url="First.html" id="menuItem12312313"><span>First.html</span></a></dd>
        </dl>
      </li>
    </ul> -->
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          	<span id="username"><%=session.getAttribute("userName")%></span>
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="systemQuit.action">退出</a></li>
    </ul>
  </div>


  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <div title="菜单缩放" class="kit-side-fold"><i class="fa fa-navicon fa-fw" aria-hidden="true"></i></div>
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="sidebar" id="side-menu">
      	<li class="layui-nav-item ">	
      		<a href="javascript:;" data-url="First.html" id="menuItem0"><i class="fa fa-home fa-fw"></i><cite>主页</cite></a>
      	</li>
      	<li class="layui-nav-item ">
			<a id="litest" href="#"><i class="fa fa-paper-plane-o fa-fw"></i><cite>测试demo</cite></a>
			<dl class="layui-nav-child">
				<dd class="">
			        <a href="javascript:;" data-url="demo/testEasyui.action" id="menuItem12312313"><cite>后台布局</cite></a>
			    </dd>
				<dd class="">
			        <a href="javascript:;" data-url="demo/testLayui.action" id="menuItem12312313"><cite>测试弹出层</cite></a>
			    </dd>			    
			    <dd class="">
			        <a href="javascript:;" data-url="demo/testGridCtr.action" id="menuItem12312313"><cite>测试grid常用操作</cite></a>
			    </dd>	
				<dd id="ddimie">
					<a href="javascript:;" data-url="model/list.action" id="menuItem0"><cite>activity</cite></a>
				</dd>			    
			</dl>
		</li>
      </ul>
	  <!-- 左侧导航 -->
	  <div id="left" class="site-text" lay-filter="left-div"></div>
    </div>

  </div>
  
  <div class="layui-body" >
	    <!-- 内容主体区域 -->
<!-- 	    <div class="ibox">
			<div class="ibox-title notselect"><h5>省市区插件</h5></div>
					
	    	<div class="ibox-content" id="pagecontainer"> 	    	
	    	</div>
	    	
	    </div> -->
    <!-- 内容主体区域 -->
    <div class="ibox">
		<div class="ibox-title notselect">
        	<h5>主页</h5>
      	</div>
		<div class="ibox-content" id="pagecontainer">
			<div class="easyui-layout" id="easyui_layout" data-options="fit:true, border:0">
				<div id="easyui_panel_main" class="easyui-panel"  style="background-color: white"  data-options="region:'center',href:'First.html'"  border="false">
				</div>
			</div>
		</div>
    </div>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>  
  
</div>
<script>
//JavaScript代码区域
  

</script>
</body>

</html>
