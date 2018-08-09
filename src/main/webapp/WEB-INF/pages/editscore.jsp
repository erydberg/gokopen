<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Ändra poäng</title>
</head>
<body>
	<div class="nav-box">
		<div class="page-head">
			<h1>Ändra poäng</h1>
		</div>
	</div>
	<c:if test="${not empty score.station }">
		<form:form commandName="score" method="post"
			action="${pageContext.request.contextPath}/score/savescorefrompatrol"
			cssClass="form-general">
			<form:hidden path="scoreId" id="scoreId" />
				Kontroll: ${score.station.stationName }<br>
				Patrull: ${score.patrol.patrolName }<br>
				Sparat: <fmt:formatDate pattern="yyyy-MM-dd H:m"
				value="${score.lastSaved}" />
			<br>
			<form:hidden path="patrol" id="patrol" />
			<form:hidden path="station.stationId" id="station.stationId" />

			<jsp:include page="include_editscore.jsp"></jsp:include>

			<div class="submit-area">
				<input type="submit" name="saveScore" value="Spara" /> | <a
					href="${pageContext.request.contextPath}/score/delete/${score.scoreId}/patrolid/${score.patrol.patrolId}">Ta
					bort</a> | <a href="${backurl}">Avbryt</a>
			</div>
		</form:form>
	</c:if>
	<br />
</body>
</html>