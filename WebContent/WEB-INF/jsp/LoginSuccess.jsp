<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page='template.jsp'/>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login success!</title>
</head>
<body class="hero">
	<div class="container">
		<h3>Welcome, ${user.username }!</h3>
		<%-- <a href="<c:url value='/j_spring_security_logout' />">Logout</a> --%>
		<a href="javascript:formSubmit()"> Logout</a>
	</div>


</body>
<jsp:include page='footer.jsp'/>
</html>