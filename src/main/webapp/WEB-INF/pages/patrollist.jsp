<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Patruller</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<script>
	var path = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/gokopen-patroladmin.js"></script>
</head>
<body>
	<p>
		<a href="${pageContext.request.contextPath}/patrol/admin/newpatrol">Lägg
			till ny patrull</a> | <a href="${pageContext.request.contextPath}/admin">Tillbaka</a>
	</p>
	<div class="switchstatus">
	<table>
		<tr>
			<th>Patrullnamn</th>
			<th>Klass</th>
			<th>Kår</th>
			<th>Kontakt</th>
			<th>Telefon</th>
			<th>Registrerad</th>
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
				<td>
					<c:if test="${not empty patrol.leaderContactMail }"><a href="mailto:${patrol.leaderContactMail}"></c:if>${patrol.leaderContact}<c:if test="${not empty patrol.leaderContactMail }"></a></c:if>
				</td>
				<td>${patrol.leaderContactPhone }</td>
				<td><fmt:formatDate pattern="yy-MM-dd HH:mm" value="${patrol.dateRegistered }" /></td>
				<c:if test="${patrol.paid }">
				<td class="paidstatus">
					
						<span class="paid-action" data-status="paid" data-id="${patrol.patrolId}">ja</span>
						</td>
					</c:if>
					
					<c:if test="${not patrol.paid }">
					<td class="paid">
						<span class="paid-action" data-status="notpaid" data-id="${patrol.patrolId}">nej</span>
					</td>
					</c:if>
				
				<td><a
					href="${pageContext.request.contextPath}/patrol/admin/edit/${patrol.patrolId}"><img src="${pageContext.request.contextPath}/css/edit2.png"/></a></td>
				<td><a
					href="${pageContext.request.contextPath}/patrol/admin/delete/${patrol.patrolId}"><img src="${pageContext.request.contextPath}/css/delete.png"/></a></td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>