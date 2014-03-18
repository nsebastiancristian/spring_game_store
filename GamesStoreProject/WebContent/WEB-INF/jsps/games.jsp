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
			<td>Added On</td>
			<td>Released On</td>
			<td>Developer</td>
			<td>Publisher</td>
			<td>Buy Game</td>
			<td>Add to wishlist</td>
		</tr>
		<c:forEach var="game" items="${games}">
			<tr>
				<td>${game.id}</td>
				<td>${game.name}</td>
				<td>${game.dateAdded}</td>
				<td>${game.dateReleased}</td>
				<td>${game.developer.name}</td>
				<td>${game.publisher.name}</td>
				<td> 
					<c:choose>
	    				<c:when test="${game.owner != null}">
					       Owned
					    </c:when>
					    <c:otherwise>
							<a href="<c:url value='/buygame?id=${game.id}' />"> Buy</a> 
					    </c:otherwise>
					</c:choose>
				</td>
				<td>
					<a href="<c:url value='/addtowishlist?id=${game.id}' />"> Add </a> 
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>