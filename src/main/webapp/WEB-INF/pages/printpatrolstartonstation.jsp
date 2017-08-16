<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Skriv ut kontrollers startpatruller</title>
</head>
<body>
<c:forEach var="startStation" items="${startStations }">
	<div class="print">
	<h2 class="center">Patruller som startar på ${startStation.station.stationNumber }. ${startStation.station.stationName }</h2>
	<table>
	<tr>
		<th class="printtd">Patrull</th>
		<th class="printtd">Klass</th>
		<th class="printtd">Kår</th>
	</tr>
	 <c:forEach var="patrol" items="${startStation.patrols }">
	 	<tr>
	 		<td>${patrol.patrolId}. ${patrol.patrolName }</td>
			<td>${patrol.track.trackName}</td>	 		
	 		<td>${patrol.troop }</td>
		<tr> 	
	 </c:forEach>
	</table>
	</div>
</c:forEach>
</body>
</html>
