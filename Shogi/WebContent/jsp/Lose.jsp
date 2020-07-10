<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>

	<head>
	
	    <meta charset="UTF-8">
	    <title>Game-End</title>
	    <link rel="stylesheet" type="text/css" href="../css/MainStyle.css">
	
	</head>
	
	<body>
		<header>
			<div id = "title">
		        <h1>Sie haben verloren!</h1>
		    </div>
		</header>
	    
	
	    <div id="content">
	    	
	    	<br><br>
	    	<form method="GET" action="http://localhost:8080/Shogi/Controller">
				<input name="playAgain" type="submit" value="Nochmal Spielen" class="Button" />
				<br><br>
				<input name="startPage" type="submit" value="Startseite" class="Button" />
			</form>
			<br><br>
			
	    </div>
	
	</body>
	
	<footer>
	
	    <a href = "../jsp/Impressum.jsp" class="footer-buttons">Impressum</a>
		<a href = "../jsp/Instructions.jsp" class="footer-buttons">Anleitung</a>
		
		<i style="font-size:15px; padding: 2px; float: right"> ©2020 DHBW Karlsruhe All rights reserved.</i>
	</footer>

</html>