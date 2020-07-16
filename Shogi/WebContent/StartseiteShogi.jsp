<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Start playing Shogi</title>
<link rel = "stylesheet" type = "text/css" href = "ShogiStyle.css">
</head>
<body>
<div id = "title">
	<h1>Herzlich Willkommen bei Shogi</h1>
</div>

<div id = "content">
	<form method="get" action="board.jsp">
		<input id="button" type="submit" value="Singleplayer"/><br><br>
	</form>	
</div>
	
<div id = "settings">
	<form method="get" action="settings.jsp">
		<input id="button" type="submit" value="Einstellungen"/><br><br>
	</form>	
</div>

	
<div id = "impressum">
	<br>
	<strong>
		<a href = "ImpressumShogi.jsp">Impressum</a>
		<a href = "AnleitungShogi.jsp">Anleitung</a>
	</strong>
	<br><br>
</div>

</body>
</html>
