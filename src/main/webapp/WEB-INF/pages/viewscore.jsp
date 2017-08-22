<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Visa poäng</title>
</head>
<body>
	<h3>Poäng för ${score.patrol.patrolName } för kontroll ${score.station.stationName } </h3>
	var
	<c:if test="${score.visitedWaypoint }">
		Passerat: <fmt:formatDate pattern="yyyy-MM-dd H:mm" value="${score.lastSaved}" />
	</c:if>
	<c:if test="${not score.visitedWaypoint }">
		<p>Poäng: ${score.scorePoint }</p>	
		<p>Stilpoäng: ${score.stylePoint }</p>
	</c:if>
</body>
</html>