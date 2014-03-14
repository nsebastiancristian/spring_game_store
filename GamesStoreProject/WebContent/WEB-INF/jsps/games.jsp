<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Games Page</title>

<link rel="stylesheet" type="text/css"
	href='<c:url value="/static/css/default.css"/>'>
</head>
<body>

<h1 class="title">Games List</h1>

	<table class="table">
		<tr>
			<td>id</td>
			<td>Game</td>
		</tr>
		<c:forEach var="game" items="${games}">
			<tr>
				<td></td>
				<td>${game.name}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>