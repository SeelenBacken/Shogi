<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Einstellungen</title>
	<link rel="stylesheet" type="text/css" href="../css/MainStyle.css">
</head>

<body>

<script>

</script>

<header>
	<div id = "title">
        <h1>Shogi mit Einstellungen</h1>
    </div>
</header>

<jsp:include page="board.jsp">
	<jsp:param name="param1" value="../css/TraditionalBoardStyle.css"/>
</jsp:include>
   
    
<div id = "content">
	 <br><br><br><br><br><br>
	Welche Spielsteine bevorzugen Sie?
	<form>
		<button>Traditionell</button>
	</form>
    
</div>

<footer>
	
    <a href = "../jsp/Impressum.jsp" class="footer-buttons">Impressum</a>
	<a href = "../jsp/Instructions.jsp" class="footer-buttons">Anleitung</a>
	
	<i style="font-size:15px; padding: 2px; float: right"> ©2020 DHBW Karlsruhe All rights reserved.</i>
</footer>


</body>
</html>