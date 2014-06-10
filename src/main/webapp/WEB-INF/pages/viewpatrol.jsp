<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</div>
<c:if test="${not empty errormsg }">
<div class="errorblock">
${errormsg}
</div>
</c:if>
	<h1>${patrol.patrolName }</h1>
	<div class="form-box">
		<p>${patrol.track.trackName } - ${patrol.troop }</p>
		<p>Ledarkontakt:
		${patrol.leaderContact }</p>
		<p>Starttid: ${patrol.startTime }<br>
		Sluttid: ${patrol.endTime }</p>
		<p>Medlemmar:<br>
		${patrol.members }</p>
</div>
	<div class="form-box">
	<h2>Poäng</h2>
<p>Totalpoäng: ${patrol.totalScore }</p> 
(Poäng: ${patrol.totalScorePoint } + stilpoäng: ${patrol.totalStylePoint })

<div>
<c:forEach items="${patrol.scores}" var="score">
<div class="scoreitem">
	<a href="/gokopen/score/editscorefrompatrol/${score.scoreId}/returnto/${patrol.patrolId}">${score.station.stationNumber}. ${score.station.stationName}</a><br/>
	Poäng: ${score.scorePoint }<br/>
	Stilpoäng: ${score.stylePoint }
</div>
</c:forEach>
</div>
</div>
</body>
</html>
