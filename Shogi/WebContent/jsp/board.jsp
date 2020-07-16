<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
<title>Spielbrett</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bulma@0.9.0/css/bulma.css">
<link rel="stylesheet" type="text/css" href="../css/MainStyle.css">
<link rel="stylesheet" type="text/css" href="../css/BoardStyle.css">
<!---<link rel="stylesheet" id="pagestyle" href="<%= request.getParameter("param1") %>">--->

</head>

<body>

	<section class="section">

		<jsp:useBean id="g" class="beans.GameBean" />

		<div class="playContainer container" draggable="false">
			<table class="playfield" draggable="false">
				<tr>
			         	
					<form method="GET" action="http://localhost:8080/Shogi/Controller">	
						<input name="undoMove" type="submit" value="Undo" class="Button" />
							
							<!-- Nur zu Test Zwecken.
							<input name="Xh" type="text" value="C" hidden="hidden">
							<input name="Yh" type="text" value="2" hidden="hidden">
							<input name="Xt" type="text" value="D" hidden="hidden">
							<input name="Yt" type="text" value="3" hidden="hidden">
							<input name="Xc" type="text" value="A" hidden="hidden">
							<input name="Yc" type="text" value="0" hidden="hidden">
							--> 
					</form>
				</tr>
		            
		            <c:forEach begin="1" end="9" var="i" >
		            
		            	<tr>
		            	
		            		<th><c:out value="${i}" /></th>
		            		
		            		<c:forEach begin="1" end="9" var="j">
		                		
		                		<td id="${String.valueOf('ABCDEFGHI').charAt(j - 1)}${i}" ondragover="allowDrop(event)" ondrop="drop(event)">
		                			
		                			<c:if test = "${g.getBoard().getBoard()[j - 1][i - 1] != null}">
			                			
			                			<div class="piece p${g.getPlayerTeam(j - 1,i - 1)}Piece ${g.getBoard().getBoard()[j - 1][i - 1].getType()}" onclick="upgrade(event, this.parentNode)" ondragstart="drag(event)" draggable="true" 
			                			></div>
		                			
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
							<div id="knight2" class="gravepiece KNIGHT" draggable="true"
								ondragstart="drag(event)">0</div>
						</div>
						<div class="level-item">
							<div id="pawn2" class="gravepiece PAWN" draggable="true"
								ondragstart="drag(event)">0</div>
						</div>
						<div class="level-item">
							<div id="lance2" class="gravepiece LANCE" draggable="true"
								ondragstart="drag(event)">0</div>
						</div>
					</nav>
					<nav class="level">
						<div class="level-item">
							<div id="gold2" class="gravepiece GOLDEN_GENERAL" draggable="true"
								ondragstart="drag(event)">0</div>
						</div>
						<div class="level-item">
							<div id="silver2" class="gravepiece SILVER_GENERAL" draggable="true"
								ondragstart="drag(event)">0</div>
						</div>
					</nav>
					<nav class="level">
						<div class="level-item">
							<div id="bishop2" class="gravepiece BISHOP" draggable="true"
								ondragstart="drag(event)">0</div>
						</div>
						<div class="level-item">
							<div id="rook2" class="gravepiece TOWER" draggable="true"
								ondragstart="drag(event)">0</div>
						</div>
					</nav>
				</div>
				<div id="gravep1" class="player1">
					<nav class="level gLevel">
						<div class="level-item">
							<div id="bishop1" class="gravepiece BISHOP" draggable="true"
								ondragstart="drag(event)">0</div>
						</div>
						<div class="level-item">
							<div id="rook1" class="gravepiece TOWER" draggable="true"
								ondragstart="drag(event)">0</div>
						</div>
					</nav>
					<nav class="level">
						<div class="level-item">
							<div id="gold1" class="gravepiece GOLDEN_GENERAL" draggable="true"
								ondragstart="drag(event)">0</div>
						</div>
						<div class="level-item">
							<div id="silver1" class="gravepiece SILVER_GENERAL" draggable="true"
								ondragstart="drag(event)">0</div>
						</div>
					</nav>
					<nav class="level">
						<div class="level-item">
							<div id="knight1" class="gravepiece KNIGHT" draggable="true"
								ondragstart="drag(event)">0</div>
						</div>
						<div class="level-item">
							<div id="pawn1" class="gravepiece PAWN" draggable="true"
								ondragstart="drag(event)">0</div>
						</div>
						<div class="level-item">
							<div id="lance1" class="gravepiece LANCE" draggable="true"
								ondragstart="drag(event)">0</div>
						</div>
					</nav>
				</div>
			</div>
		</div>

	</section>

	<script>
	
		var xc = "Z";
		var yc = -1;
		
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

	    function allowDrop(ev) {
	        ev.preventDefault();
	    }
	    function drag(ev) {
	    	
	        fields.forEach(function (field) {
	            field.classList.remove('pHighlight');
	        })
	        
	        ev.dataTransfer.setData("text", ev.target.id);
	        if (!ev.target.classList.contains("gravepiece")) {
	       		highlightForPiece(ev.target);
	        }
	        else {
	        	highlightForGravepiece(ev.target);
	        }
	    }
		
	    function drop(ev) {
	        ev.preventDefault();
	        let target = document.getElementById(ev.target.id);
	        let hitter = document.getElementById(ev.dataTransfer.getData('text'));
	        if(!checkTarget(target, hitter)){
	            fields.forEach(function (field) {
	                field.classList.remove('pHighlight');
	            })
	            return false;
	        }
	        movePiece(ev, target, hitter);
	        fields.forEach(function (field) {
	            field.classList.remove('pHighlight');
	        });
	        
	        let xh = hitter.parentElement.Id.charAt(0);
	        let yh = hitter.parentElement.Id.charAt(1);
	        let xt = target.parentElement.Id.charAt(0);
	        let yt = target.parentElement.Id.charAt(1);
	        location.assign("http://localhost:8080/Shogi/Controller?Xh=" + xh + "&Yh=" + yh + "&Xt=" + xt + "&Yt=" + yt + "&Xc=" + xc + "&Yc=" + yc);
	        
	    }
	    
	    function upgrade(event, parent){
	    	if(xc == "Z" && yc == -1){
				let id = parent.id;
		    	xc = id.charAt(0);
		    	yc = id.charAt(1);
			}
	    
	    function checkTarget(target, hitter) {
	    	let field = target;
	    	if (field.classList.contains("piece")){
	    		field = document.getElementById(coordinatesToId(getPieceCoordinates(target.id).x, getPieceCoordinates(target.id).y));
	    	}
	    	
	    	if (!field.classList.contains("pHighlight")) {
	    		return false;
	        } else return true;
	    }

        function summonPiece(target, gravePiece) {
            let playerNr = 0;
            if(gravePiece.parentNode.parentNode.parentNode.id == "gravep1") {
                playerNr = 1;
            }else if(gravePiece.parentNode.parentNode.parentNode.id == "gravep2") {
                playerNr = 2;
            }
            if(gravePiece.innerHTML >= 1) {
                gravePiece.innerHTML = gravePiece.innerHTML - 1;
                if (gravePiece.classList.contains('bishop')) {
                    target.appendChild(newBishop(playerNr));
                } else if (gravePiece.classList.contains("gold")) {
                    target.appendChild(newGold(playerNr));
                } else if (gravePiece.classList.contains("silver")) {
                    target.appendChild(newSilver(playerNr));
                } else if (gravePiece.classList.contains("knight")) {
                    target.appendChild(newKnight(playerNr));
                } else if (gravePiece.classList.contains("lance")) {
                    target.appendChild(newLance(playerNr));
                } else if (gravePiece.classList.contains("rook")) {
                    target.appendChild(newRook(playerNr));
                } else if (gravePiece.classList.contains("pawn")) {
                    target.appendChild(newPawn(playerNr));
                }

            }
        }

	    function movePiece(ev, target, hitter) {
            if(hitter.classList.contains('gravepiece')){
                summonPiece(target, hitter);
                return;
            }

            if(target.hasChildNodes()){
                target.firstChild.remove();
                target.appendChild(hitter);
                if(target.id.slice(1) <= 3) {
                    upgrade(hitter);
                }
            }else if(target.classList.contains('piece')){
                if(hitter.classList.contains("p1Piece")) {
                    if (target.parentNode.id.slice(1) >= 7) {
                        hitter.upgradeable = "true";
                    } else {
                        hitter.upgradeable = "false";
                    }
                } else if(hitter.classList.contains("p2Piece")) {
                    if(target.parentNode.id.slice(1) <= 3) {
                        hitter.upgradeable = "true";
                    } else {
                        hitter.upgradeable = "false";
                    }
                }
                target.parentNode.append(hitter);
                hit(target, hitter);
            }else{
                if(hitter.classList.contains("p1Piece")) {
                    if (target.id.slice(1) >= 7) {
                        hitter.upgradeable = "true";
                    } else {
                        hitter.upgradeable = "false";
                    }
                } else if(hitter.classList.contains("p2Piece")) {
                    if(target.id.slice(1) <= 3) {
                        hitter.upgradeable = "true";
                    } else {
                        hitter.upgradeable = "false";
                    }
                }
                ev.target.appendChild(hitter);
            }
	    }
	    
	    function newGame(board, wPrison, bPrison){
	    	if(board == null){
	    		//Player1
		        document.getElementById("A1").appendChild(newLance(1));
		        document.getElementById("B1").appendChild(newKnight(1));
		        document.getElementById("C1").appendChild(newSilver(1));
		        document.getElementById("D1").appendChild(newGold(1));
		        document.getElementById("E1").appendChild(newKing(1));
		        document.getElementById("F1").appendChild(newGold(1));
		        document.getElementById("G1").appendChild(newSilver(1));
		        document.getElementById("H1").appendChild(newKnight(1));
		        document.getElementById("I1").appendChild(newLance(1));
		        document.getElementById("B2").appendChild(newBishop(1));
		        document.getElementById("H2").appendChild(newRook(1));
		        document.getElementById("A3").appendChild(newPawn(1));
		        document.getElementById("B3").appendChild(newPawn(1));
		        document.getElementById("C3").appendChild(newPawn(1));
		        document.getElementById("D3").appendChild(newPawn(1));
		        document.getElementById("E3").appendChild(newPawn(1));
		        document.getElementById("F3").appendChild(newPawn(1));
		        document.getElementById("G3").appendChild(newPawn(1));
		        document.getElementById("H3").appendChild(newPawn(1));
		        document.getElementById("I3").appendChild(newPawn(1));
		        //Player2
		        document.getElementById("A9").appendChild(newLance(2));
		        document.getElementById("B9").appendChild(newKnight(2));
		        document.getElementById("C9").appendChild(newSilver(2));
		        document.getElementById("D9").appendChild(newGold(2));
		        document.getElementById("E9").appendChild(newKing(2));
		        document.getElementById("F9").appendChild(newGold(2));
		        document.getElementById("G9").appendChild(newSilver(2));
		        document.getElementById("H9").appendChild(newKnight(2));
		        document.getElementById("I9").appendChild(newLance(2));
		        document.getElementById("H8").appendChild(newBishop(2));
		        document.getElementById("B8").appendChild(newRook(2));
		        document.getElementById("A7").appendChild(newPawn(2));
		        document.getElementById("B7").appendChild(newPawn(2));
		        document.getElementById("C7").appendChild(newPawn(2));
		        document.getElementById("D7").appendChild(newPawn(2));
		        document.getElementById("E7").appendChild(newPawn(2));
		        document.getElementById("F7").appendChild(newPawn(2));
		        document.getElementById("G7").appendChild(newPawn(2));
		        document.getElementById("H7").appendChild(newPawn(2));
		        document.getElementById("I7").appendChild(newPawn(2));
	    	} else {
	    		board.foreach(foreach(placeFigure()));
	    	}
	    	
	    }
	    
	    function placeFigure(i, item, j){
	    	let rows = ["A", "B", "C", "D", "E", "F", "G", "H", "I"]
	    	
	    	document.getElementById(rows[i] + j).appendChild("new" + item.type + "(1)");
	    }
	    
	    function newKing(playerNr) {
	        let piece = document.createElement('div');
	        piece.classList.add('piece');
	        piece.classList.add('king');
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
	        piece.classList.add('bishop');
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
	        piece.classList.add('gold');
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
	        piece.classList.add('silver');
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
	        piece.classList.add('knight');
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
	        piece.classList.add('lance');
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
	        piece.classList.add('rook');
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
	        piece.classList.add('pawn');
	        piece.classList.add('p' + playerNr + 'Piece');
	        piece.id = 'pawn' + pieceCounter.pawn;
	        pieceCounter.pawn ++;
	        piece.draggable = true;
	        piece.setAttribute("ondragstart", "drag(event)");
	        return piece;
	    }
	    function hit(target, hitter){
	        if(target.classList.contains("bishop")){
	            let bishop;
	            if(target.classList.contains("p1Piece")){
	                bishop = document.querySelector('#bishop2');
	            } else if (target.classList.contains("p2Piece")){
	                bishop = document.querySelector('#bishop1');
	            }
	            let count = bishop.innerHTML;
	            count ++;
	            bishop.innerHTML = count;
	        } else if (target.classList.contains("pawn")){
	            let pawn;
	            if(target.classList.contains("p1Piece")){
	                pawn = document.querySelector('#pawn2');
	            } else if (target.classList.contains("p2Piece")){
	                pawn = document.querySelector('#pawn1');
	            }
	            let count = pawn.innerHTML;
	            count ++;
	            pawn.innerHTML = count;
	        } else if(target.classList.contains("gold")){
	            let gold;
	            if(target.classList.contains("p1Piece")){
	                gold = document.querySelector('#gold2');
	            } else if (target.classList.contains("p2Piece")){
	                gold = document.querySelector('#gold1');
	            }
	            let count = gold.innerHTML;
	            count ++;
	            gold.innerHTML = count;
	        } else if(target.classList.contains("silver")){
	            let silver;
	            if(target.classList.contains("p1Piece")){
	                silver = document.querySelector('#silver2');
	            } else if (target.classList.contains("p2Piece")){
	                silver = document.querySelector('#silver1');
	            }
	            let count = silver.innerHTML;
	            count ++;
	            silver.innerHTML = count;
	        } else if(target.classList.contains("knight")){
	            let knight;
	            if(target.classList.contains("p1Piece")){
	                knight = document.querySelector('#knight2');
	            } else if (target.classList.contains("p2Piece")){
	                knight = document.querySelector('#knight1');
	            }
	            let count = knight.innerHTML;
	            count ++;
	            knight.innerHTML = count;
	        } else if(target.classList.contains("lance")){
	            let lance;
	            if(target.classList.contains("p1Piece")){
	                lance = document.querySelector('#lance2');
	            } else if (target.classList.contains("p2Piece")){
	                lance = document.querySelector('#lance1');
	            }
	            let count = lance.innerHTML;
	            count ++;
	            lance.innerHTML = count;
	        } else if(target.classList.contains("rook")){
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

        function upgrade(ev, piece){
            ev.preventDefault();
            if(this.upgradeable == "true") {
                console.log("Upgrade");
            } else {
                console.log("No Upgrade")
            }
        }

	 	// Zugmöglichkeiten anzeigen
	    function numberToLetter(n)
	    {
	    	return "ABCDEFGHI".charAt(n - 1);
	    }
	 	
	    function coordinatesToId(x, y)
	    {
	    	return numberToLetter(x) + y;
	    }
	    
	    function highlightField(x,y,teamTwo)
	    {
	    	if ( (x >= 1) && (x <= 9) && (y >= 1) && (y <= 9) )
	    	{
	    		let field = document.querySelector("#" + coordinatesToId(x,y) + " div");
	    		if (field != null)
		    	if (teamTwo == field.classList.contains("p2Piece"))
	    		{
	    			return;
	    		}
	    		
	    		document.getElementById(coordinatesToId(x,y)).classList.add('pHighlight');
	    	}
	    }
	    
	    function getPieceCoordinates(piece)
	    {
	    	for (var x = 1; x <= 9; x += 1)
	    	{
	    		for (var y = 1; y <= 9; y += 1)
	   			{
	    			let field = document.querySelector("#" + coordinatesToId(x,y) + " div");
	    			if (field != null)
	   				if (field.id === piece)
	   				{
						return {x, y};
	   				}
	   			}
	    	}
	    	return null;
	    }
	    
	    function highlightDirection(x, y, dirX,dirY, teamTwo)
	    {
	    	if ( (x >= 1) && (x <= 9) && (y >= 1) && (y <= 9) )
	    	{
	    		highlightField(x,y, teamTwo);
		   		if (document.getElementById(coordinatesToId(x,y)).innerHTML == "")
		   		{
		   			
		   			highlightDirection(x + dirX, y + dirY, dirX, dirY, teamTwo);
		   		}
	    	}
	    }
	    
	    function highlightForPiece(piece)
	    {
	    	let coordinates = getPieceCoordinates(piece.id);
	    	let yAxis = 1;
	    	let teamTwo = false;
	    	if (piece.classList.contains("p2Piece"))
	    	{
	    		yAxis = -1;
	    		teamTwo = true;
	    	}
	    	
	    	if (piece.classList.contains("pawn"))
	   		{
	   			highlightField(coordinates.x, coordinates.y + (1 * yAxis), teamTwo);
	   		}
	    	if (piece.classList.contains("silver"))
	   		{
	    		highlightField(coordinates.x, coordinates.y + (1 * yAxis), teamTwo);
	    		highlightField(coordinates.x - 1, coordinates.y + (1 * yAxis), teamTwo);
	    		highlightField(coordinates.x + 1, coordinates.y + (1 * yAxis), teamTwo);
	    		highlightField(coordinates.x - 1, coordinates.y + ((-1) * yAxis), teamTwo);
	    		highlightField(coordinates.x + 1, coordinates.y + ((-1) * yAxis), teamTwo);
	   		}
	    	if ( (piece.classList.contains("gold")) 
	    			|| (piece.classList.contains("proSilver"))
	    			|| (piece.classList.contains("proKnight"))
	    			|| (piece.classList.contains("proLance"))
	    			|| (piece.classList.contains("tokin")) )
	   		{
	    		highlightField(coordinates.x, coordinates.y + (1 * yAxis), teamTwo);
	    		highlightField(coordinates.x - 1, coordinates.y + (1 * yAxis), teamTwo);
	    		highlightField(coordinates.x + 1, coordinates.y + (1 * yAxis), teamTwo);
	    		highlightField(coordinates.x + 1, coordinates.y, teamTwo);
	    		highlightField(coordinates.x - 1, coordinates.y, teamTwo);
	    		highlightField(coordinates.x, coordinates.y + ((-1) * yAxis), teamTwo);
	   		}
	    	if (piece.classList.contains("knight"))
	   		{
	    		highlightField(coordinates.x - 1, coordinates.y + (2 * yAxis), teamTwo);
	    		highlightField(coordinates.x + 1, coordinates.y + (2 * yAxis), teamTwo);
	   		}
	    	if (piece.classList.contains("lance"))
	   		{
	   			highlightDirection(coordinates.x, coordinates.y + (1 * yAxis), 0, yAxis, teamTwo);
	   		}
	    	if ( (piece.classList.contains("rook"))
	    			|| (piece.classList.contains("dragon")) )
	   		{
	    		highlightDirection(coordinates.x, coordinates.y + 1, 0, 1, teamTwo);
	    		highlightDirection(coordinates.x, coordinates.y - 1, 0, -1, teamTwo);
	    		highlightDirection(coordinates.x + 1, coordinates.y, 1, 0, teamTwo);
	    		highlightDirection(coordinates.x - 1, coordinates.y, -1, 0, teamTwo);
	   		}
	    	if ( (piece.classList.contains("bishop"))
	    			|| (piece.classList.contains("horse")) )
	   		{
	    		highlightDirection(coordinates.x + 1, coordinates.y + 1, 1, 1, teamTwo);
	    		highlightDirection(coordinates.x - 1, coordinates.y - 1, -1, -1, teamTwo);
	    		highlightDirection(coordinates.x + 1, coordinates.y - 1, 1, -1, teamTwo);
	    		highlightDirection(coordinates.x - 1, coordinates.y + 1, -1, 1, teamTwo);
	   		}
	    	if ( (piece.classList.contains("king"))
	    			|| (piece.classList.contains("dragon"))
	    			|| (piece.classList.contains("horse")) )
	   		{
	    		highlightField(coordinates.x, coordinates.y + (1 * yAxis), teamTwo);
	    		highlightField(coordinates.x - 1, coordinates.y + (1 * yAxis), teamTwo);
	    		highlightField(coordinates.x + 1, coordinates.y + (1 * yAxis), teamTwo);
	    		highlightField(coordinates.x + 1, coordinates.y, teamTwo);
	    		highlightField(coordinates.x - 1, coordinates.y, teamTwo);
	    		highlightField(coordinates.x + 1, coordinates.y + ((-1) * yAxis), teamTwo);
	    		highlightField(coordinates.x, coordinates.y + ((-1) * yAxis), teamTwo);
	    		highlightField(coordinates.x - 1, coordinates.y + ((-1) * yAxis), teamTwo);
	   		}
	    }
	    
	    function highlightForGravepiece(piece)
	    {
	        fields.forEach(function (field) {
	            if(field.innerHTML == "") {
	                field.classList.add('pHighlight');
	            }
	        });
	    }
	    
	    newGame();
	    
	</script>


</body>

<footer>

	<a href="../jsp/Impressum.jsp" class="footer-buttons">Impressum</a> <a
		href="../jsp/Instructions.jsp" class="footer-buttons">Anleitung</a> <i
		style="font-size: 15px; padding: 2px; float: right"> ©2020 DHBW
		Karlsruhe All rights reserved.</i>

</footer>

</html>
