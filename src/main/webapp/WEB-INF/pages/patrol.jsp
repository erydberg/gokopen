<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Patrull - ${patrol.patrolName }</title>
</head>
<body>
<h1>Patrull</h1>
<form:form commandName="patrol" method="post" action="/gokopen/patrol" cssClass="form-general">
<form:hidden path="patrolId" id="patrolId"/>
<div class="form-box">
<fieldset>
<div class="text size-3">
<label>Patrullens namn:</label>
<form:input path="patrolName" id="patrolName"/>
</div>
<div class="text size-3">
<label>Klass:</label>
<form:select path="track" id="track">
<form:options items="${tracks}" itemLabel="trackName"/>
</form:select>
</div>
<div class="text size-3">
<label>Kår</label>
<form:input path="troop" id="troop"/>
</div>
<div class="text size-3">
<label>Starttid: </label>
<form:input path="startTime" id="startTime"/>
</div>
<div class="text size-3">
<label>Sluttid: </label>
<form:input path="endTime" id="endTime"/>
</div>
<div class="text size-3">
<label>Medlemmar: </label>
<form:textarea path="members" rows="7" cols="30" />
</div>
<div class="text size-3">
<label>Ledare: </label>
<form:input path="leaderContact" id="leaderContact"/>
</div>

<label>Anteckning: </label>
<form:textarea path="note" rows="7" cols="30" />
</fieldset>
<div class="submit-area">
<input type="submit" name="savePatrol" value="Spara"/> | <a href="/gokopen/patrol">Avbryt</a>
</div>
</div>
</form:form>
<div class="form-box">
<h2>Poäng</h2>
<h3>Totalpoäng</h3>
<p>Totalpoäng: ${patrol.totalScore }</p> 
(Poäng: ${patrol.totalScorePoint } + stilpoäng: ${patrol.totalStylePoint })
</div>
<div class="form-box">
<table>
<tr>
<th>Kontroll</th>
<th>Poäng</th>
<th>Stilpoäng</th>
</tr>
<c:forEach items="${patrol.scores}" var="score">
 <tr>
 <td>${score.station.stationNumber}. ${score.station.stationName }</td>
 <td>${score.scorePoint }</td>
 <td>${score.stylePoint }</td>
 </tr>
</c:forEach>
</table>
</div>
</body>
</html>