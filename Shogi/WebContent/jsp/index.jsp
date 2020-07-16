<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>

	<head>
	
	    <meta charset="UTF-8">
	    <title>Start</title>
	    <link rel="stylesheet" type="text/css" href="../css/MainStyle.css">
	
	</head>
	
	<body>
		<header>
			<div id = "title">
		        <h1>Willkommen bei Shogi - The Game</h1>
		    </div>
		</header>
	    
	
	    <div id="content">
	    	
	    	<form method="GET" action="http://localhost:8080/Shogi/Controller">
				<input name="singleplayer" type="submit" value="Einzelspieler" class="Button" /><br><br>
				<input name="multiplayer" type="submit" value="Mehrspieler" class="Button" /><br><br>
				<input name="settings" type="image" value="Einstellungen" src="../res/settings.png" alt="Einstellungen" width="50" height="50" class="settingBt" />
				<br><br>
			</form>
			
	    </div>
	
	</body>
	
	<footer>
	
	    <a href = "../jsp/Impressum.jsp" class="footer-buttons">Impressum</a>
		<a href = "../jsp/Instructions.jsp" class="footer-buttons">Anleitung</a>
		
		<i style="font-size:15px; padding: 2px; float: right"> ©2020 DHBW Karlsruhe All rights reserved.</i>
	</footer>

</html>