<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a new User/Administrator</title>
</head>
<body>

<form:form method="POST" action="${pageContext.request.contextPath}/doAdminAddUserForm" commandName="user">
	<table id="formGame">
		<tr>
			<td>Username:</td>
			<td>  <form:input path="username" size="26" />  </td>
		</tr>
		<tr>
			<td>Password:</td>
			<td>  <form:input path="password" size="26" />  </td>
		</tr>
		<tr>
			<td>Email:</td>
			<td>  <form:input path="email" size="26" />  </td>
		</tr>
		<tr>
			<td>Name:</td>
			<td>  <form:input path="name" size="26" />  </td>
		</tr>
		<tr>
			<td><form:radiobutton path="admin" value="true" />Admin</td>
			<td><form:radiobutton path="admin" value="false" />User</td>
		</tr>
		<tr>
			<td colspan="2" class="tableTextOnCenter"> <input type="submit" value="Submit"/></td>
		</tr>
	</table>
</form:form>

</body>
</html>