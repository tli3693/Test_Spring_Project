<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<jsp:include page='template.jsp'/>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login failure</title>
</head>
<body>
<div class="container">
	<h3>Incorrect username and password. Try again!</h3>
	<a href="${pageContext.request.contextPath }/home/register">Register!</a>
	<sf:form method="POST" action="${pageContext.request.contextPath }/home/loginUser" modelAttribute="user">

		<sf:input type="text" placeholder="Username" id="usr" path="username" />
		<sf:errors path="username" />
		<br />
		<sf:input type="password" placeholder="Password" id="pwd" path="password" />
		<sf:errors path="password" />
		<br />
		<input type="submit" value="Login" />
	</sf:form>
</div>
</body>
<jsp:include page='footer.jsp'/>
</html>