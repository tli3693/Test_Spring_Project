<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<jsp:include page='template.jsp'/>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration failure</title>
</head>
<body class="hero">
<div class="container">
	<div id="register-form">
	<h3>Username already exists. Try again!</h3>
	<sf:form method="POST" action="${pageContext.request.contextPath }/home/registerUser" modelAttribute="user">
		<sf:input type="text" placeholder="Username" id="usr" path="username" />
		<sf:errors path="username" />
		<br />
		<sf:input type="password" placeholder="Password" id="pwd" path="password" />
		<sf:errors path="password" />
		<br />
		<sf:input type="text" placeholder="First Name" id="fname" path="firstname" />
		<sf:errors path="firstname" />
		<br />
		<sf:input type="text" placeholder="Last Name" id="lname" path="lastname" />
		<sf:errors path="lastname" />
		<br />
		<input type="submit" value="Register" />
	</sf:form>
	</div>
</div>
</body>
<jsp:include page='footer.jsp'/>
</html>