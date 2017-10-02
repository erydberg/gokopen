<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Skriv ut små startkort för alla patruller</title>
</head>
<body>
<div class="printpage">
<ul>
<c:forEach items="${patrols}" var="patrol">
	<li class="smallcard">
	Startkort ${config.name }<br>
	<b>${patrol.patrolName }</b><br>
	Patrullnummer: ${patrol.patrolId }<br>
	${patrol.troop }<br>
	${patrol.track.trackName }<br>
	<c:if test="${not empty patrol.startStation }">Startplats: ${patrol.startStation.stationNumber}. ${patrol.startStation.stationName}</c:if>
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