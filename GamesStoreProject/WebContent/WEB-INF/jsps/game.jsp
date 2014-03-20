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

<h1 class="title">Game</h1>

	<table class="table">
		<tr>
			<td> Game: </td>
			<td> ${game.name} </td>
		</tr>
		<tr>
			<td>Developer:</td>
			<td>${game.developer.name}</td>
		</tr>
		<tr>
			<td>Publisher:</td>
			<td>${game.publisher.name}</td>
		</tr>
		<tr>
			<td>Released on:</td>
			<td>${game.dateReleased}</td>
		</tr>
		<tr>
			<td>Description:</td>
			<td>${game.description}</td>
		</tr>
		<tr>
			<td>Buy:</td>
			<td>
				<c:choose>
	    				<c:when test="${game.owner != null}">
					       You allready own this game
					    </c:when>
					    <c:otherwise>
							<a href="<c:url value='/buygame?id=${game.id}' />"> Buy this game</a> 
					    </c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>Add to wishlist:</td>
			<td>
			<c:choose>
	    			<c:when test="${game.owner != null}">
					    Owned
				    </c:when>
	    			<c:when test="${game.wishlisted == true}">
					    Wishlisted
					</c:when>
					<c:otherwise>
						<a href="<c:url value='/addtowishlist?id=${game.id}' />"> Add </a> 
					</c:otherwise>
			</c:choose>
			</td>
		</tr>
	</table>

</body>
</html>