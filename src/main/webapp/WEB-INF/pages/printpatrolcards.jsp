<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Skriv ut startkort för alla patruller</title>
</head>
<body>
<c:forEach items="${patrols}" var="patrol">
	<div class="print">
	<h1 class="center">Startkort ${config.name }</h1>
	<h2 class="center"><b>${patrol.patrolName }</b></h2>
	<h4 class="center">Patrullnummer: ${patrol.patrolId }</h4>
	<h4 class="center">${patrol.troop }</h4>
	<h4 class="center">${patrol.track.trackName }</h4>
	<c:if test="${not empty patrol.startStation }"><h4 class="center">Startplats: ${patrol.startStation.stationNumber}. ${patrol.startStation.stationName}</h4></c:if>
	<table>
	<tr>
		<th class="station">Kontroll</th>
		<th>Poäng</th>
		<th>Stilp.</th>
		<th>Totalt</th>
		<th>Sign.</th>
	</tr>
	<c:forEach items="${stations }" var="station">
	<tr>
		<td class="station">${station.stationNumber }. ${station.stationName } </td>
		<td> </td>
		<td> </td>
		<td> </td>
		<td> </td>
	</tr>
	</c:forEach>
	</table>
	</div>
</c:forEach>
</body>
</html>