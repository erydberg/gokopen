<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Gök Open Admin</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
</head>
<body>
<div class="nav-box">
<h1>GökOpen administration</h1>
<ul>
<li class="nav-item"><a href="/gokopen/patrol">Visa patruller</a></li>
<li class="nav-item"><a href="/gokopen/admin/station">Visa kontroller</a></li>
<li class="nav-item"><a href="/gokopen/admin/track">Visa klasser</a></li>
<li class="nav-item"><a href="/gokopen">Till huvudmenyn</a>
</ul>
</div>
</body>
</html>