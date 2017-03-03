<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Spring MVC checkbox</title>
</head>

<body>
	<h2>The users you selected are shown below:</h2>
	<br/>
<%-- 	<c:forEach var="course" items="${member.courses2}">  
			<c:out value="${course}"/><br>
	</c:forEach> --%>
	
	<c:forEach var="user" items="${admin.users2}">  
			<c:out value="${user.username }"/><br>
	</c:forEach>

</body>
</html>