<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="tags.jsp" %>
<c:forEach var="parentMenu" items="${parentMenus}">
    <c:set var="key" value="${parentMenu.id}${''}" scope="request"></c:set>
    <c:set var="sms" value='${subMenus[key]}' scope="request"></c:set>
    <c:choose>
        <c:when test='${not empty sms and fn:length(sms) > 0}'>
        	<li class="treeview">
        		<a href="${not empty parentMenu.url?ctx:''}${not empty parentMenu.url?parentMenu.url:'javascript:void(0)'}">
        		<i class="${not empty parentMenu.iconcls?parentMenu.iconcls:'fa fa-bars'}"></i> <span>${parentMenu.text}</span>
        			<span class="pull-right-container">
		              <i class="fa fa-angle-left pull-right"></i>
		            </span>
        		</a>
        		<ul class="treeview-menu">
        			<c:forEach var="sm" items="${sms}">
                        <c:set var="parentMenus" value="${sms}" scope="request" />
                        <%-- 相对项目访问地址的绝对地址引用，后期直接调用，不需要修改地址 --%>
                        <c:import url="/web/common/recursionMenu.jsp" />
                    </c:forEach>
        		</ul>
        	</li>
        </c:when>
        <c:otherwise>
        	<li>
        		<a href="${not empty parentMenu.url?ctx:''}${not empty parentMenu.url?parentMenu.url:'javascript:void(0)'}">
        		<i class="${not empty parentMenu.iconcls?parentMenu.iconcls:'fa fa-circle-o'}"></i> <span>${parentMenu.text}</span>
        		</a>
        	</li>
        </c:otherwise>
    </c:choose>
</c:forEach>