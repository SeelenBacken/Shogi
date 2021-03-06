<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>

	<head>
	
		<meta charset="UTF-8">
		<title>Instructions</title>
		<link rel = "stylesheet" type = "text/css" href = "../css/MainStyle.css">
    	<link rel = "stylesheet" type = "text/css" href = "../css/InstructionStyle.css">
    	<link rel = "stylesheet" type = "text/css" href = "../css/TraditionalPieceStyle.css" id="styleBoard">
	
	</head>
	
	<body>
	
		<header>
			<div id = "title">
				<h1>将棋 - Shogi Spielanleitung</h1>
			</div>
		</header>
		
		<div id = "content">
			<h3>Grundregeln</h3>
			Shogi ist ein strategigsches Spiel für zwei Personen, welches dem Schachspiel ähnelt. Gespielt wird auf einem Brett mit 9x9 Feldern. Die Spieler ziehen
			abwechselnd eine ihrer Figuren mit dem Ziel, den gegnerischen König zu schlagen. Wird der König bedroht, steht dieser im Schach und muss im nächsten Zug
			den Angriff auf den König abwehren. Die Figuren weisen unterschiedliche Bewegungsmuster auf, welche ebenfalls auf dieser Website beschrieben werden. Die 
			Richtung, in die der Stein zeigt, gibt den Spieler an, dem der Stein gehört.
			
			<br>
			
			<h3>Sonderregeln</h3>
			Schlägt ein Spieler eine gegnerische Figur ist diese nicht aus dem Spiel, sondern wird gefangen genommen. In einem
			späteren Zug kann der Spieler anstatt eine seiner Figuren zu bewegen, eine gefangene Figur beliebig auf einem freien Feld auf dem Spielbrett einsetzen. 
			Durch das Einsetzen einer Figur ist es dem Spieler erlaubt den Gegner Schach zu setzen. Nur ein Bauer darf beim Einsetzen den Gegner nicht Matt setzen.
			Zudem dürfen sich nicht zwei eigene Bauern in einer Linie befinden. Eingesetzte Figuren müssen sich in weiteren Zügen bewegen können, also kann man zum
			Beispiel keine Bauern direkt in die letzte Reihe setzen.
			
			<br><br>
			
			Ein weitere Spielmechanik ist das Befördern von Figuren. Erreicht eine eigene Figur die Basis des Gegners, also eine der letzten drei Reihen, kann die Figur am Ende
			des Zuges befördert werden. Dadurch ergeben sich neue Zugmuster für die Figur. Kann die Figur sich am Ende des Zuges nicht weiter bewegen, zum Beispiel wenn
			ein Bauer die letzte Reihe erreicht, muss diese befördert werden. Die Beförderung wird erst wieder rückgängig gemacht, wenn sie geschlagen wird. Eingesetzte 
			Figuren sind nicht befördert und können erst im folgenden Zug wieder befördert werden.
			
			<br><br>
			
			<h3>Spielfiguren</h3>
			
			<br>
			<input type="submit" onclick = "changeStyle()" value="Ansicht wechseln" class="inputStyle" />
			
			<select name = "piece" id="piece" onchange="showTable()">
				<option value = "pawn">Bauer</option>
				<option value = "silver">Silberner General</option>
				<option value = "gold">Goldener General</option>
				<option value = "lance">Lanze</option>
				<option value = "bishop">Läufer</option>
				<option value = "rook">Turm</option>
				<option value = "knight">Springer</option>
				<option value = "king">König</option>
			</select>
		</div>
		
		<br><br>
		
		<div id = "tables">
		
			<table id = "tablePawn">
				<caption>Bauer</caption>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td class="PAWN"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		
			<table id = "tableTokin">
				<caption>Befördert: Vergoldeter</caption>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td class="TOKIN"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		
			<table id = "tableSilver">
				<caption>Silberner General</caption>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td class="SILVER_GENERAL"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		
			<table id = "tableProsilver">
				<caption>Beförderter Silber</caption>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td class="PROSILVER"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			
			<table id = "tableGold">
				<caption>Goldener General</caption>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td class="GOLDEN_GENERAL"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			
			
			<table id = "tableLance">
				<caption>Lanze</caption>
				<tr>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td class="LANCE"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			
			<table id = "tableProlance">
				<caption>Beförderte Lanze</caption>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td class="PROLANCE"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			
			<table id = "tableBishop">
				<caption>Läufer</caption>
				<tr>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td ></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td class="BISHOP"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
				</tr>
			</table>
			
			<table id = "tableHorse">
				<caption>Befördert: Pferd</caption>
				<tr>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td class="HORSE"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
				</tr>
			</table>
			
			<table id = "tableRook">
				<caption>Turm</caption>
				<tr>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td class="TOWER"></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			
			<table id = "tableDragon">
				<caption>Befördert: Drache</caption>
				<tr>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td class="DRAGON"></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
				</tr>
			</table>
	
			<table id = "tableKnight">
				<caption>Springer</caption>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td class="KNIGHT"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			
			<table id = "tableProknight">
				<caption>Beförderter Springer</caption>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td class="PROKNIGHT"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td style="background-color:white;"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			
			
			<table id = "tableKing">
				<caption>König</caption>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td class="KING"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td style="background-color:white;"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
            
		</div>

		
		
		<footer>
			<a href = "../jsp/Impressum.jsp" class="footer-buttons">Impressum</a>
			<a href = "../jsp/Instructions.jsp" class="footer-buttons">Anleitung</a>
			<i style="font-size:15px; padding: 2px; float: right"> ©2020 DHBW Karlsruhe All rights reserved.</i>
		</footer>
		
		<script>
			showTable();
			
			function changeStyle(){
				var aktuellerStyle = document.getElementById("styleBoard");
				if(aktuellerStyle.getAttribute("href") === "../css/TraditionalPieceStyle.css"){
					return aktuellerStyle.setAttribute("href", "../css/ModernPieceStyle.css");  
				}
				if(aktuellerStyle.getAttribute("href") === "../css/ModernPieceStyle.css"){
					return aktuellerStyle.setAttribute("href", "../css/TraditionalPieceStyle.css");  
				}
			}
			
			function showTable()
			{
				hideAll();
				dropdown = document.getElementById("piece");
				
				switch (document.getElementById("piece").value)
				{
					case "pawn":
						document.getElementById("tablePawn").style.display = "inline-block";
						document.getElementById("tableTokin").style.display = "inline-block";
						break;
						
					case "silver":
						document.getElementById("tableSilver").style.display = "inline-block";
						document.getElementById("tableProsilver").style.display = "inline-block";
						break;
						
					case "gold":
						document.getElementById("tableGold").style.display = "inline-block";
						break;
						
					case "lance":
						document.getElementById("tableLance").style.display = "inline-block";
						document.getElementById("tableProlance").style.display = "inline-block";
						break;
						
					case "bishop":
						document.getElementById("tableBishop").style.display = "inline-block";
						document.getElementById("tableHorse").style.display = "inline-block";
						document.getElementById("opBishop").style.background = "white";
						break;
						
					case "rook":
						document.getElementById("tableRook").style.display = "inline-block";
						document.getElementById("tableDragon").style.display = "inline-block";
						break;
						
					case "knight":
						document.getElementById("tableKnight").style.display = "inline-block";
						document.getElementById("tableProknight").style.display = "inline-block";
						break;
						
					case "king":
						document.getElementById("tableKing").style.display = "inline-block";
				}
			}
			
			function hideAll()
			{
				document.getElementById("tablePawn").style.display = "none";
				document.getElementById("tableTokin").style.display = "none";
				document.getElementById("tableSilver").style.display = "none";
				document.getElementById("tableProsilver").style.display = "none";
				document.getElementById("tableGold").style.display = "none";
				document.getElementById("tableLance").style.display = "none";
				document.getElementById("tableProlance").style.display = "none";
				document.getElementById("tableBishop").style.display = "none";
				document.getElementById("tableHorse").style.display = "none";
				document.getElementById("tableRook").style.display = "none";
				document.getElementById("tableDragon").style.display = "none";
				document.getElementById("tableKnight").style.display = "none";
				document.getElementById("tableProknight").style.display = "none";
				document.getElementById("tableKing").style.display = "none";
			}
			
		</script>
	
	</body>
	
</html>