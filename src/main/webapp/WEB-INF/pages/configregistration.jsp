<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="sv">
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Konfiguration</title>
</head>
<body>
	<div class="nav-box">
		<div class="page-head">
			<h1>Konfiguration för självregistring</h1>
		</div>

		<c:if test="${not empty errormsg }">
			<div class="ingress_background">
				<div class="ingress">
					<div class="errorblock">${errormsg}</div>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty confirmmsg }">
			<div class="ingress_background">
				<div class="ingress">
					<div class="confirmblock">${confirmmsg}</div>
				</div>
			</div>
		</c:if>
	</div>
	</div>
	<form:form commandName="configregistration" method="post"
		action="${pageContext.request.contextPath}/admin/configregistration"
		cssClass="form-general">
		<form:hidden path="id" id="id" />
		<div class="form-box">
			<fieldset class="form-box">
				<div>
					<form:label path="allowPublicRegistration">Aktivera publik registrering av patruller</form:label>
					<form:checkbox path="allowPublicRegistration" />
				</div>
				<div class="text size-3">
					<form:label path="firstRegisterDay">Första anmälningsdag (startar vid midnatt) (YYYY-MM-DD) (behöver finnas om publik registrering ska kunna visas)</form:label>
					<form:input path="firstRegisterDay" />
				</div>
				<div class="text size-3">
					<form:label path="lastRegisterDay">Sista anmälningsdag (YYYY-MM-DD) (behöver finnas om publik registrering ska kunna visas)</form:label>
					<form:input path="lastRegisterDay" />
				</div>
				<div class="text size-3">
					<form:label path="maxPatrols">Max antal patruller</form:label>
					<form:input path="maxPatrols" />
				</div>
				<div class="text size-3">
					<form:label path="registerInfo">Informationstext på registreringssidan</form:label>
					<form:textarea path="registerInfo" rows="7" cols="30" />
				</div>
				<div class="text size-3">
					<form:label path="confirmMessage">Text till sidan som visas när någon anmält en patrull</form:label>
					<form:textarea path="confirmMessage" rows="7" cols="30" />
				</div>
				<div class="text size-3">
					<form:label path="registrationNotOpen">Text till sidan som visas när anmälan inte är öppen, till exempel om det är fullt.</form:label>
					<form:textarea path="registrationNotOpen" rows="7" cols="30" />
				</div>

			</fieldset>
		</div>
		<div class="submit-area">
			<input type="submit" name="saveConfig" value="Spara" /> | <a
				href="${pageContext.request.contextPath}/admin">Tillbaka</a>
		</div>
	</form:form>
	</div>
</body>
</html>