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

	<div class="container well">
	<c:if test="${not empty selected}">
		<h3 class="msg">${selected } </h3>
	</c:if>
	<sf:form name='selectedUsers' action="${pageContext.request.contextPath }/test/users/update" method='POST' commandName="admin">
	<h3><sf:errors path="*" class="error" element="div" /></h3>
	<table class="table table-hover test">
	    <thead>
      <tr>
      	<th>#</th>
        <th>Username</th>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Enabled</th>
      </tr>
    </thead>
    <tbody>
      <c:set var="index" value="1" ></c:set>
		<c:if test="${not empty lists}">
			<c:forEach var="user" items="${lists}">
			<tr>
				<td>${index }</td>
				<%-- <p><form:input size="67" path="questions[${s.index}].text"/></p> --%>
				<td><sf:input path="listUsers[${index-1 }].username" value="${user.username  }" readonly="true"></sf:input></td>
				<%-- <td><input name="listUsers[${index-1 }].username" value='<c:out value="${user.username}"></c:out>' readonly="readonly" class="required"></td> --%>
				<td><sf:input path="listUsers[${index-1 }].nameFirst" value="${user.nameFirst }"></sf:input></td>
				<td><sf:input path="listUsers[${index-1 }].nameLast" value="${user.nameLast }"></sf:input></td>
				<%-- <td><sf:input path="listUsers[${index-1 }].enabled" value="${user.enabled  }"></sf:input></td> --%>
				<td>
				<sf:select path="listUsers[${index-1 }].enabled" multiple="single" id="enabled">
					<option value="${ user.enabled  }" selected="selected">${user.enabled  }</option>
					<option value="${!user.enabled  }" >${!user.enabled  }</option>
				</sf:select>
				</td>
			</tr>
			<c:set var="index" value="${index+1}"></c:set>
			</c:forEach>
		</c:if>
    </tbody>
  </table>
  <div id="buttonDiv" class="pull-right">
  <button name="cancelButton" type="submit" class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i> Cancel</button>
  <button name="updateButton" type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-floppy-disk"></i> Update</button>
  </div>
  <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
  </sf:form>
	</div>
<script type="text/javascript">
	$(document).ready(function () {
		$('#checkBoxAll').click(function () {
			if($(this).is(":checked"))
				$('.chkCheckBoxId').prop('checked',true);
			else
				$('.chkCheckBoxId').prop('checked',false);
		});
	});
	$(document).ready(function (){
		   $(".nav").find(".active").removeClass("active");
		   $("#UsersPage").addClass("active");
		});
</script>
</body>
<style>
.table-hover > tbody > tr:hover {
  background-color: #d3d3d3;
}
</style>
<jsp:include page='footer.jsp'/>
</html>