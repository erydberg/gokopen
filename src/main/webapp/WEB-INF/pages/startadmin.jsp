<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${config.name } admin</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
</head>
<body>
<div class="nav-box">
<div class="page-head">
<h1>${config.name } administration</h1>
</div>
<ul>
<li class="nav-item"><a href="${pageContext.request.contextPath}/patrol">Patruller</a></li>
<li class="nav-item"><a href="${pageContext.request.contextPath}/admin/station">Kontroller</a></li>
<li class="nav-item"><a href="${pageContext.request.contextPath}/admin/distribute">Fördela patruller på startkontroller</a></li>
<li class="nav-item"><a href="${pageContext.request.contextPath}/admin/track">Klasser</a></li>
<li class="nav-item"><a href="${pageContext.request.contextPath}/admin/user">Användare</a>
<li class="nav-item"><a href="${pageContext.request.contextPath}/print">Skriv ut listor</a></li>
<li class="nav-item"><a href="${pageContext.request.contextPath}/admin/config">Konfiguration</a>
<li class="nav-item"><a href="${pageContext.request.contextPath}/">Till huvudmenyn</a>
</ul>
</div>
</body>
</html>