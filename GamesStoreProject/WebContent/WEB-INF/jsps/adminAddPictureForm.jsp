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

</head>
<body>
<script type="text/javascript">
	var inputIdx = 1;
	
	$(function() {
		$("a#addInput").click(function() {
			inputIdx++;
			var inputName = "image_" + inputIdx;
			
			var strInput = "<tr id='last'>";
			strInput += "<td><label='" + inputName + "' > Image: </label> </td>";
			strInput += "<td><input='" + inputName + "' type='file' /></td>";
			strInput += "</tr>";
			var newTr = $(strInput);
			
			$("tr#last").before(newTr).removeAttr("id");
		});
	});
</script>

<sf:form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/doAdminAddPictureForm"  >
<table>
	<tr>
		<td><label for="image_1">Image:</label></td>
		<td><input name="image_1" type="file"/></td>
	</tr>
	<tr id="last">
		<td colspan="2"><input type="hidden" name="gameId" value="3" /> </td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="Upload image"/></td>
	</tr>
</table>
</sf:form>
<p><a id="addInput" href="#">Add another file</a></p>
</body>
</html>