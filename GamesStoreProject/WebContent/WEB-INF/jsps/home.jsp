<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>

<link rel="stylesheet" type="text/css"
	href='<c:url value="/static/css/default.css"/>'>

</head>
<body>
	<h1 class="title">Welcome to the Game Store Project (2nd edition)!</h1>

	<p>
		<sec:authorize access="isAuthenticated()"> Hello ${username}  </sec:authorize>
	</p>

	<h2>Your options:</h2>
	<p>
		<a href="<c:url value='/games' />">Go to the games page.</a>
	</p>

	<p>
		<a href="<c:url value='/userpage' />">Go to the user page.</a>
	</p>

	<p>
		<a href="<c:url value='/adminpage' />">Go to the admin page.</a>
	</p>
	
	<p>
		<a href="<c:url value='/mygames' />">Go to My Games.</a>
	</p>
	
	<p>
		<a href="<c:url value='/mygames' />">Go to My Games.</a>
	</p>
	

	

	<sec:authorize access="isAuthenticated()"> <p><a href='<c:url value="/j_spring_security_logout"/>'>Log out</a> </p> </sec:authorize>
	<sec:authorize access="!isAuthenticated()"> <p>	<a href='<c:url value="/login"/>'>Log in</a> </p> </sec:authorize>
	
	<img alt="This is an image" src='<c:url value="/static/images/europa universalis III.jpg" />'>
</body>
</html>