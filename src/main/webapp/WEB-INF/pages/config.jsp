<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Konfiguration</title>
</head>
<body>
	<div class="nav-box">
		<h1>Appens konfiguration</h1>
		<form:form commandName="config" method="post"
			action="${pageContext.request.contextPath}/admin/config">
			<form:hidden path="id" id="id" />
			<div class="form-box">
				<fieldset>
					<label for="name">Tävlingens namn:</label>
					<form:input path="name" id="name" />
				</fieldset>
			</div>
			<div class="submit-area">
				<input type="submit" name="saveConfig" value="Spara" /> | <a href="${pageContext.request.contextPath}/admin">Tillbaka</a>
			</div>
		</form:form>
	</div>
</body>
</html>