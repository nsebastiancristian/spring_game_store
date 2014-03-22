<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a Pic</title>
</head>
<body>

<sf:form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/doAddPicture"  >
<table>
	<tr>
		<td><label for="image">Profile image:</label></td>
		<td><input name="image" type="file"/></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="Upload image"/></td>
	</tr>
</table>
</sf:form>
</body>
</html>