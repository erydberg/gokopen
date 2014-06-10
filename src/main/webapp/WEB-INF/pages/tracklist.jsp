<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Klasser</title>
</head>
<body>
<p>
<a href="/gokopen/admin/track/newtrack">Lägg till ny klass</a> | <a href="/gokopen/admin">Tillbaka</a>
</p>
<table>
		<tr>
			<th>Klass</th>
		</tr>

		<c:forEach items="${tracks}" var="track">
<tr> 
<td>${track.trackName }</td>
<td><a href="/gokopen/admin/track/edit/${track.trackId}">Redigera</a></td>
<td><a href="/gokopen/admin/track/delete/${track.trackId}">Ta bort</a></td>
</tr>
</c:forEach>
</table>
</body>
</html>