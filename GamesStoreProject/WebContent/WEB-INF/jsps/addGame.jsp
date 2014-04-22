<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a new game</title>

<link rel="stylesheet" type="text/css" href='<c:url value="/static/css/tmp/default.css"/>'>

</head>
<body>

<%--
<form:form method="POST" action="${pageContext.request.contextPath}/doAddGameForm" commandName="game" >
	<table>
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
			<td><form:input path="dateAdded"/></td>
		</tr>
		<tr>
			<td colspan="2" class="tableTextOnCenter"> <input type="submit" value="Press da Button! I dare you!"/></td>
		</tr>
	</table>
</form:form>
--%>

<form:form method="POST" action="${pageContext.request.contextPath}/doAddGameForm" commandName="game">
	<table>
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
			<td><form:input path="dateAdded"/></td>
		</tr>
		<tr>
			<td colspan="2" class="tableTextOnCenter"> <input type="submit" value="Press da Button! I dare you!"/></td>
		</tr>
	</table>
</form:form>

</body>
</html>