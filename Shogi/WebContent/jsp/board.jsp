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
		    
		    	<form method="GET" action="http://localhost:8080/Shogi/Controller">	
		        
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
		                		
		                		<td id="${String.valueOf('ABCDEFGHI').charAt(j)}${8 - i}" ondragover="allowDrop(event)" ondrop="drop(event)">
		                			
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
			
		    let fields = document.querySelectorAll('.playfield tr td')
		    let pieceCounter = {
		        king: 3,
		        bishop: 3,
		        gold: 3,
		        silver: 3,
		        knight: 3,
		        lance: 3,
		        rook: 3,
		        pawn: 3,
		    }
		    
		    function changeStyle(){
				var aktuellerStyle = document.getElementById("styleBoard");
				if(aktuellerStyle.getAttribute("href") === "../css/TraditionalPieceStyle.css"){
					return aktuellerStyle.setAttribute("href", "../css/ModernPieceStyle.css");  
				}
				if(aktuellerStyle.getAttribute("href") === "../css/ModernPieceStyle.css"){
					return aktuellerStyle.setAttribute("href", "../css/TraditionalPieceStyle.css");  
				}
			}
		    
		    function allowDrop(ev) {
		        ev.preventDefault();
		    }
		    
		    function drag(ev) {
		    	
		    	var xs = "Z";
		    	var ys;
		    	var id = ev.target.id;
		    	
		    	if(id == "bishop1"){
		    		ys = -1;
		    	} else if(id == "rook1"){
		    		ys = -2;
		    	} else if(id == "gold1"){
		    		ys = -3;
		    	} else if(id == "silver1"){
		    		ys = -4;
		    	} else if(id == "knight1"){
		    		ys = -5;
		    	} else if(id == "pawn1"){
		    		ys = -6;
		    	} else if(id == "lance1"){
		    		ys = -7;
		    	} 
		    	
		    	document.getElementById("xstart").innerHTML = xs;
		    	document.getElementById("ystart").innerHTML = ys;
		    	
		        ev.dataTransfer.setData("text/plain", ev.target.id);
		        
		        fields.forEach(function (field) {
		        	console.log(field.innerHTML);
		        	if(field.innerHTML == "") {
		        		console.log("empty");
		        		field.classList.add('pHighlight');
		            }
		        });
		    }
		    
		    function drop(ev) {
		    	
		        ev.preventDefault();
		        let target = document.getElementById(ev.target.id);
		        let hitter = document.getElementById(ev.dataTransfer.getData('text'));
		        if(!checkTarget(target, hitter)){
		        	return false;
		        }
		        movePiece(ev, target, hitter);
		        fields.forEach(function (field) {
		        	field.classList.remove('pHighlight');
		        })
		       
		    }
		    
		    function checkTarget(target, hitter) {
		    	if(target === hitter){
		    		return false;
		        } else if(target.classList.contains('p1Piece') && hitter.classList.contains('p1Piece')) {
		        	return;
		        } else if (target.hasChildNodes()) {
		        	if(target.firstChild.classList.contains("p1Piece") && hitter.classList.contains("p1Piece")) {
		        		alert("5");
		        		return;
		            }
		        } else 
		        	return true;
		    }
		    
		    function movePiece(ev, target, hitter) {
		        if(target.hasChildNodes()){
		        	target.firstChild.remove();
		        	target.appendChild(hitter);
		        }else if(target.classList.contains('piece')){
		        	target.parentNode.append(hitter);
		        	hit(target, hitter);
		        }else{
		        	ev.target.appendChild(hitter);
		        }
		    }
		    
		    function newKing(playerNr) {
		        let piece = document.createElement('div');
		        piece.classList.add('piece');
		        piece.classList.add('KING');
		        piece.classList.add('p' + playerNr + 'Piece');
		        piece.id = 'king' + pieceCounter.king;
		        pieceCounter.king ++;
		        piece.draggable = true;
		        piece.setAttribute("ondragstart", "drag(event)");
		        return piece;
		    }
		    
		    function newBishop(playerNr) {
		        let piece = document.createElement('div');
		        piece.classList.add('piece');
		        piece.classList.add('BISHOP');
		        piece.classList.add('p' + playerNr + 'Piece');
		        piece.id = 'bishop' + pieceCounter.bishop;
		        pieceCounter.bishop ++;
		        piece.draggable = true;
		        piece.setAttribute("ondragstart", "drag(event)");
		        return piece;
		    }
		    
		    function newGold(playerNr) {
		        let piece = document.createElement('div');
		        piece.classList.add('piece');
		        piece.classList.add('GOLDEN_GENERAL');
		        piece.classList.add('p' + playerNr + 'Piece');
		        piece.id = 'gold' + pieceCounter.gold;
		        pieceCounter.gold ++;
		        piece.draggable = true;
		        piece.setAttribute("ondragstart", "drag(event)");
		        return piece;
		    }
		    
		    function newSilver(playerNr) {
		        let piece = document.createElement('div');
		        piece.classList.add('piece');
		        piece.classList.add('SILVER_GENERAL');
		        piece.classList.add('p' + playerNr + 'Piece');
		        piece.id = 'silver' + pieceCounter.silver;
		        pieceCounter.silver ++;
		        piece.draggable = true;
		        piece.setAttribute("ondragstart", "drag(event)");
		        return piece;
		    }
		    
		    function newKnight(playerNr) {
		        let piece = document.createElement('div');
		        piece.classList.add('piece');
		        piece.classList.add('KNIGHT');
		        piece.classList.add('p' + playerNr + 'Piece');
		        piece.id = 'knight' + pieceCounter.knight;
		        pieceCounter.knight ++;
		        piece.draggable = true;
		        piece.setAttribute("ondragstart", "drag(event)");
		        return piece;
		    }
		    
		    function newLance(playerNr) {
		        let piece = document.createElement('div');
		        piece.classList.add('piece');
		        piece.classList.add('LANCE');
		        piece.classList.add('p' + playerNr + 'Piece');
		        piece.id = 'lance' + pieceCounter.lance;
		        pieceCounter.lance ++;
		        piece.draggable = true;
		        piece.setAttribute("ondragstart", "drag(event)");
		        return piece;
		    }
		    
		    function newRook(playerNr) {
		        let piece = document.createElement('div');
		        piece.classList.add('piece');
		        piece.classList.add('TOWER');
		        piece.classList.add('p' + playerNr + 'Piece');
		        piece.id = 'rook' + pieceCounter.rook;
		        pieceCounter.rook ++;
		        piece.draggable = true;
		        piece.setAttribute("ondragstart", "drag(event)");
		        return piece;
		    }
		    
		    function newPawn(playerNr) {
		        let piece = document.createElement('div');
		        piece.classList.add('piece');
		        piece.classList.add('PAWN');
		        piece.classList.add('p' + playerNr + 'Piece');
		        piece.id = 'pawn' + pieceCounter.pawn;
		        pieceCounter.pawn ++;
		        piece.draggable = true;
		        piece.setAttribute("ondragstart", "drag(event)");
		        return piece;
		    }
		    
		    function hit(target, hitter){
		        if(target.classList.contains("BISHOP")){
		            let bishop;
		            if(target.classList.contains("p1Piece")){
		                bishop = document.querySelector('#bishop2');
		            } else if (target.classList.contains("p2Piece")){
		                bishop = document.querySelector('#bishop1');
		            }
		            let count = bishop.innerHTML;
		            count ++;
		            bishop.innerHTML = count;
		        } else if (target.classList.contains("PAWN")){
		            let pawn;
		            if(target.classList.contains("p1Piece")){
		                pawn = document.querySelector('#pawn2');
		            } else if (target.classList.contains("p2Piece")){
		                pawn = document.querySelector('#pawn1');
		            }
		            let count = pawn.innerHTML;
		            count ++;
		            pawn.innerHTML = count;
		        } else if(target.classList.contains("GOLDEN_GENERAL")){
		            let gold;
		            if(target.classList.contains("p1Piece")){
		                gold = document.querySelector('#gold2');
		            } else if (target.classList.contains("p2Piece")){
		                gold = document.querySelector('#gold1');
		            }
		            let count = gold.innerHTML;
		            count ++;
		            gold.innerHTML = count;
		        } else if(target.classList.contains("SILVER_GENERAL")){
		            let silver;
		            if(target.classList.contains("p1Piece")){
		                silver = document.querySelector('#silver2');
		            } else if (target.classList.contains("p2Piece")){
		                silver = document.querySelector('#silver1');
		            }
		            let count = silver.innerHTML;
		            count ++;
		            silver.innerHTML = count;
		        } else if(target.classList.contains("KNIGHT")){
		            let knight;
		            if(target.classList.contains("p1Piece")){
		                knight = document.querySelector('#knight2');
		            } else if (target.classList.contains("p2Piece")){
		                knight = document.querySelector('#knight1');
		            }
		            let count = knight.innerHTML;
		            count ++;
		            knight.innerHTML = count;
		        } else if(target.classList.contains("LANCE")){
		            let lance;
		            if(target.classList.contains("p1Piece")){
		                lance = document.querySelector('#lance2');
		            } else if (target.classList.contains("p2Piece")){
		                lance = document.querySelector('#lance1');
		            }
		            let count = lance.innerHTML;
		            count ++;
		            lance.innerHTML = count;
		        } else if(target.classList.contains("TOWER")){
		            let rook;
		            if(target.classList.contains("p1Piece")){
		                rook = document.querySelector('#rook2');
		            } else if (target.classList.contains("p2Piece")){
		                rook = document.querySelector('#rook1');
		            }
		            let count = rook.innerHTML;
		            count ++;
		            rook.innerHTML = count;
		        }
		        target.remove();
		    }
			
		    
		</script>

	
	</body>

	<footer>

	    <a href = "../jsp/Impressum.jsp" class="footer-buttons">Impressum</a>
		<a href = "../jsp/Instructions.jsp" class="footer-buttons">Anleitung</a>
		
		<i style="font-size:15px; padding: 2px; float: right"> ©2020 DHBW Karlsruhe All rights reserved.</i>
	
	</footer>

</html>