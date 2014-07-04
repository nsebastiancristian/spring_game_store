<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Games Admin Page</title>

<!-- JSCRIPT LIBS -->
<script type="text/javascript" src='<c:url value="/static/script/tmp/jquery-1.10.2.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/script/tmp/jquery_ui/jquery.ui.core.js"/>'></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.js" type="text/javascript"></script>
<!--  
<script type="text/javascript" src='<c:url value="/static/script/tmp/jquery_ui/jquery.ui.widget.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/script/tmp/jquery_ui/jquery.ui.mouse.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/script/tmp/jquery_ui/jquery.ui.draggable.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/script/tmp/jquery_ui/jquery.ui.resizable.js"/>'></script>
-->
<script type="text/javascript" src='<c:url value="/static/script/tmp/jquery_ui/jquery.ui-contextmenu.js"/>'></script>

<script type="text/javascript" src='<c:url value="/static/script/admingames.js"/>'></script>

<link rel="stylesheet" type="text/css" href='<c:url value="/static/css/games_admin_page.css"/>'>
<link rel="stylesheet" type="text/css" href='<c:url value="/static/css/tmp/jquery_ui/jquery.ui.all.css"/>'>

<style type="text/css">
	
</style>
</head>
<body>
<script type="text/javascript">
	function console(text) {
		$("div.console_text").append("<p>" + text + "</p>");
	}

	//Add a new game functionality
	$(function() {
		$("#button_add").click(function(){
			window.location = "<c:url value='/adminAddGameForm' />";
		}).hover(function(){
			$(this).css("background-color", "green");
		}, function(){
			$(this).css("background-color", "red");
		});
	});

	//Contextual menu related script
	$(function(){
		$(document).contextmenu({
			delegate: "td.game_cell",
			menu: "#options",
	        position: {my: "left top", at: "left bottom"},
			/*position: function(event, ui){
				return {my: "left top", at: "left bottom", of: ui.target};
			},*/
			preventSelect: true,
			taphold: true,
			focus: function(event, ui) {
				var menuId = ui.item.find(">a").attr("href");
				$("#info").text("focus " + menuId);
				console.log("focus", ui.item);
			},
			blur: function(event, ui) {
				$("#info").text("");
				console.log("blur", ui.item);
			},
			beforeOpen: function(event, ui) {
				$(document).contextmenu("setEntry", "title", ui.target.text())

			},
			open: function(event, ui) {
//	          alert("open on " + ui.target.text());
			},
			select: function(event, ui) {
				$target = ui.target;
				switch(ui.cmd) {
					case "view" : {
						//console("View selected");
						window.location = $target.find("a").attr("href");
						break;
					}
					case "edit" : {
						console("Edit selected");
						break;
					}
					case "delete" : {
						console("Delete selected");
						break;
					}
				}
			}
		});
	});

	//Table related script
	$(function(){
		$("#games_table td").hover(function() {
			$(this).addClass("selected_cell").siblings().addClass("selected_row");
		}, function () {
			$(this).removeClass("selected_cell").siblings().removeClass("selected_row");
		});
	});

	//Console related script
	$(function() {
		$("#console div.close_icon").css("background-image", "url(<c:url value='/static/css/tmp/jquery_ui/images/ui-icons_2e83ff_256x240.png'/>)").css("background-position", "-33px -192px").css("cursor", "pointer"); ; 
		$("#console div.close_icon").hover(function(){
			$("#console div.close_icon").css("background-image", "url(<c:url value='/static/css/tmp/jquery_ui/images/ui-icons_cd0a0a_256x240.png'/>)");  
			$("#console div.close_icon").css("background-position", "-33px -192px"); 
		}, function() {
			
			$("#console div.close_icon").css("background-image", "url(<c:url value='/static/css/tmp/jquery_ui/images/ui-icons_2e83ff_256x240.png'/>)"); 
			$("#console div.close_icon").css("background-position", "-33px -192px"); 
		}).click(function() {
			$("#console").slideUp();
		});

		$("#console").resizable({ alsoResize: "div.console_text"}).draggable({ cancel: "div.console_text, div.close_icon"  , scroll: false , containment: "window"});
		
	});
</script>

	<div class="wrapper">

	<h1 class="title">Games List</h1>
	
		<div id="button_add" > </div>
		<table id="games_table">
			<tr>
				<th>Game</th>
				<th>Developer</th>
				<th>Publisher</th>
			</tr>
			<c:forEach var="game" items="${games}">
				<tr>
					<td class="game_cell" >
						<a href="<c:url value='/adminViewGame?id=${game.id}' />">	${game.name} </a>
					</td>
					<td>${game.developer.name}</td>
					<td>${game.publisher.name}</td>
				</tr>
			</c:forEach>
		</table>
		
		<div id="console">
			<div class="close_icon"></div>
			<div class="console_text"></div>
			<!-- <div class="drag_handle"></div>  -->
		</div>
	</div>
	
	<ul id="options" style="display: none; font-size: 0.8em; width: 150px">
			<li class="ui-state-disabled"><a href="#title">Test</a>
			<li>----
			<li><a href="#view"><span class="ui-icon ui-icon-search"></span>View</a>
			<li><a href="#edit"><span class="ui-icon ui-icon-pencil"></span>Edit</a>
			<li><a href="#delete"><span class="ui-icon ui-icon-close"></span>Delete</a>
			<li>----
			<li><a href="#favorite"><span class="ui-icon ui-icon-star"></span>Favorite</a>
	</ul>
</body>
</html>