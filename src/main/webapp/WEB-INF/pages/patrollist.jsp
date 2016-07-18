<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Patruller</title>
</head>
<body>
	<p>
		<a href="${pageContext.request.contextPath}/patrol/admin/newpatrol">Lägg
			till ny patrull</a> | <a href="${pageContext.request.contextPath}/admin">Tillbaka</a>
	</p>
	<table>
		<tr>
			<th>Patrullnamn</th>
			<th>Klass</th>
			<th>Kår</th>
			<th>Kontakt</th>
			<th>Betalt</th>
		</tr>

		<c:forEach items="${patrols}" var="patrol" varStatus="status">
		<c:set var='trclass' value=''></c:set>
		<c:choose>
 			<c:when test="${(status.index)%2 eq 1}"><c:set var='trclass' value='class="odd"'></c:set></c:when>
 		</c:choose>
			<tr ${trclass}>
				<td><a
					href="${pageContext.request.contextPath}/patrol/viewpatrol/${patrol.patrolId}">${patrol.patrolName }</a></td>
				<td>${patrol.track.trackName }</td>
				<td>${patrol.troop }</td>
				<td>${patrol.leaderContact}</td>
				<td>
				<c:if test="${patrol.paid }">ja</c:if>
				</td>
				<td><a
					href="${pageContext.request.contextPath}/patrol/admin/edit/${patrol.patrolId}">Redigera</a></td>
				<td><a
					href="${pageContext.request.contextPath}/patrol/admin/delete/${patrol.patrolId}">Ta
						bort</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>