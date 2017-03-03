<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page='template.jsp'/>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>

</head>
<body class="hero">
	
	<div class="container well test" id="login-box">
	<div>
	
	</div>
	<p >Enter your username and password to login</p>
		<c:if test="${not empty error}">
			<p class="error warning">${error}</p>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		
		<form action="<c:url value='/j_spring_security_check' />" method='POST'>
			<input type="text" placeholder="Username" id="usr" name="username" />
			<br />
			<input type="password" placeholder="Password" id="pwd" name="password"/>
			<input type="submit" value="Login" />
			<br />New here? <a href="${pageContext.request.contextPath }/home/register">Click here to register!</a>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
</body>
<jsp:include page='footer.jsp'/>
</html>