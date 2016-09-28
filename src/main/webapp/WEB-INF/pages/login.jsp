<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Logga in</title>
</head>
<body>
<div class="nav-box">
			<div class="page-head">
			<h1>${config.name }</h1>
			</div>
			</div>
			<div class="ingress">
				Logga in för att rapportera poäng.<br>
				<c:if test="${config.allowPublicResult}">
					<p>Du kan titta på den aktuella <a href="${pageContext.request.contextPath}/public/">poängställningen</a> utan att logga in.</p>
				</c:if>
			</div>
			
<form name="f" action="${pageContext.request.contextPath}/login" method="POST" class="form-general">
	<div class="form-box">
	
	<h4>Logga in för kontrollanter</h4>
 
	<c:if test="${not empty error}">
		<div class="errorblock">
			${error }
		</div>
	</c:if>
	
	 		
<label for="j_username">Användarnamn:</label>
<input type="text" name="ssoId" id="username" placeholder="Ange användarnamn" required>
<label for="j_password">Lösenord:</label>
<input type='password' name='password' placeholder="Ange lösenord" required>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</div>
<input name="submit" type="submit" value="Logga in" />
<c:if test="${not empty config.phone }"><br><small>Telefon till start/mål: <a href="tel:${config.phone }">${config.phone }</a></small><br></c:if>
</form>
</body>
</html>