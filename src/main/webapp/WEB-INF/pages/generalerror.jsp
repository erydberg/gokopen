<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Fel ${config.name }</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
</head>
<body>

<div class="nav-box">
<c:if test="${not empty errormsg }">
<div class="errorblock">
${errormsg}
</div>
</c:if>
<h1>Inte bra, det blev fel någonstans</h1>
<p>Någonstans blev det fel i systemet.</p>
</div>
</body>
</html>