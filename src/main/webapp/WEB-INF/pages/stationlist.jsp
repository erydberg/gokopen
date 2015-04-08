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
<c:if test="${not empty errormsg }">
<div class="errorblock">
${errormsg}
</div>
</c:if>
<div class="nav-box">
<a href="${pageContext.request.contextPath}/admin/station/newstation">Lägg till ny kontroll</a> | <a href="${pageContext.request.contextPath}/admin">Tillbaka</a>
</div>
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
<td><a href="${pageContext.request.contextPath}/admin/station/edit/${station.stationId}">${station.stationName }</a></td>
<td>${station.stationContact }</td>
<td>${station.stationPhonenumber }</td>
<td><a href="${pageContext.request.contextPath}/admin/station/delete/${station.stationId}"><img src="${pageContext.request.contextPath}/css/delete.png"></a></td>
</tr>
</c:forEach>
</table>
</body>
</html>