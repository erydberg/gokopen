<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Logga in</title>
</head>
<body onload='document.f.j_username.focus();'>
<div class="nav-box">
			<div class="page-head">
			<h1>Välkommen till ${config.name }</h1>
			</div>
			</div>
			<div class="ingress">
				<p>Här rapporteras poängen för tävlingen av de olika kontrollanterna. </p>
				<c:if test="${config.allowPublicResult}">
					<p>Du kan titta på den aktuella <a href="${pageContext.request.contextPath}/public/">poängställningen</a> utan att logga in.</p>
				</c:if>
			</div>
			
<form name="f" action="j_spring_security_check" method="POST" class="form-general">
	<div class="form-box">
	
	<h3>Logga in för kontrollanter</h3>
 
	<c:if test="${not empty error}">
		<div class="errorblock">
			${error }
		</div>
	</c:if>
<label for="j_username">Användarnamn:</label>
<input type="text" name="j_username" id="j_username">
<label for="j_password">Lösenord:</label>
<input type='password' name='j_password' />
</div>
<input name="submit" type="submit" value="Logga in" />
</form>
</body>
</html>