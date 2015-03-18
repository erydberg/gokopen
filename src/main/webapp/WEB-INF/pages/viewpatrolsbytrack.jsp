<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Visa resultat per klass</title>
</head>
<body>
		<div class="nav-box">
		<a href="${pageContext.request.contextPath}/">Tillbaka</a>
		<ul>
		<c:forEach items="${tracks }" var="track">
			<li class="nav-item-small"><a href="${pageContext.request.contextPath}/reports/bytrack/${track.trackId }">${track.trackName }</a></li> 
		</c:forEach>
		</ul>
		</div>
	
	<c:if test="${not empty selectedTrack }">
		<h2>Resultatlista ${selectedTrack }</h2>
	</c:if>
	
	<c:if test="${not empty patrols }">
	<c:forEach items="${patrols }" var="patrol" varStatus="status">
	<div class="scoreitem">
	<a href="${pageContext.request.contextPath}/patrol/viewpatrolfromlisttrack/${patrol.patrolId}/track/${trackid}">${status.count }. ${patrol.patrolName }</a><br/>
	${patrol.troop }<br/>
	<strong>Totalt: ${patrol.totalScore }</strong><br/>
	(${patrol.totalScorePoint } poäng + ${patrol.totalStylePoint} stilpoäng)<br/>
	Antal stationer: ${patrol.totalReportedStations }
	</div>
	</c:forEach>
	</c:if>
</body>
</html>