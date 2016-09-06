<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Skriv ut per kontroll och vald gren</title>
</head>
<body>
<c:forEach items="${stations}" var="station">
	<div class="print">
	<h1>Poängkort för ${station.stationName } - ${selectedTrack }</h1>
	<table>
	<tr>
		<th>Patrull</th>
		<th class="printtd">Poäng</th>
		<th class="printtd">Stilp</th>
		<th class="printtd">Totalt</th>
		<th class="printtd">Signatur</th>
	</tr>
	<c:forEach items="${patrols }" var="patrol">
	<tr>
		<td>${patrol.patrolName }<br><small>Patrullnr: ${patrol.patrolId}</small><br/><small>${patrol.troop }</small></td>
		<td class="printtd"> </td>
		<td class="printtd"> </td>
		<td class="printtd"> </td>
		<td class="printtd"> </td>
	</tr>
	</c:forEach>
	</table>
	</div>
</c:forEach>
</body>
</html>