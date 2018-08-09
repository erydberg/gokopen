<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${config.name }</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
</head>
<body>

<div class="nav-box">
<c:if test="${not empty errormsg }">
<div class="errorblock">
${errormsg}
</div>
</c:if>
<span class="toolbar">
			<a class="toolitem" href="${pageContext.request.contextPath}/admin/"><img src="${pageContext.request.contextPath}/css/back2.png"></a>
		</span>
		<div class="page-head">
<h1>Skriva ut</h1>
</div>
<ul>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_STARTFINISH')">
<li class="nav-item"><a href="${pageContext.request.contextPath}/print/stationscorecards">Kontrollistor</a><br>Skriver ut listor där kontrollerna ska fylla i patrullernas poäng. En backupåtgärd ifall inrapporteringen slutar fungera.</li>
<li class="nav-item"><a href="${pageContext.request.contextPath}/print/patrolscorecards">Kompletta startkort för patruller</a><br>Skriver ut startkort till alla patruller, sorterade i bokstavsordning. Startkorten innehåller patrullinformation samt kolumner för att fylla i poäng på alla kontroller. Testa att skriv ut 2 sidor på 1 sida för att få A5-format på startkorten.</li>
<li class="nav-item"><a href="${pageContext.request.contextPath}/print/smallpatrolcards">Små startkort för patruller</a><br>Skriver ut startkort till alla patruller, sorterade i bokstavsordning. Startkorten innehåller patrullinformation samt små "kryssrutor" för kontrollerna. Inte meningen att fylla i poängen i dessa rutor.</li>
<li class="nav-item"><a href="${pageContext.request.contextPath}/print/patrolstartonstation">Startlista per kontroll</a><br>Skriver ut en lista med alla patruller som ska starta på respektive kontroll.</li>
</sec:authorize>
</ul>
</div>
</body>
</html>