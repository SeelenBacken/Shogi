<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Einstellungen</title>
<link rel = "stylesheet" type = "text/css" href = "ShogiStyle.css">
</head>
<body>

<style>
.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
}

.switch input { 
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}

input:checked + .slider {
  background-color: #2196F3;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196F3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}
</style>

<div id = "title">
	<h1>Einstellungen</h1>
</div>

<div id = "content">
	
	Traditionelle Steine
	<form>
		<label class="switch">
			<input type="checkbox" checked>
			<span class="slider round"></span>
		</label>
	</form>	
</div>
	
<div id = "impressum">
	<br>
	<strong>
		<a href = "ImpressumShogi.jsp">Impressum</a>
		<a href = "AnleitungShogi.jsp">Anleitung</a>
		<a href = "StartseiteShogi.jsp">Startseite</a>
	</strong>
	<br><br>
</div>

</body>
</html>
