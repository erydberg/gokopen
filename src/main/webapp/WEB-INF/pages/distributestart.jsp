<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Fördela patruller på startkontroller</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
</head>
<body>

<div class="nav-box">
<c:if test="${not empty errormsg }">
<div class="errorblock">
${errormsg}
</div>
</c:if>
<c:if test="${not empty msg }">
<div class="statusrow">
${msg }
</div>
</c:if>
<span class="toolbar">
			<a class="toolitem" href="${pageContext.request.contextPath}/admin/"><img src="${pageContext.request.contextPath}/css/back2.png"></a>
		</span>
		<div class="page-head">
<h1>Fördela patrullernas startkontroller</h1>
</div>
<ul>
<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
<li class="nav-item"><a href="${pageContext.request.contextPath}/admin/distribute/all">Fördela alla patruller jämt över alla kontroller</a><br></li>
<li class="nav-item"><a href="${pageContext.request.contextPath}/admin/distribute/basedontrack">Fördela alla patruller per klass jämt över alla kontroller</a><br></li>
</sec:authorize>
</ul>
</div>
</body>
</html>