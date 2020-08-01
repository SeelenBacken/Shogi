<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
	    <title>Spielbrett</title>
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.0/css/bulma.css">
		<link rel="stylesheet" type="text/css" href="../css/MainStyle.css">
		<link rel="stylesheet" type="text/css" href="../css/BoardStyle.css">
		<link rel="stylesheet" type="text/css" href="../css/TraditionalPieceStyle.css" id="styleBoard">
	</head>
	
	<body>
		<br>
		<b style="font-size: 40pt; text-align: center;"><c:out value="${g.getBlack().getName().equals('AI') ? g.getWhite().getName() : g.getBlack().getName()}"></c:out> vs. Com</b>
		
		<section class="section">
		
			<jsp:useBean id="g" class="beans.GameBean" scope="session" />
		
			<div style="margin-bottom: 30px;">
		    
		    	<form method="GET" action="/Shogi/Controller">	
		        
			        <table>
			        
			        	<tr>
			        		<th><h3><b>Verbessern Sie eine Figur (optional)</b></h3><br></th>
			        		<th colspan="2"  style="padding-left: 20px;"><h3><b>Bewegen Sie eine Figure (nötig)</b></h3><br></th>
			        		<th><h3><b>Gefangene einsetzen:</b></h3><br></th>
			        	</tr>
			        	<tr>
			        		<td><h4>Position:</h4></td>
				        	<td style="padding-left: 20px;"><h4>Ausgangsposition:</h4></td>
				        	<td style="padding-left: 20px;"><h4>Zielposition:</h4></td>
				        	<td rowspan="4" style="padding-left: 20px;">
				        		
				        		<div>
									
									<p>Geben Sie Z als X-Wert für die Ausgangsposition an.<br>Für den Y-Wert geben Sie die unten angezeigte Nummer ein.</p>
									
									<br>
									
									<table style="border: none;">
									
										<tr>
										
											<td>-1: </td><td><div class="piece p1Piece BISHOP"></div></td>
											<td>-2: </td><td><div class="piece p1Piece TOWER"></div></td>
											<td>-3: </td><td><div class="piece p1Piece GOLDEN_GENERAL"></div></td>
											<td>-4: </td><td><div class="piece p1Piece SILVER_GENERAL"></div></td>
											<td>-5: </td><td><div class="piece p1Piece KNIGHT"></div></td>
											<td>-6: </td><td><div class="piece p1Piece PAWN"></div></td>
											<td>-7: </td><td><div class="piece p1Piece LANCE"></div></td>
										
										</tr>
									
									</table>
										
								</div>
				        		
				        	</td>
			        	</tr>
			        	<tr>
			        		<td>
			        			<label for="Xc">X-Ordinate</label>	
				        		<input name="Xc" value="Z" type="text" maxlength="1" pattern="[A-I, Z]" />
			        		</td>
			        		<td style="padding-left: 20px;">
			        			<label for="Xh">X-Ordinate</label>
			       				<input id="xstart" name="Xh" type="text" required="required" maxlength="2" pattern="-?[A-I, Z]" >
			        		</td>
			        		<td style="padding-left: 20px;">
			        			<label for="Xt">X-Ordinate</label>
			        			<input name="Xt" type="text" required="required" maxlength="1" pattern="[A-I]" >
			        		</td>
			        	</tr>
			        	<tr>
			        		<td>
			        			<label for="Yc">Y-Ordinate</label>
								<input name="Yc" value="-1" type="text" maxlength="2" pattern="-?[0-8]{1}" />
			        		</td>
			        		<td style="padding-left: 20px;">
			        			<label for="Yh">Y-Ordinate</label>
								<input id="ystart" name="Yh" type="text" required="required" maxlength="2" pattern="-?[0-8]{1}" >
			        		</td>
			        		<td style="padding-left: 20px;">
			        			<label for="Yt">Y-Ordinate</label>
								<input name="Yt" type="text" required="required" maxlength="2" pattern="-?[0-8]{1}" ><br>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td colspan="3" style="padding-top: 20px;">
			        			<input name="submitMove" style="width: 300px; height: 60px; padding: 1px;" type="submit" class="button" />
			        			<input type="button" onclick = "changeStyle()" value="Ansicht wechseln" class="button" style="margin-left: 20px;  height: 60px; padding: 1px;" />
			        		</td>
			        	</tr>
			        
			        </table>
			        		        
				</form>
		    
		    </div>
		
		    <div class="playContainer container" draggable="false" style="padding-left: 15em; padding-top: 1em; padding-bottom: 1em;">
 
 				<table class="playfield" draggable="false">
			     
		            <c:forEach begin="0" end="8" var="i" >
		            
		            	<tr>
		            	
		            		<th><c:out value="${8 - i}" /></th>
		            		
		            		<c:forEach begin="0" end="8" var="j">
		                		
		                		<td id="${String.valueOf('ABCDEFGHI').charAt(j)}${8 - i}">
		                			
		                			<c:if test = "${g.getBoard().getBoard()[j][8 - i] != null}">
			                			
			                			<div class="piece p${g.getPlayerTeam(j,8 - i)}Piece ${g.getBoard().getBoard()[j][8 - i].getType()}" ></div>
		                			
		                			</c:if>
		                		
		                		</td>
		                		
		            		</c:forEach>
		            	
		            	</tr>
		            	
		            </c:forEach>
		            
		            <tr>
		                <th></th>
		                <th>A</th>
		                <th>B</th>
		                <th>C</th>
		                <th>D</th>
		                <th>E</th>
		                <th>F</th>
		                <th>G</th>
		                <th>H</th>
		                <th>I</th>
		            </tr>
		            
		        </table> 
 			
		        <div class="graveyard">
		            <div id="gravep2" class="player2">
		                <nav class="level gLevel">
		                    <div class="level-item">
		                        <div id="knight2" class="gravepiece KNIGHT"><c:out value="${g.getPlayerPrisonCountOfFigureType(2, 'KNIGHT')}" /></div>
		                    </div>
		                    <div class="level-item">
		                        <div id="pawn2" class="gravepiece PAWN"><c:out value="${g.getPlayerPrisonCountOfFigureType(2, 'PAWN')}" /></div>
		                    </div>
		                    <div class="level-item">
		                        <div id="lance2" class="gravepiece LANCE"><c:out value="${g.getPlayerPrisonCountOfFigureType(2, 'LANCE')}" /></div>
		                    </div>
		                </nav>
		                <nav class="level">
		                    <div class="level-item">
		                        <div id="gold2" class="gravepiece GOLDEN_GENERAL"><c:out value="${g.getPlayerPrisonCountOfFigureType(2, 'GOLDEN_GENERAL')}" /></div>
		                    </div>
		                    <div class="level-item">
		                        <div id="silver2" class="gravepiece SILVER_GENERAL"><c:out value="${g.getPlayerPrisonCountOfFigureType(2, 'SILVER_GENERAL')}" /></div>
		                    </div>
		                </nav>
		                <nav class="level">
		                    <div class="level-item">
		                    
		                        <div id="bishop2" class="gravepiece BISHOP"><c:out value="${g.getPlayerPrisonCountOfFigureType(2, 'BISHOP')}" /></div>
		                    </div>
		                    <div class="level-item">
		                        <div id="rook2" class="gravepiece TOWER"><c:out value="${g.getPlayerPrisonCountOfFigureType(2, 'TOWER')}" /></div>
		                    </div>
		                </nav>
		            </div>
		            <div id="gravep1" class="player1">
		                <nav class="level gLevel">
		                    <div class="level-item">
		                        <div id="bishop1" class="gravepiece BISHOP" draggable="true" ondragstart="drag(event)"><c:out value="${g.getPlayerPrisonCountOfFigureType(1, 'BISHOP')}" /></div>
		                    </div>
		                    <div class="level-item">
		                        <div id="rook1" class="gravepiece TOWER" draggable="true" ondragstart="drag(event)"><c:out value="${g.getPlayerPrisonCountOfFigureType(1, 'TOWER')}" /></div>
		                    </div>
		                </nav>
		                <nav class="level">
		                    <div class="level-item">
		                        <div id="gold1" class="gravepiece GOLDEN_GENERAL" draggable="true" ondragstart="drag(event)"><c:out value="${g.getPlayerPrisonCountOfFigureType(1, 'GOLDEN_GENERAL')}" /></div>
		                    </div>
		                    <div class="level-item">
		                        <div id="silver1" class="gravepiece SILVER_GENERAL" draggable="true" ondragstart="drag(event)"><c:out value="${g.getPlayerPrisonCountOfFigureType(1, 'SILVER_GENERAL')}" /></div>
		                    </div>
		                </nav>
		                <nav class="level">
		                    <div class="level-item">
		                        <div id="knight1" class="gravepiece KNIGHT" draggable="true" ondragstart="drag(event)"><c:out value="${g.getPlayerPrisonCountOfFigureType(1, 'KNIGHT')}" /></div>
		                    </div>
		                    <div class="level-item">
		                        <div id="pawn1" class="gravepiece PAWN" draggable="true" ondragstart="drag(event)"><c:out value="${g.getPlayerPrisonCountOfFigureType(1, 'PAWN')}" /></div>
		                    </div>
		                    <div class="level-item">
		                        <div id="lance1" class="gravepiece LANCE" draggable="true" ondragstart="drag(event)"><c:out value="${g.getPlayerPrisonCountOfFigureType(1, 'LANCE')}" /></div>
		                    </div>
		                </nav>
		            </div>
		        </div>		        
		    </div>
		    
		</section>
		
		<script>
		
			function changeStyle(){
				var aktuellerStyle = document.getElementById("styleBoard");
				if(aktuellerStyle.getAttribute("href") === "../css/TraditionalPieceStyle.css"){
					document.getElementById("styleInfo").innerhtml = "../css/ModernPieceStyle.css";
					return aktuellerStyle.setAttribute("href", "../css/ModernPieceStyle.css");  
				}
				if(aktuellerStyle.getAttribute("href") === "../css/ModernPieceStyle.css"){
					document.getElementById("styleInfo").innerhtml = "../css/TraditionalPieceStyle.css";
					return aktuellerStyle.setAttribute("href", "../css/TraditionalPieceStyle.css");  
				}
			}
		    
		</script>

	
	</body>

	<footer>

	    <a href = "../jsp/Impressum.jsp" class="footer-buttons">Impressum</a>
		<a href = "../jsp/Instructions.jsp" class="footer-buttons">Anleitung</a>
		
		<i style="font-size:15px; padding: 2px; float: right"> ©2020 DHBW Karlsruhe All rights reserved.</i>
	
	</footer>

</html>