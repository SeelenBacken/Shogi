<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Einstellungen</title>
</head>

<body>

<script>

</script>

<div id = "title">
	<h1>Einstellungen</h1>
</div>

<jsp:include page="board.jsp">
	<jsp:param name="param1" value="TraditionalBoard.css"/>
</jsp:include>
   
    
<div id = "content">
	 
	Welche Spielsteine bevorzugen Sie?
	<form>
		<button>Traditionell</button>
	</form>
    
</div>
	


</body>
</html>