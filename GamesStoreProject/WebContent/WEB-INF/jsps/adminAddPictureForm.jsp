<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a Pic</title>

<script type="text/javascript" src='<c:url value="/static/script/tmp/jquery-1.10.2.js"/>'></script>

<link rel="stylesheet" type="text/css" href='<c:url value="/static/css/tmp/default.css"/>'>


</head>
<body>
<script type="text/javascript">
	
	function getRandomInt(min, max) {
		return Math.floor(Math.random() * (max - min + 1)) + min;
	}
		
	function changePhotoDim(start, n) {
		var choice;
		
		for(var i = start; i < (start + n); i++) {
			choice = getRandomInt(1,2);
			
			switch(choice) {
				case 1: {	//big picture
					    $("#picContainer img").eq(i).css("width", "100%");
					    break; 
				    }
				case 2: {  //two small equal sized pictures
					    $("#picContainer img").eq(i).css("width", "50%");
					    $("#picContainer img").eq(++i).css("width", "50%");
					    break; 
				    }
			}
		}
	}
	
	$(function() {
	    changePhotoDim(0,9);
		
		$("#addInput").click(function() {
			var num     = $('.clonedInput').length; 
			var newNum  = new Number(num + 1); 
			
			var newElem = $('#input' + num).clone().attr('id', 'input' + newNum);
			
			$('#input' + num).after(newElem);
		});
	});
</script>

<sf:form id="addPicForm" method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/adminAddPictureForm"  >
		<div id='input1' class='clonedInput'>
			<label for="image">Image:</label>
			<input name="image" type="file"/>
		</div>
		<input type="hidden" name="gameId" value="9" /> 
		<input type="submit" value="Upload image"/>
</table>
</sf:form>
<p><input type="button" id="addInput" value="Add another file" /></p>

<div id="picContainer">
		<c:forEach items="${pics}" var="picPath">
			<img src='<c:url value="${picPath}" />' />
		</c:forEach>
</div>
</body>
</html>