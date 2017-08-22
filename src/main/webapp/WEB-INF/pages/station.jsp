<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Kontroll - ${station.stationName }</title>
</head>
<body>
<div class="nav-box">
<h1>Kontroll</h1>
<form:form commandName="station" method="post" action="${pageContext.request.contextPath}/admin/station" cssClass="form-general">
<form:hidden path="stationId" id="stationId"/>

<div class="form-box">
<fieldset>
<div class="text size-3">
<label>Kontrollens nummer:</label>
<form:input path="stationNumber" id="stationNumber"/>
<label>Namn:</label>
<form:input path="stationName" id="stationName"/>
<p>
<label>Milstolpe</label>
För kontroller där patrullen bara anmäler sig och inte får några poäng
<form:checkbox path="waypoint" id="waypoint"/>
</p>
<label>Minsta poäng: </label>
<form:input path="minScore" id="minScore"/>
<label>Max poäng: </label>
<form:input path="maxScore" id="maxScore"/>
<label>Minsta stilpoäng: </label>
<form:input path="minStyleScore" id="minStyleScore"/>
<label>Max stilpoäng: </label>
<form:input path="maxStyleScore" id="maxStyleScore"/>
<label>Ansvarig på kontrollen: </label>
<form:input path="stationContact" id="stationContact"/>
<label>Telefonnummer till ansvarig: </label>
<form:input path="stationPhonenumber" id="stationPhonenumber"/>
<label>Användare som får lägga till poäng (utöver admin-konton): </label>
<form:input path="stationUser" id="stationUser"/>
</div>
</fieldset>
<div class="submit-area">
<input type="submit" name="saveStation" value="Spara"/> | <a href="${pageContext.request.contextPath}/admin/station">Tillbaka</a>
</div>
</div>
</form:form>
</div>
</body>
</html>