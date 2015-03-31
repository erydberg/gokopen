<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Lägg till eller ändra användare</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
</head>
<body>

	<div class="nav-box">
		<h1>Användare</h1>
		<form:form commandName="user" method="post" action="${pageContext.request.contextPath}/admin/user/save" cssClass="form-general">
			<form:hidden path="id" id="id" />

			<div class="form-box">
				<fieldset>
					<div class="text size-3">
						<label>Användarnamn</label>
						<form:input path="username" id="username" />
					</div>
					<div class="text size-3">
						<label>Lösenord</label>
						<form:input path="password" id="password" />
					</div>
					<div class="text size-3">
						<label>Roll (vanligtvis ROLE_USER)</label>
						<form:input path="role" id="role" />
					</div>
				</fieldset>
				<div class="submit-area">
					<input type="submit" name="saveUser" value="Spara" /> | <a
						href="${pageContext.request.contextPath}/admin/user">Avbryt</a>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>