<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Visa patruller</title>
</head>
<body>
<div class="nav-box">
<div>
<a href="${pageContext.request.contextPath}/">Tillbaka</a>
</div>
<h1>Alla patruller</h1>
	<c:if test="${not empty patrols }">
	<c:forEach items="${patrols }" var="patrol" varStatus="status">
	<div class="scoreitem">
	<a href="${pageContext.request.contextPath}/patrol/viewpatrolfrompatrollist/${patrol.patrolId}">${patrol.patrolName }</a> (${patrol.track.trackName })<br/>
	${patrol.troop }<br/>
	Antal stationer: ${patrol.totalReportedStations }
	</div>
	</c:forEach>
	</c:if>
	</div>
</body>
</html>