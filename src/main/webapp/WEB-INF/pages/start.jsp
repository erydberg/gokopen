<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Gök Open</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
</head>
<body>

<div class="nav-box">
<h1>Gök Open</h1>
<ul>
<li class="nav-item"><a href="/gokopen/score/">Rapportera poäng</a></li>
<li class="nav-item"><a href="/gokopen/reports/patrols">Patruller</a></li>
<li class="nav-item"><a href="/gokopen/reports/bytrack">Resultat per klass</a></li>
<li class="nav-item"><a href="/gokopen/print/start">Skriv ut listor</a></li>
<li class="nav-item"><a href="/gokopen/admin/">Administration</a></li>
</ul>
Inloggad användare: ${username }
</div>
</body>
</html>