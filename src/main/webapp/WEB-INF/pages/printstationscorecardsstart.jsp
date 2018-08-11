<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Skriv ut poängkort per kontroll</title>
</head>
<body>
		<div class="nav-box">
		<a href="${pageContext.request.contextPath}/startmenu/">Tillbaka</a>
		<ul>
		<c:forEach items="${tracks }" var="track">
			<li class="nav-item-small"><a href="${pageContext.request.contextPath}/print/bytrack/${track.trackId }">${track.trackName }</a></li> 
		</c:forEach>
		</ul>
		</div>
</body>
</html>