<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${config.name }admin</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
</head>
<body>
	<div class="nav-box">
		<div class="page-head">
			<h1>${config.name }administration</h1>
		</div>
	</div>
	<c:if test="${not empty errormsg }">
		<div class="ingress_background">
			<div class="ingress">

				<div class="errorblock">${errormsg}</div>
			</div>
		</div>
	</c:if>
	<c:if test="${not empty confirmmsg }">
		<div class="ingress_background">
			<div class="ingress">
				<div class="confirmblock">${confirmmsg}</div>
			</div>
		</div>
	</c:if>
	</div>
	</div>
	<div class="nav-box">
		<ul>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/patrol">Patruller</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/admin/station">Kontroller</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/admin/distribute">Fördela
					patruller på startkontroller</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/admin/track">Klasser</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/admin/user">Användare</a>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/print">Skriv ut listor</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/admin/config">Konfiguration</a>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/startmenu">Till
					huvudmenyn</a>
		</ul>
	</div>
</body>
</html>