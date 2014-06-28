<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Kontroller</title>
</head>
<body>
<p>
<a href="${pageContext.request.contextPath}/admin/station/newstation">Lägg till ny kontroll</a> | <a href="${pageContext.request.contextPath}/admin">Tillbaka</a>
</p>
<table>
		<tr>
			<th>Nr</th>
			<th>Namn</th>
			<th>Ansvarig</th>
			<th>Telefon</th>
		</tr>

		<c:forEach items="${stations}" var="station">
<tr> 
<td>${station.stationNumber }</td>
<td>${station.stationName }</td>
<td>${station.stationContact }</td>
<td>${station.stationPhonenumber }</td>
<td><a href="${pageContext.request.contextPath}/admin/station/edit/${station.stationId}">Redigera</a></td>
<td><a href="${pageContext.request.contextPath}/admin/station/delete/${station.stationId}">Ta bort</a></td>
</tr>
</c:forEach>
</table>
</body>
</html>