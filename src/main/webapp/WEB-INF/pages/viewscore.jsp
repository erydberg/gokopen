<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Visa poäng</title>
</head>
<body>
	<h3>Poäng för ${score.patrol.patrolName } för kontroll ${score.station.stationName } </h3>
<p>Poäng: ${score.scorePoint }</p>	
<p>Stilpoäng: ${score.stylePoint }</p>

</body>
</html>