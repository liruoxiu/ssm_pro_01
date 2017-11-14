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
    <title>H+ 后台主题UI框架 - 主页</title>

    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

	<%-- 页面样式表 引入 --%>
	<jsp:include page="Common1/theams.jsp" />
	<%-- ./页面样式表 引入 END --%>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">layui 后台布局</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          贤心
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">退了</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="sidebar" id="side-menu">
      	<li class="layui-nav-item ">
			<a id="litest" href="#">通用接口</a>
			<dl class="layui-nav-child">
				<dd id="ddimie">
					<a href="javascript:;" data-url="First.html" id="menuItem12312313">First.html</a>
				</dd>
				<dd class="">
			        <a href="javascript:;" data-url="SystemManager/testEasyui.html" id="menuItem12312313">后台布局</a>
			      </dd>
			      
			</dl>
		</li>
      </ul>
    </div>
  </div>

  
  <div class="layui-body" >
    <!-- 内容主体区域 -->
<!--     <div class="ibox">

		<div class="ibox-content" id="pagecontainer">
				<div class="ibox-title notselect">
        	<h5>省市区插件</h5>
      	</div>
			
		</div>
    </div> -->
<div class="easyui-layout" data-options="fit:true, border:false,noheader:true">
    <div data-options="region:'north',title:'North Title',border:false,noheader:true" >
 		<div class="ibox-title notselect">
        	<h5>省市区插件</h5>
      	</div>
	</div>    	
	<div data-options="region:'center',title:'center title',border:false,noheader:true" style="padding:5px;background:#eee;">
				<div id="pp" class="easyui-panel" title="Load Panel Content"
						data-options="fit:true,border:false,noheader:true,
							tools:[{
								iconCls:'icon-reload',
								handler:function(){
									$('#p').panel('refresh', '_content.html');
								}
							}]
						">
				</div>
				<div class="ibox-content" id="pagecontainer"></div>
	    </div>
	    
</div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>

<script>
//JavaScript代码区域
 
/*  layui.use('element', function(){
  var element = layui.element;
  

	  
	  
	  element.init();
});  */   
$('#p').panel('resize', {height:'auto'});


</script>
</body>

</html>
