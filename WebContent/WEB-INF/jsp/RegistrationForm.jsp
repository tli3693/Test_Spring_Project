<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<jsp:include page='template.jsp'/>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration page</title>
</head>
<body class="hero">
<div class="container well test" id="register-form">
	<div >
		Already registered?  <a href="${pageContext.request.contextPath }/home/login">Click here to login!</a> 
		<sf:form method="POST"
			action="${pageContext.request.contextPath }/home/registerUser"
			modelAttribute="user">
			<sf:errors path="*" class="error" element="div" />
			<sf:input type="text" placeholder="Username" id="usr" path="username" />
			<br />
			<sf:input type="password" placeholder="Password" id="pwd"
				path="password" />
			<br />
			<sf:input type="text" placeholder="First Name" id="fname"
				path="nameFirst" />
			<br />
			<sf:input type="text" placeholder="Last Name" id="lname"
				path="nameLast" />
			<br />
			<input type="submit" value="Register" />	
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
				
		</sf:form>
	</div>
	</div>
</body>
<jsp:include page='footer.jsp'/>
</html>