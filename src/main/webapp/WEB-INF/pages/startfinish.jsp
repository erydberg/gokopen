<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Start och målgång</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
</head>
<body>
	<div class="nav-box">
		<c:if test="${not empty errormsg }">
			<div class="errorblock">${errormsg}</div>
		</c:if>

		<h1>Start och målgång</h1>
		<table>
			<tr>
				<th>Status</th>
				<th>Patrull</th>
				<th>Klass</th>
				<th>Kår</th>
				<th>Antal kontroller</th>
				<th>Antal poäng</th>
			</tr>
			<c:forEach items="${patrols }" var="patrol" varStatus="status">
			<c:set var='trclass' value=''></c:set>
			<c:choose>
 				<c:when test="${(status.index)%2 eq 1}"><c:set var='trclass' value='class="odd"'></c:set></c:when>
 			</c:choose>
			<tr ${trclass}>

					<td>status</td>
					<td><a href="">${patrol.patrolName }</a></td>
					<td>${patrol.track.trackName }</td>
					<td>${patrol.troop }</td>
					<td>${patrol.totalReportedStations}</td>
					<td>${patrol.totalScore} (${patrol.totalScorePoint}+${patrol.totalStylePoint})</td>
				</tr>
			</c:forEach>
		</table>

	</div>
	<div>
		<a href="${pageContext.request.contextPath}/">Tillbaka</a>
	</div>
</body>
</html>