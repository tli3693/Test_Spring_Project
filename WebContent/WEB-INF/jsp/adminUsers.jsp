<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page='template.jsp'/>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${title }</title>
</head>
<body class="hero">
	<div class="container">
	<h3>${selected }</h3>
	
	<sf:form method='POST' commandName="admin2" action="${pageContext.request.contextPath }/test/submit">
			<c:forEach var="item" items="${users}">
				<span class="checkbox">
				<sf:checkbox path="testString" value="${item }" name="username"/>${item.username }
				</span>
			</c:forEach>
			<td><input type="submit" name="submit" value="Submit"></td>
	
  
  <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
  </sf:form>
	</div>

</body>
<jsp:include page='footer.jsp'/>
</html>