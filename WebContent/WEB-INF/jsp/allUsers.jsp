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

	<div class="container well test">
	<sf:form name='filteredUsers' action="${pageContext.request.contextPath }/test/users/filtered" method='POST' commandName="filterUser">
		<h3><sf:errors path="*" class="error" element="div" /></h3>
		<div class="col-md-2">
			<h3>Filter by:</h3>
			Username<br/>
			<sf:input path="username" type="text"/><br/>
			First name<br/>
			<sf:input path="nameFirst" type="text"/><br/>
			Last name<br/>
			<sf:input path="nameLast" type="text"/><br/>
			Enabled?<br/>
			<sf:select path="enabled" multiple="single" >
				<option value="null" disabled="true" selected="selected">-Select-</option>
				<option value="true" >True</option>
				<option value="false" >False</option>
			</sf:select><br/><br/>
			<button id="filterButton" name="filterButton" class="button btn-default" type="submit">Filter</button>
		</div>
	</sf:form>
	<div class="col-md-1 divider"></div>
	<div class="col-md-9">
		<h3>Select user(s) to modify/delete.</h3>
		<c:if test="${not empty error}">
			<h3 class="error">${error } </h3>
		</c:if>
		<c:if test="${not empty selected}">
			<h3 class="msg">${selected } </h3>
		</c:if>
	<h3 id="error" class="error" hidden="true"></h3>
	<sf:form name='selectedUsers' action="${pageContext.request.contextPath }/test/users/selected" method='POST' commandName="admin">
	<h3><sf:errors path="*" class="error" element="div" /></h3>
	
	<table id="myTable" class="table table-hover test tablesorter table-bordered">
	    <thead class="thead-inverse">
      <tr>
      	<th>#</th>
        <th>Username</th>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Enabled</th>
        <td class="theader"><input type="checkbox" id="checkBoxAll" class="checkbox"/></td>
      </tr>
    </thead>
    <tbody>
      <c:set var="index" value="1" ></c:set>
		<c:if test="${not empty lists}">
			<c:forEach var="user" items="${lists}">
			<tr >
				<td>${index }</td>
				<td>${user.username }</td>
				<td>${user.nameFirst }</td>
				<td>${user.nameLast }</td>
				<td>${user.enabled }</td>
				<td><sf:checkbox path="listUsername" value="${user }" name="username" class="chkCheckBoxId"/></td>
			</tr>
			<c:set var="index" value="${index+1}"></c:set>
			</c:forEach>
		</c:if>
    </tbody>
  </table>
  <div id="buttonDiv" class="pull-right">
 
  <!-- <button id="delButton" type="button" class="btn btn-danger icon-trash icon-white" data-toggle="modal" data-target="#myModal"> -->
  <button id="delButton" type="button" class="btn btn-danger icon-trash icon-white" onclick="submitForm();">
  	<i class="glyphicon glyphicon-trash"></i> Delete
  </button>
  <button name="modifyButton" type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-pencil"></i> Modify</button>
  <!-- Modal: Confirmation message for Deletion -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div  class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Confirm Deletion</h4>
        </div>
        <c:set var="index2" value="1"></c:set>
        <div class="modal-body">
          <p>Are you sure you want to delete the following users?</p>
			<div id="myDiv"> </div>
		</div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
          <button name="deleteButton" type="submit" class="btn btn-primary" >Yes</button>
        </div>
      </div>
      
    </div>
  </div>
  
  </div>
  <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
  </sf:form>
  </div>
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
	
	$(document).ready(function (){
		$("#delButton").click(function() {
			var text = "";
			  var arr = $(".chkCheckBoxId:checked").map(function() { 
			              return $(this).val();  //get the <label>'s text
			            }).get();
			  for (i = 0; i < arr.length; i++) { 
				    text += i+1 + ". " +  arr[i] + "<br>";
				}
			  $("#myDiv").html(text);
		});
		
	});
	function submitForm() {
		if ($(".chkCheckBoxId:checked").length > 0)
		{
			$("#myModal").modal("show");
			
		}
		else
		{
			$(".error").hide();
			$("#error").show();
		    $("#error").text("Must select at least one user for deletion");
		}
	}
	$(document).ready(function() 
	    { 
	        $("#myTable").tablesorter(); 
	    } 
	); 
		    
</script>
</body>
<style>
.table-hover > tbody > tr:hover {
  background-color: #d3d3d3;
}
</style>
<jsp:include page='footer.jsp'/>
</html>