<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="sv">
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Skriv ut små startkort för alla patruller</title>
</head>
<body>
<div class="printpage">
<ul>
<c:forEach items="${patrols}" var="patrol">
	<li class="smallcard">
	${config.name }<br>
	<div class="patrolName">${fn:substring(patrol.patrolName, 0, 35 )}<c:if test="${fn:length(patrol.patrolName)>35}"> ...</c:if></div>
	Patrullnummer: ${patrol.patrolId }<br>
	${patrol.troop }<br>
	${patrol.track.trackName }<br>
	<div class=small-text>
	<c:if test="${not empty patrol.startStation }">
	    Start: ${patrol.startStation.stationNumber} ${fn:substring(patrol.startStation.stationName,0,27)}
	    <c:if test="${fn:length(patrol.startStation.stationName)>27}"> ...
	    </c:if>
	</c:if>
	<c:if test="${empty patrol.startStation }">
	  <br>
	</c:if>
	</div>
	<div class="stationlist">
	<ul class="stations">
	<c:forEach items="${stations }" var="station">
		<li>${station.stationNumber }</li>
	</c:forEach>
	</ul>
	</div>
</li>
</c:forEach>
</ul>
</div>

</body>
</html>