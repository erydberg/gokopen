<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<c:set var="rubrik" value="Ny patrull"/>
<c:if test="${not empty patrol.patrolId }">
	<c:set var="rubrik" value="Patrull - "/>
</c:if>

<title>${rubrik} ${patrol.patrolName }</title>
</head>
<body>
	<div class="nav-box">
		<div class="page-head">
			<h1>${rubrik} ${patrol.patrolName}</h1>
		<c:if test="${not empty errormsg }">
				<div class="errorblock">${errormsg}</div>
			</c:if>
			<c:if test="${not empty confirmmsg }">
				<div class="confirmblock">${confirmmsg}</div>
			</c:if>
		</div>
	</div>
	<form:form commandName="patrol" method="post"
		action="${pageContext.request.contextPath}/patrol"
		cssClass="form-general">
		<form:hidden path="patrolId" id="patrolId" />
		<div class="form-box">
			<fieldset>
				<div class="text size-3">
					<label>Patrullens namn:</label>
					<form:input path="patrolName" id="patrolName" />
					<form:errors path="patrolName" cssClass="errortext" />
					<br>
					<small>Patrullnummer: ${patrol.patrolId }</small>
				</div>
				<div class="text size-3">
					<label>Eventuellt externt id:</label>
					<form:input path="externalId" id="externalId" />
				</div>
				<div class="text size-3">
					<label>Klass:</label>
					<form:select path="track" id="track">
						<form:options items="${tracks}" itemLabel="trackName" />
					</form:select>
				</div>
				<div class="text size-3">
					<label>Kår</label>
					<form:input path="troop" id="troop" />
					<form:errors path="troop" cssClass="errortext" />
				</div>

				<div class="text size-3">
					<label>Startar på kontroll:</label>
					<form:select path="startStation">
						<option value="0" label="Välj kontroll" />
						<form:options items="${stations}" itemLabel="stationName" />
					</form:select>
				</div>

				<div class="text size-3">
					<label>Starttid: </label>
					<form:input path="startTime" id="startTime" />
				</div>
				<div class="text size-3">
					<label>Sluttid: </label>
					<form:input path="endTime" id="endTime" />
				</div>
				<div class="text size-3">
					<label>Medlemmar: </label>
					<form:textarea path="members" rows="7" cols="30" />
				</div>
				<div class="text size-3">
					<label>Ledare: </label>
					<form:input path="leaderContact" id="leaderContact" />
					<form:errors path="leaderContact" cssClass="errortext" />
				</div>
				<div class="text size-3">
					<label>E-post till ledare: </label>
					<form:input path="leaderContactMail" id="leaderContactMail" />
					<form:errors path="leaderContactMail" cssClass="errortext" />
				</div>
				<div class="text size-3">
					<label>Mobiltelefonnummer till ledare: </label>
					<form:input path="leaderContactPhone" id="leaderContactPhone" />
					<form:errors path="leaderContactPhone" cssClass="errortext" />
				</div>
				<div>
					<label>Betalt:</label>
					<form:checkbox path="paid" id="paid" />
				</div>

				<label>Anteckning: </label>
				<form:textarea path="note" rows="7" cols="30" />
				<form:errors path="patrolName" cssClass="errortext" />

				<div class="text size-3">
					<label>Status:</label>
					<form:select path="status" id="status">
						<form:options items="${statuslist}" />
					</form:select>
				</div>
			</fieldset>

			<div class="submit-area">
				<input type="submit" name="savePatrol" value="Spara" /> | <a
					href="${pageContext.request.contextPath}/patrol">Avbryt</a>
			</div>
		</div>
	</form:form>
	<div class="form-box">
		<h2>Poäng</h2>
		<h3>Totalpoäng</h3>
		<p>Totalpoäng: ${patrol.totalScore }</p>
		(Poäng: ${patrol.totalScorePoint } + stilpoäng:
		${patrol.totalStylePoint })
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
					<td>${score.station.stationNumber}.
						${score.station.stationName }</td>
					<td>${score.scorePoint }</td>
					<td>${score.stylePoint }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>