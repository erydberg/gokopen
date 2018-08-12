<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Ingen registrering av patruller för ${config.name }</title>
</head>
<body>
	<div class="nav-box">
		<div class="page-head">
			<h1>Ingen registrering av patruller för ${config.name }</h1>
		</div>
	</div>
	<div class="ingress">
		<c:if test="${not empty errormsg }">
			<div class="errorblock">${errormsg}</div>
		</c:if>
		<c:if test="${not empty confirmmsg }">
			<div class="confirmblock">${confirmmsg}</div>
		</c:if>
		<p>Det går inte att registrera några patruller för tävlingen. Anmälan är stängd, sista dagen har passerat och/eller antalet anmälda patruller har nått max-gränsen.</p>
		<a href="${pageContext.request.contextPath}/">Till startsidan</a>
	</div>
</body>
</html>