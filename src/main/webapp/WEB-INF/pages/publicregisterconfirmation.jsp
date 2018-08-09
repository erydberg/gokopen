<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Bekräftelse anmäld patrull till ${config.name }</title>
</head>
<body>
	<h1>Patrull ${patrol.patrolName} anmäld till ${config.name }</h1>

	<c:if test="${not empty errormsg }">
		<div class="errorblock">${errormsg}</div>
	</c:if>
	<c:if test="${not empty confirmmsg }">
		<div class="confirmblock">${confirmmsg}</div>
	</c:if>
	
	Du kommer få ett mail som bekräftelse också till den mailadress du skrev i e-post-fältet.
	
	<a href="${pageContext.request.contextPath}/register">Klicka här om du vill anmäla en patrull till.</a>

</body>
</html>