<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Bekräftelse anmäld patrull till ${config.name }</title>
</head>
<body>
	<div class="nav-box">
		<div class="page-head">
			<h1>Patrull ${patrol.patrolName} anmäld till ${config.name }</h1>
		</div>
	</div>
	<div class="ingress">
		<c:if test="${not empty errormsg }">
			<div class="errorblock">${errormsg}</div>
		</c:if>
		<c:if test="${not empty confirmmsg }">
			<div class="confirmblock">${confirmmsg}</div>
		</c:if>
		<h2>Anmälningsbekräftelse</h2>
		<p>
		Patrullnamn: ${patrol.patrolName }<br/>
		Patrullnummer: ${patrol.patrolId }<br/>
		Klass/gren: ${patrol.track.trackName}<br/>
		Scoutkår: ${patrol.troop }<br/>
		Kontaktperson: ${patrol.leaderContact }<br/>
		Telefon: ${patrol.leaderContactPhone }<br/>
		E-post: ${patrol.leaderContactMail }<br/>
		</p>
		<p>${config.confirmMessage }</p>
		
		<a href="${pageContext.request.contextPath}/register">Klicka här
			om du vill anmäla en patrull till.</a>
	</div>

</body>
</html>