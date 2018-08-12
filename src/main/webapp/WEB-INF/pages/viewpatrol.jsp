<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Patrull - ${patrol.patrolName }</title>
</head>
<body>
	<div class="form-general">
		<div>
			<a href="${backurl}">Tillbaka</a>
			<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_STARTFINISH')">
 | <a href="${pageContext.request.contextPath}/startfinish">Tillbaka
					till start och måladministration</a>
			</sec:authorize>
		</div>
		<c:if test="${not empty errormsg }">
			<div class="errorblock">${errormsg}</div>
		</c:if>
		<h1>${patrol.patrolName }</h1>
		<small>Patrullnummer: ${patrol.patrolId }, externt id:
			${patrol.externalId }</small>
		<div class="form-box">
			<p>${patrol.track.trackName }- ${patrol.troop }</p>
			<p>
				Ledarkontakt: ${patrol.leaderContact }<br>
				<c:if test="${not empty patrol.leaderContactPhone }">
		Telefon: ${patrol.leaderContactPhone }
		</c:if>
			<p>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_STARTFINISH')">
			E-post: <a href="mailto:${patrol.leaderContactMail}">${patrol.leaderContactMail}</a>
					<br>
				</sec:authorize>
			</p>
			<c:if test="${not empty patrol.startTime }">
		Starttid: ${patrol.startTime }<br>
			</c:if>
			<c:if test="${not empty patrol.endTime }">
		Sluttid: ${patrol.endTime }<br>
			</c:if>
			<c:if test="${not empty patrol.members }">
		Medlemmar:<br>
		${patrol.members }<br>
			</c:if>
		</div>
		<div class="form-box">
			<h2>Poäng</h2>
			Totalpoäng: ${patrol.totalScore }<br> (Poäng:
			${patrol.totalScorePoint } + stilpoäng: ${patrol.totalStylePoint })<br>
			<fmt:formatDate pattern="yyyy-MM-dd H:mm"
				value="${patrol.latestScore.lastSaved }" var="latestScoreDate" />
			<c:if test="${latestScoreDate gt '2014-01-01 0:00'}">
Senast rapporterat: ${latestScoreDate }
</c:if>
			<c:if test="${latestScoreDate le '2014-01-02 0:00'}">
	Ingen poäng rapporterad ännu
</c:if>
		</div>
		<div>
			<small>Sorterade med senaste poängen överst</small>
			<c:forEach items="${patrol.scores}" var="score">
				<div class="scoreitem">

					<a
						href="${pageContext.request.contextPath}/score/editscorefrompatrol/${score.scoreId}/returnto/${patrol.patrolId}">${score.station.stationNumber}.
						${score.station.stationName}</a><br />
					<c:if test="${score.visitedWaypoint }">
		Passerat: <fmt:formatDate pattern="yyyy-MM-dd H:mm"
							value="${score.lastSaved}" />
						<br>
					</c:if>
					<c:if test="${not score.visitedWaypoint }">
		Poäng: ${score.scorePoint }<br>
		Stilpoäng: ${score.stylePoint }<br>
		Sparat: <fmt:formatDate pattern="yyyy-MM-dd H:mm"
							value="${score.lastSaved}" />
						<br>
					</c:if>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>
