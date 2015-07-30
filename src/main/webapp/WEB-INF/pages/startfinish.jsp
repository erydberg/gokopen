<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Start och målgång</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
</head>
<body>
	<div class="nav-box">
		<c:if test="${not empty errormsg }">
			<div class="errorblock">${errormsg}</div>
		</c:if>

		<h1>Start och målgång</h1>
		<table>
			<tr>
				<th>Status <a href="${pageContext.request.contextPath}/startfinish/sortbystatus"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
				<th>Patrull <a href="${pageContext.request.contextPath}/startfinish/"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
				<th>Klass <a href="${pageContext.request.contextPath}/startfinish/sortbytrack"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
				<th>Kår <a href="${pageContext.request.contextPath}/startfinish/sortbytroop"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
				<th>Antal kontroller <a href="${pageContext.request.contextPath}/startfinish/sortbycompletedstations"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
				<th>Antal poäng <a href="${pageContext.request.contextPath}/startfinish/sortbyscore"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
			</tr>
			<c:forEach items="${patrols }" var="patrol" varStatus="status">
			<c:set var='trclass' value=''></c:set>
			<c:choose>
 				<c:when test="${(status.index)%2 eq 1}"><c:set var='trclass' value='class="odd"'></c:set></c:when>
 			</c:choose>
			<tr ${trclass}>
					<c:if test="${patrol.status=='REGISTERED' }">
					<td class="center">
						<a href="${pageContext.request.contextPath}/startfinish/movetoactive/${patrol.patrolId}"><img src="${pageContext.request.contextPath}/css/waiting.png" alt="Starta patrullen"></a>
					</td>
					</c:if>
					<c:if test="${patrol.status=='ACTIVE' }">
					<td class="center">
						<a href="${pageContext.request.contextPath}/startfinish/movetofinished/${patrol.patrolId}"><img src="${pageContext.request.contextPath}/css/walking.png" alt="Gå i mål"></a>
					</td>
					</c:if>
					<c:if test="${patrol.status=='FINISHED' }">
					<td class="center">
						<a href="${pageContext.request.contextPath}/startfinish/movetoresigned/${patrol.patrolId}"><img src="${pageContext.request.contextPath}/css/finished.png" alt="Avboka patrullen"></a>
					</td>
					</c:if>
					<c:if test="${patrol.status=='RESIGNED' }">
					<td class="center">
						<a href="${pageContext.request.contextPath}/startfinish/movetoregistered/${patrol.patrolId}"><img src="${pageContext.request.contextPath}/css/canceled.png" alt="Aktivera patrullen"></a>
					</td>
					</c:if>
					<c:if test="${empty patrol.status }">
						<td class="center">
							<a href="${pageContext.request.contextPath}/startfinish/movetoactive/${patrol.patrolId}"><img src="${pageContext.request.contextPath}/css/waiting.png" alt="Starta patrullen"></a>
						</td>
					</c:if>
					<td><a href="">${patrol.patrolName }</a></td>
					<td>${patrol.track.trackName }</td>
					<td>${patrol.troop }</td>
					<td>${patrol.totalReportedStations}</td>
					<td>${patrol.totalScore} (${patrol.totalScorePoint}+${patrol.totalStylePoint})</td>
				</tr>
			</c:forEach>
		</table>

	</div>
	<div>
		<a href="${pageContext.request.contextPath}/">Tillbaka</a>
	</div>
</body>
</html>