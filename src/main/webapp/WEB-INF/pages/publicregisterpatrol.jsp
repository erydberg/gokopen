<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Anmäl patrull till ${config.name }</title>
</head>
<body>
<div class="nav-box">
			<div class="page-head">
	<h1>Anmäl patrull till ${config.name }</h1>
	</div>
	</div>
	<div class="ingress_background">
	<div class="ingress">
	<c:if test="${not empty errormsg }">
		<div class="errorblock">${errormsg}</div>
	</c:if>
	<c:if test="${not empty confirmmsg }">
		<div class="confirmblock">${confirmmsg}</div>
	</c:if>

	Fyll i uppgifterna nedan. Sista anmälningsdag är DATUM. Max antal
	patruller är: YYY Redan anmälda patruller är: PPP är jag här

	<h2>${status }</h2>
</div> 
</div>
	<form:form commandName="patrol" method="post"
		action="${pageContext.request.contextPath}/register"
		cssClass="form-general">
		<form:hidden path="patrolId" id="patrolId" />
		<div class="form-box">
			<fieldset>
				<div class="text size-3">
					<form:label path="patrolName">Patrullens namn:</form:label>
					<form:input path="patrolName" />
					<form:errors path="patrolName" cssClass="errortext" />
				</div>
				<div class="text size-3">
					<form:label path="troop">Scoutkår</form:label>
					<form:input path="troop" />
				</div>
				<div class="text size-3">
					<form:label path="track">Klass:</form:label>
					<form:select path="track">
						<form:options items="${tracks}" itemLabel="trackName" />
					</form:select>
				</div>
				<div class="text size-3">
					<form:label path="leaderContact">Kontaktperson (ledare)</form:label>
					<form:input path="leaderContact" />
					<form:errors path="leaderContact" cssClass="errortext" />
				</div>
				<div class="text size-4">
					<form:label path="leaderContactMail">E-post till kontaktperson</form:label>
					<form:input path="leaderContactMail" />
					<form:errors path="leaderContactMail" cssClass="errortext" />
				</div>
				<div class="text size-3">
					<form:label path="leaderContactPhone">Mobiltelefonnummer till kontaktperson</form:label>
					<form:input path="leaderContactPhone" />
				</div>
				<div class="text size-3">
					<form:label path="note">Eventuell kommentar: </form:label>
					<form:textarea path="note" rows="7" cols="30" />
				</div>
		
		</fieldset>
		</div>

		<div class="submit-area">
			<input type="submit" value="Skicka in" />
		</div>
	</form:form>
</body>
</html>