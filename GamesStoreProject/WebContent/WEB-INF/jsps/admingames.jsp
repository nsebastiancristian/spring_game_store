<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Games Admin Page</title>

<script type="text/javascript" src='<c:url value="/static/script/jquery.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/script/admingames.js"/>'></script>

<link rel="stylesheet" type="text/css"
	href='<c:url value="/static/css/games_admin_page.css"/>'>
</head>
<body>


	<div class="wrapper">

	<h1 class="title">Games List</h1>
	
		<table class="games_table">
			<tr>
				<th>Game</th>
				<th>Developer</th>
				<th>Publisher</th>
			</tr>
			<c:forEach var="game" items="${games}">
				<tr>
					<td class="game_cell">
						<a
							href="<c:url value='/game?id=${game.id}' />">
							${game.name}
						</a>
					</td>
					<td>${game.developer.name}</td>
					<td>${game.publisher.name}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>