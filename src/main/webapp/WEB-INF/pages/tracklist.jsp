<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Klasser</title>
</head>
<script>
function confirmDelete(id){
	var r=confirm("Vill du ta bort klassen?");
	if (r==true)
	  {
		window.location.href = '${pageContext.request.contextPath}/admin/track/delete/'+id;
	  }
	else
	  {
	  return false;
	  }
	}
</script>
<body>
<div class="nav-box">
<h1>Klasser</h1>
<a href="${pageContext.request.contextPath}/admin/track/newtrack">Lägg till ny klass</a> | <a href="${pageContext.request.contextPath}/admin">Tillbaka</a>
</div>
<div class="notepad">
<ul class="list">
<c:forEach items="${tracks}" var="track">
<li><a href="${pageContext.request.contextPath}/admin/track/edit/${track.trackId}">${track.trackName }</a> <a href="#" onclick="return confirmDelete(${track.trackId});" class="del"><img src="${pageContext.request.contextPath}/css/delete.png"></a></li>
</c:forEach>
</ul>
</div>
</body>
</html>