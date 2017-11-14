<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%-- 引入公共的css/js文件,tab标签 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%-- jstl 标签 --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%-- jstl function 标签 --%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%-- jstl format 标签 --%>
<%-- 设置页内路径对象 --%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"></c:set>