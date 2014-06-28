<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Konfiguration</title>
</head>
<body>
<h1>Appens konfiguration</h1>
<form:form commandName="config" method="post" action="${pageContext.request.contextPath}/admin/config">
<form:hidden path="id" id="id"/>
<label for="name">Tävlingens namn:</label>
<form:input path="name" id="name"/>
<p>
<input type="submit" name="saveConfig" value="Spara"/> | <a href="${pageContext.request.contextPath}/admin">Tillbaka</a>
</p>
</form:form>
</body>
</html>