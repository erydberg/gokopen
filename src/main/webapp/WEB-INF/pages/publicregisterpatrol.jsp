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
	<h1>Anmäl patrull till ${config.name }</h1>
	Fyll i uppgifterna nedan. Sista anmälningsdag är DATUM. Max antal
	patruller är: YYY Redan anmälda patruller är: PPP

<h2>${status }</h2>
	<h2>Uppgifter</h2>
	<form:form commandName="patrol" method="post"
		action="${pageContext.request.contextPath}/register"
		modelAttribute="patrol">
		<form:hidden path="patrolId" id="patrolId" />
		<form:label path="patrolName">Patrullens namn:</form:label>
		<form:input path="patrolName" />

		<form:label path="troop">Scoutkår</form:label>
		<form:input path="troop" />

		<form:label path="track">Klass:</form:label>
		<form:select path="track">
			<form:options items="${tracks}" itemLabel="trackName" />
		</form:select>

		
<input type="submit" value="Submit"/>
	</form:form>
</body>
</html>