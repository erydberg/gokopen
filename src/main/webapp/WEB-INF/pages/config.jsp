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
	<div class="page-head">
		<h1>Appens konfiguration</h1>
		</div>
		<form:form commandName="config" method="post" action="${pageContext.request.contextPath}/admin/config">
			<form:hidden path="id" id="id" />
			<div class="form-box">
				<fieldset>
				<div class="text size-3">
					<label for="name">Tävlingens namn:</label>
					<form:input path="name" id="name" />
				</div>
				<div class="text size-3">
					<label for="phone">Telefon till start/mål</label>
					<form:input path="phone" id="phone" />
				</div>
				<div>
		
				<label>Tillåt publik resultatsida (visar också länk från inloggningssidan):</label>
				<form:checkbox path="allowPublicResult" id="allowPublicResult"/>
				</div>
			</fieldset>
			</div>
			<div class="submit-area">
				<input type="submit" name="saveConfig" value="Spara" /> | <a href="${pageContext.request.contextPath}/admin">Tillbaka</a>
			</div>
		</form:form>
	</div>
</body>
</html>