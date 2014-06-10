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
<form:form commandName="station" method="post" action="/gokopen/admin/station">
<form:hidden path="stationId" id="stationId"/>
<table>
<tr>
<td><label>Kontrollens nummer:</label></td>
<td><form:input path="stationNumber" id="stationNumber"/></td>
</tr>
<tr>
<td><label>Namn:</label></td>
<td><form:input path="stationName" id="stationName"/></td>
</tr>
<tr>
<td><label>Minsta poäng: </label></td>
<td><form:input path="minScore" id="minScore"/></td>
</tr>
<tr>
<td><label>Max poäng: </label></td>
<td><form:input path="maxScore" id="maxScore"/></td>
</tr>
<tr>
<tr>
<td><label>Minsta stilpoäng: </label></td>
<td><form:input path="minStyleScore" id="minStyleScore"/></td>
</tr>
<tr>
<td><label>Max stilpoäng: </label></td>
<td><form:input path="maxStyleScore" id="maxStyleScore"/></td>
</tr>
<tr>
<td><label>Ansvarig på kontrollen: </label></td>
<td><form:input path="stationContact" id="stationContact"/></td>
</tr>
<tr>
<td><label>Telefonnummer till ansvarig: </label></td>
<td><form:input path="stationPhonenumber" id="stationPhonenumber"/></td>
</tr>
<tr>
<td><label>Användare som får lägga till poäng: </label></td>
<td><form:input path="stationUser" id="stationUser"/></td>
</tr>

<tr><td><input type="submit" name="saveStation" value="Spara"/> | <a href="/gokopen/admin/station">Tillbaka</a></td>
</tr>
</table>
</form:form>
</body>
</html>