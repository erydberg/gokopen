<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Klass - ${track.trackName }</title>
</head>
<body>
<form:form commandName="track" method="post" action="${pageContext.request.contextPath}/admin/track">
<form:hidden path="trackId" id="trackId"/>
<table>
<tr>
<td><label>Klass:</label></td>
<td><form:input path="trackName" id="trackName"/></td>
</tr>
<tr>
<tr><td><input type="submit" name="saveTrack" value="Spara"/> | <a href="${pageContext.request.contextPath}/admin/track">Tillbaka</a></td>
</tr>
</table>
</form:form>
</body>
</html>