<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="sv">
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Klass - ${track.trackName }</title>
</head>
<body>
	<div class="nav-box">
	<h1>Klass</h1>
		<form:form commandName="track" method="post"
			action="${pageContext.request.contextPath}/admin/track">
			<form:hidden path="trackId" id="trackId" />
			<div class="form-box">
				<fieldset>
					<div class="text size-3">
						<label>Klass:</label>
						<form:input path="trackName" id="trackName" />
					</div>
				</fieldset>
				<div class="submit-area">
					<input type="submit" name="saveTrack" value="Spara" /> | <a
						href="${pageContext.request.contextPath}/admin/track">Tillbaka</a>
				</div>
			</div>
		</form:form>
	</div>