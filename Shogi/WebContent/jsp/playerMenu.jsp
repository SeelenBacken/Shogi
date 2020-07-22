<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>

	<head>
	
	    <meta charset="UTF-8">
	    <title>Spieler Menu</title>
	    <link rel="stylesheet" type="text/css" href="../css/MainStyle.css">
        <link rel="stylesheet" type="text/css" href="../css/PlayerMenuStyle.css">
	
	</head>
	
	<body>
	
		<jsp:useBean id="g" class="beans.GameBean" scope="session" />
	
	    <div id = "title">
	        <h1>Geben Sie ihren Usernamen ein:</h1>
	    </div>
	
	    <div id="content" class="form__group field">
	    	
	    	<form method="GET" action="http://localhost:8080/Shogi/Controller" name="form">
				
				<table>
	    			<tr>
	    				<td colspan="2"><input id="PlayerName" type="text" class="form__field" placeholder="Username" name="Username" required="required" /></td>
	    			</tr>
	    			<tr>
	    				<td colspan="2">&nbsp;</td>
	    			</tr>
	    			<tr>
	    				<td><label>Weiß:   </label><input type="radio" name="PlayerColor" required="required" value="Weiß" /></td>
	    				<td><label>Schwarz:   </label><input type="radio" name="PlayerColor" required="required" value="Schwarz" /></td>
	    			</tr>
	    			<tr>
	    				<td colspan="2">&nbsp;</td>
	    			</tr>
	    			<tr>
	    				<td colspan="2"><input type="submit" class="Button" /></td>
	    			</tr>
	    		</table>
		
			</form>
			
	    </div>
	
	</body>
	
	<footer>
	
	    <a href = "ImpressumShogi.jsp" class="footer-buttons">Impressum</a>
		<a href = "AnleitungShogi.jsp" class="footer-buttons">Anleitung</a>
		
		<i style="font-size:15px; padding: 2px; float: right"> ©2020 DHBW Karlsruhe All rights reserved.</i>
	</footer>

</html>