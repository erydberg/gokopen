<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<script>
	var path = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/gokopen.js"></script>
<script>
	<c:if test="${not empty alertmsg }">
	var msg = '${alertmsg }';
	</c:if>

	$(document).ready(function() {
		if (typeof msg !== 'undefined') {
			alert(msg);
		}
	});
</script>

<title>Korrigera poäng</title>
</head>
<body>
	<c:if test="${not empty msg }">
		<div class="statusrow">${msg }</div>
	</c:if>
	<div class="nav-box">
		<c:if test="${not empty errormsg }">
			<div class="errorblock">${errormsg}</div>
		</c:if>
		<span class="toolbar"> <a class="toolitem"
			href="${pageContext.request.contextPath}/startmenu"><img
				src="${pageContext.request.contextPath}/css/back2.png"></a>
		</span>

		<h1>Ändra poäng</h1>
		<form:form commandName="selectedstation" method="GET"
			action="${pageContext.request.contextPath}/correctscore/selectstation"
			cssClass="form-general">
			<div class="form-box">
				<fieldset>
					<label for="stations">Välj kontroll:</label>

					<form:select path="stationId" id="stationId"
						onChange="this.form.submit()">
						<option value="-1">-- Välj kontroll --</option>
						<form:options items="${stations}" itemLabel="stationName" />
					</form:select>
				</fieldset>
			</div>
		</form:form>
	</div>
	<c:if test="${not empty scores }">
		<h3>${fn:length(scores)}rapporterade poäng</h3>
		<small>Sorteras med senaste poängen överst</small>
		<c:forEach items="${scores }" var="score">
			<div class="scoreitem">

				<c:if test="${not empty score.patrol}">
					<a
						href="${pageContext.request.contextPath}/correctscore/edit/${score.scoreId}">${score.patrol.patrolName }</a> (${score.patrol.track.trackName})<br>
					<small>Patrullnummer ${score.patrol.patrolId}</small>
					<br>${score.patrol.troop}<br>
				</c:if>

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
	</c:if>
	<div></div>
</body>
</html>