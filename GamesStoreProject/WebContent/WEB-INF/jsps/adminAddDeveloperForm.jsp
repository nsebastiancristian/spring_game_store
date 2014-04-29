<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a new developer</title>

<link rel="stylesheet" type="text/css" href='<c:url value="/static/css/adminpage.css"/>'>

</head>
<body>

<h2 class="title">Create a new developer/publisher company</h2>

<form class="table" action='<c:url value="/adminAddDeveloperForm" />' method="POST">
<table>
	<tr> <td>Company Name</td> <td><input type="text" name="name" size="35" /></td> </tr>
	<tr> <td></td> <td colspan="2"><input type="submit" value="Create Company"></td> </tr>
</table>
</form>

</body>
</html>