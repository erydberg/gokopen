<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Rapportera poäng</title>
</head>
<body>
	<h1>Ändra poäng</h1>
	
<c:if test="${not empty score.station }">
	<form:form commandName="score" method="post" action="/gokopen/score/savescorefrompatrol" cssClass="form-general">
	<form:hidden path="scoreId" id="scoreId" />
Kontroll: ${score.station.stationName }<br>
Patrull: ${score.patrol.patrolName }<br/>
<form:hidden path="patrol" id="patrol" />
<form:hidden path="station.stationId" id="station.stationId" />
<div class="form-box">
<fieldset>
<label for="scorePoint">Poäng: </label>
<form:select path="scorePoint" id="scorePoint">
<c:forEach var="j" begin="${score.station.minScore}" end="${score.station.maxScore}">
		<c:if test="${score.scorePoint==j }"><option selected="selected" value="${j}">${j}</option></c:if>
		<c:if test="${score.scorePoint!=j }"><option value="${j}">${j}</option></c:if>
</c:forEach>
</form:select>
</fieldset>
</div>
<div class="form-box">
<fieldset>
<label for="stylePoint">Stilpoäng: </label>
<form:select path="stylePoint" id="stylePoint">
	<c:forEach var="i" begin="${score.station.minStyleScore}" end="${score.station.maxStyleScore}">
		<c:if test="${score.stylePoint==i }"><option selected="selected" value="${i}">${i}</option></c:if>
		<c:if test="${score.stylePoint!=i }"><option value="${i}">${i}</option></c:if>
	</c:forEach>
</form:select>
</fieldset>
</div>
<div class="submit-area">
<input type="submit" name="saveScore" value="Spara"/> | <a href="${backurl}">Avbryt</a>
</div>
</form:form>
</c:if>
<br/><br/><br/>
<div class="form-box">
<a href="/gokopen/score/delete/${score.scoreId}/patrolid/${score.patrol.patrolId}">Ta bort</a>
</div>
</body>
</html>