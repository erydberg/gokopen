<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<script>
	var path = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/gokopen.js"></script>

<title>Visa patruller</title>
</head>
<body>
	<div class="nav-box">
		<c:if test="${not empty errormsg }">
			<div class="errorblock">${errormsg}</div>
		</c:if>
		<span class="toolbar">
			<a class="toolitem" href="${pageContext.request.contextPath}/startmenu"><img src="${pageContext.request.contextPath}/css/back2.png"></a>
			<a class="toolitem" href="#" onClick="reloadPage();"><img src="${pageContext.request.contextPath}/css/reload.png"></a>
		</span>
		<h1>Alla patruller</h1>
		<c:if test="${not empty patrols }">
			Totalt ${fn:length(patrols)} patruller
		</c:if>
		</div>
	<c:if test="${not empty patrols }">
	<c:forEach items="${patrols }" var="patrol" varStatus="status">
	<div class="scoreitem">
	<a href="${pageContext.request.contextPath}/patrol/viewpatrolfrompatrollist/${patrol.patrolId}">${patrol.patrolName }</a> (${patrol.track.trackName })<br/>
	${patrol.troop }<br/>
	Antal kontroller: ${patrol.totalReportedStations }
	</div>
	</c:forEach>
	</c:if>
</body>
</html>