<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>  
<html>
<head>
<title>Start och målgång</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<script>
	var path = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/gokopen.js"></script>

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
		<h1>Start och målgång</h1>
		<c:if test="${not empty patrols }">
			${fn:length(patrols)} patruller
		</c:if>
		</div>
		<div class="switchstatus">
		<table>
			<tr>
				<th>Status <a href="${pageContext.request.contextPath}/startfinish/sortbystatus"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
				<th>Patrull <a href="${pageContext.request.contextPath}/startfinish/"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
				<th>Klass <a href="${pageContext.request.contextPath}/startfinish/sortbytrack"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
				<th>Kår <a href="${pageContext.request.contextPath}/startfinish/sortbytroop"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
				<th>Antal kontroller <a href="${pageContext.request.contextPath}/startfinish/sortbycompletedstations"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
				<th>Antal poäng <a href="${pageContext.request.contextPath}/startfinish/sortbyscore"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
				<th>Senast inrapporterat</th>
			</tr>
			<c:forEach items="${patrols }" var="patrol" varStatus="status">
			<c:set var='trclass' value=''></c:set>
			<c:choose>
 				<c:when test="${(status.index)%2 eq 1}"><c:set var='trclass' value='class="odd"'></c:set></c:when>
 			</c:choose>
			<tr ${trclass}>
					<c:if test="${patrol.status=='REGISTERED' }">
					<td class="patrolstatus">
						<img class="patrol" data-status="registered" data-id="${patrol.patrolId}" src="${pageContext.request.contextPath}/css/registered.png"/>
					</td>
					</c:if>
					<c:if test="${patrol.status=='ACTIVE' }">
					<td class="patrolstatus">
						<img class="patrol" data-status="active" data-id="${patrol.patrolId}" src="${pageContext.request.contextPath}/css/active.png"/>
					</td>
					</c:if>
					<c:if test="${patrol.status=='FINISHED' }">
					<td class="patrolstatus">
						<img class="patrol" data-status="finished" data-id="${patrol.patrolId}" src="${pageContext.request.contextPath}/css/finished.png"/>
					</td>
					</c:if>
					<c:if test="${patrol.status=='RESIGNED' }">
					<td class="patrolstatus">
						<img class="patrol" data-status="resigned" data-id="${patrol.patrolId}" src="${pageContext.request.contextPath}/css/resigned.png"/>
					</td>
					</c:if>
					<c:if test="${empty patrol.status }">
						<td class="patrolstatus"> 
							<img class="patrol" data-status="registered" data-id="${patrol.patrolId}" src="${pageContext.request.contextPath}/css/registered.png"/>
						</td>
					</c:if>
					<td><a href="${pageContext.request.contextPath}/startfinish/viewpatrol/${patrol.patrolId}">${patrol.patrolName }</a> <br><small>(Patrullnr: ${patrol.patrolId }. 
							<c:if test="${not empty patrol.startStation }">Start: ${patrol.startStation.stationNumber}. ${patrol.startStation.stationName} </c:if>)</small></td>
					<td>${patrol.track.trackName }</td>
					<td>${patrol.troop }</td>
					<td>${patrol.totalReportedStations}</td>
					<td>${patrol.totalScore} (${patrol.totalScorePoint}+${patrol.totalStylePoint})</td>
					<td>
					<fmt:formatDate value="${patrol.latestScore.lastSaved }" pattern="yyyy" var="dummyYear" />
					<c:if test="${dummyYear gt 2014}"><fmt:formatDate pattern="HH:mm (d/M)" value="${patrol.latestScore.lastSaved }" /> ${patrol.latestScore.station.stationName }</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<a href="${pageContext.request.contextPath}/startmenu">Tillbaka</a>
	</div>
</body>
</html>