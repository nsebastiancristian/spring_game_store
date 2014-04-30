<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>

<link rel="stylesheet" type="text/css" href='<c:url value="/static/css/default.css"/>'> 

</head>
<body>
<h1 class="title">This is the page for the Administrator</h1>
<p><a href='<c:url value="/adminAddDeveloperForm"/>'>Add a new developer</a></p>
<p><a href='<c:url value="/adminGames"/>'>Go to the games administration page</a></p>
<p><a href='<c:url value="/adminAddPictureForm"/>'>Add a picture</a></p>

</body>
</html>