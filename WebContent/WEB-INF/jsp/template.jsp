<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%-- 	<link href="<c:url value="/scripts/bootstrap.min.css" />" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/scripts/jquery-3.1.0.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/scripts/bootstrap.min.js" />"></script>  --%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/scripts/jquery.tablesorter.min.js" />"></script>

</head>
<body>
	<nav class="navbar navbar-default test">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="glyphicon glyphglyphicon glyphicon-bar"></span> <span
						class="glyphicon glyphicon-bar"></span> <span
						class="glyphicon glyphicon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="${pageContext.request.contextPath }/welcome"><i
					class="glyphicon glyphicon-home"></i> Home</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="" id="Link"><a href="#">Link <span
							class="sr-only">(current)</span></a></li>
					<li class="" id="UsersPage"><a
						href="${pageContext.request.contextPath }/test/users">Users</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Dropdown <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Separated link</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">One more separated link</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li>
					<form class="navbar-form ">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Search">
							<button name="searchCriteria" type="submit"
								class="btn btn-default">
								<i class="glyphicon glyphicon-search"></i> Search
							</button>
						</div>
					
						<c:if test="${pageContext.request.userPrincipal.name == null}">
				You are not logged in.
					<a href="${pageContext.request.contextPath }/home/login">Login</a> | 
					<a href="${pageContext.request.contextPath }/home/register">Register</a>
						</c:if>
						<c:if test="${pageContext.request.userPrincipal.name != null}">
			
			Logged in as: ${pageContext.request.userPrincipal.name} | <a
								href="javascript:formSubmit()"> Logout</a>
						</c:if>
						</form>
					</li>
				</ul>


			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div>
		<div class="container page-header">
			<h1>${title}:${message}</h1>
		</div>
		<div class="container">

			<sec:authorize access="hasRole('ROLE_USER')">
				<!-- For login user -->
				<c:url value="/j_spring_security_logout" var="logoutUrl" />
				<form action="${logoutUrl}" method="post" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<script>
						function formSubmit() {
							document.getElementById("logoutForm").submit();
						}
					</script>


			</sec:authorize>
		</div>
	</div>

</body>
<style>
.hero {
	background-color: #262626;
	color: white;
}

.error {
	padding: 2px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 2px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 550px;
	padding: 20px;
	margin: 100px auto;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}

#register-form {
	width: 550px;
	padding: 20px;
	margin: 100px auto;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}

.center {
	float: none;
	margin-left: auto;
	margin-right: auto;
	margin: 100px auto;
}

.table-hover>tbody>tr:hover {
	background-color: #d3d3d3;
}

.test {
	color: black;
}

/* th.headerSortUp { 
    background-image: url(<c:url value="/scripts/asc.gif" />); 
    background-color: #e0ebeb; 
} 
th.headerSortDown { 
    background-image: url(<c:url value="/scripts/desc.gif" />); 
    background-color: #e0ebeb; 
} 
th.header { 
    background-image: url(<c:url value="/scripts/bg.gif" />); 
    cursor: pointer; 
    font-weight: bold; 
    background-repeat: no-repeat; 
    background-position: center right; 
    padding-left: 20px; 
    border-right: 1px solid #dad9c7; 
    margin-left: -1px; 
}  */
/* tables */
table.tablesorter thead tr .header {
	background-image: url(< c : url value = "/scripts/bg.gif"/ >);
	background-repeat: no-repeat;
	background-position: center right;
	cursor: pointer;
}

table.tablesorter thead tr .headerSortUp {
	background-image: url(< c : url value = "/scripts/asc.gif"/ >);
}

table.tablesorter thead tr .headerSortDown {
	background-image: url(< c : url value = "/scripts/desc.gif"/ >);
}

table.tablesorter thead tr .headerSortDown, table.tablesorter thead tr .headerSortUp
	{
	background-color: #8dbdd8;
}
</style>
</html>