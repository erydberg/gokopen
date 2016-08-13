<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Resultat för ${config.name }</title>
</head>
<body>
	<c:if test="${not config.allowPublicResult}">
		<div class="nav-box">
			<div class="page-head">
				<p>Tävlingsledningen har slagit av möjligheten att titta på
					resultatet utan att logga in.</p>
			</div>
		</div>
	</c:if>

	<c:if test="${config.allowPublicResult}">
		<div class="nav-box">
			<div class="page-head">
				<h1>Resultat för ${config.name }</h1>
				<p>
					Här kan du följa tävlingen. Resultaten uppdateras hela tiden och
					fastställs av tävlingsledningen. <br>Välj bland följande
					klasser:
				</p>
			</div>
			<ul>
				<c:forEach items="${tracks }" var="track">
					<li class="nav-item-small"><a
						href="${pageContext.request.contextPath}/public/bytrack/${track.trackId }">${track.trackName }</a></li>
				</c:forEach>
			</ul>
		</div>

		<c:if test="${not empty selectedTrack }">
			<div class="result-box-head">
				<h2>Resultatlista ${selectedTrack }</h2>
				<c:if test="${not empty patrols }">
			Totalt ${fn:length(patrols)} patruller
		</c:if>
			</div>
		</c:if>

		<c:if test="${not empty patrols }">
			<div class="result-box-body">
				<c:forEach items="${patrols }" var="patrol" varStatus="status">
					<div class="scoreitem">

						<strong>${status.count}. ${patrol.patrolName }</strong>
						(${patrol.troop }) <br>
						<c:if test="${patrol.status=='REGISTERED' }">
							<img alt="Registrerad och väntar på start"
								src="${pageContext.request.contextPath}/css/registered.png" />
						</c:if>
						<c:if test="${patrol.status=='ACTIVE' }">
							<img alt="Ute på tävlingen"
								src="${pageContext.request.contextPath}/css/active.png" />
						</c:if>
						<c:if test="${patrol.status=='FINISHED' }">
							<img alt="Har kommit i mål"
								src="${pageContext.request.contextPath}/css/finished.png" />
						</c:if>
						<c:if test="${patrol.status=='RESIGNED' }">
							<img alt="Har lämnat återbud"
								src="${pageContext.request.contextPath}/css/resigned.png" />
						</c:if>
						<c:if test="${empty patrol.status }">
							<img alt="Registrerad och väntar på start"
								src="${pageContext.request.contextPath}/css/registered.png" />
						</c:if>
						<br> <strong>Totalt: ${patrol.totalScore } poäng</strong><br />
						Antal kontroller: ${patrol.totalReportedStations }
					</div>
				</c:forEach>
			</div>
		</c:if>
	</c:if>

</body>
</html>