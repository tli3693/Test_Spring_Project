<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page='template.jsp'/>

<html>
<title>Permission denied</title>
<body>

	<div class="container ">
		<h1>HTTP Status 403 - Access is denied</h1>

		<c:choose>
			<c:when test="${empty username}">
				<h2>You do not have permission to access this page!</h2>
			</c:when>
			<c:otherwise>
				<h2>
					You do not have permission to access this page!
				</h2>
			</c:otherwise>
		</c:choose>
	</div>
	<div>
	<jsp:include page='footer.jsp'/>
	</div>
</body>

</html>