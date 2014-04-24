<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a new game</title>

<!-- STYLESHEETS -->
<link rel="stylesheet" type="text/css" href='<c:url value="/static/css/tmp/jquery_ui/jquery.ui.all.css"/>'>
<link rel="stylesheet" type="text/css" href='<c:url value="/static/css/tmp/default.css"/>'>

<!-- JSCRIPT LIBS -->
<script type="text/javascript" src='<c:url value="/static/script/tmp/jquery-1.10.2.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/script/tmp/jquery_ui/jquery.ui.core.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/script/tmp/jquery_ui/jquery.ui.widget.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/script/tmp/jquery_ui/jquery.ui.datepicker.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/script/tmp/jquery_ui/jquery.ui.effect.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/script/tmp/jquery_ui/jquery.ui.effect-bounce.js"/>'></script>

<!-- SCRIPTS -->
<script>
	$(function() {
		$( "#datepickerAdded" ).datepicker({
			changeMonth: true,
			changeYear: true,
			showAnim: 'bounce'
		});
		
		$( "#datepickerReleased" ).datepicker({
			changeMonth: true,
			changeYear: true,
			showAnim: 'bounce'
		});
	});
</script>

</head>
<body>



<form:form method="POST" action="${pageContext.request.contextPath}/doAddGameForm" commandName="game">
	<table id="formGame">
		<tr>
			<td>Title:</td>
			<td>  <form:input path="name" size="26" />  </td>
		</tr>
		<tr>
			<td class="tableTextOnTop">Description:</td>
			<td><form:textarea path="description" rows="12" cols="20"/></td>
		</tr>
		<tr>
			<td>Added on:</td>
			<td><form:input path="dateAdded" id="datepickerAdded" /></td>
		</tr>
		<tr>
			<td>Released on:</td>
			<td><form:input path="dateReleased" id="datepickerReleased" /></td>
		</tr>
		<tr>
			<td>Developer:</td>
			<td><form:select path="developer" items="${developers}"></form:select></td>
		</tr>
		<tr>
			<td colspan="2" class="tableTextOnCenter"> <input type="submit" value="Press da Button! I dare you!"/></td>
		</tr>
	</table>
</form:form>

</body>
</html>