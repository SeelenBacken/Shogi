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
	<br><br><br><br>
	Sie kennen das Spiel noch nicht? 
	Kein Problem, hier finden Sie eine ausführliche <a href = "AnleitungShogi.jsp">Anleitung</a>!
	 <br>
	Sie wissen bereits wie alles funktioniert?
	Dann spielen Sie <a href = "board.jsp">hier</a> direkt los!
	<br><br>
	
</div>

<div id = "PlayButton">
	<form action = "board.jsp">
		<input type="button" name= "VerweisSpiel" value = "Play" onClick="board.jsp">
	</form>
</div>

<div id = "image">
	<br><br><br><br><br><br>
	<img src = "https://cdna.artstation.com/p/assets/images/images/024/079/214/large/
	ishan-verma-thumbnail.jpg?1581264210" width = "250" height = "250">

</div>
	
<div id = "impressum">
	<br>
	<a href = "ImpressumShogi.jsp"><strong>Impressum</strong></a>
	<br><br>
</div>

</body>
</html>