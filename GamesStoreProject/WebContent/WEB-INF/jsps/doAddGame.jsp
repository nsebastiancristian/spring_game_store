<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a game to the database</title>
</head>
<body>

<c:choose>
	<c:when test="${ success == true }">
		<p>Succesfully submited!</p>
	</c:when>
	<c:otherwise>
		<p>An error has occured!</p>
	</c:otherwise>
</c:choose>

</body>
</html>