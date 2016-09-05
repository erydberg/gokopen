<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Ändra poäng</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
</head>
<body>
<c:if test="${not empty msg }">
<div class="statusrow">
${msg }
</div>
</c:if>
	<div class="nav-box">
		<c:if test="${not empty errormsg }">
			<div class="errorblock">${errormsg}</div>
		</c:if>
		<h1>Ändra poäng för ${score.patrol.patrolName }</h1>
		<form:form commandName="score" method="post"
			action="${pageContext.request.contextPath}/correctscore/save"
			cssClass="form-general">
			<form:hidden path="scoreId" id="scoreId" />
			<form:hidden path="patrol.patrolId" id="patrol.patrolId" />
			<form:hidden path="station.stationId" id="station.stationId" />

			<div class="form-box">
				<p>Patrullnummer: ${score.patrol.patrolId }<br>
				${score.patrol.track.trackName }<br>
				${score.patrol.troop}<br>
				</p>
				<h3>Befintliga poäng för ${score.station.stationName }</h3>
				<small>Sparat: <fmt:formatDate pattern="yyyy-MM-dd H:mm" value="${score.lastSaved}" /></small>
				<div class="form-box">
					<fieldset>
						<label for="scorePoint">Poäng: </label>
						<form:select path="scorePoint" id="scorePoint">
							<c:forEach var="j" begin="${score.station.minScore}"
								end="${score.station.maxScore}">
								<c:if test="${score.scorePoint==j }">
									<option selected="selected" value="${j}">${j}</option>
								</c:if>
								<c:if test="${score.scorePoint!=j }">
									<option value="${j}">${j}</option>
								</c:if>
							</c:forEach>
						</form:select>
					</fieldset>
				</div>
				<div class="form-box">
					<fieldset>
						<label for="stylePoint">Stilpoäng: </label>
						<form:select path="stylePoint" id="stylePoint">
							<c:forEach var="i" begin="${score.station.minStyleScore}"
								end="${score.station.maxStyleScore}">
								<c:if test="${score.stylePoint==i }">
									<option selected="selected" value="${i}">${i}</option>
								</c:if>
								<c:if test="${score.stylePoint!=i }">
									<option value="${i}">${i}</option>
								</c:if>
							</c:forEach>
						</form:select>
					</fieldset>
				</div>

				<div class="submit-area">
					<input type="submit" name="savescore" value="Spara" /> | <a
						href="${pageContext.request.contextPath}/correctscore/selectstation?stationId=${score.station.stationId}">Avbryt</a>
						
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>