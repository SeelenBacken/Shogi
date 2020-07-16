<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
	    <title>Spielbrett</title>
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.0/css/bulma.css">
		<link rel="stylesheet" type="text/css" href="../css/MainStyle.css">
	  
	    <style>
	        body {
	            user-drag: none;
	            user-select: none;
	            -moz-user-select: none;
	            -webkit-user-drag: none;
	            -webkit-user-select: none;
	            -ms-user-select: none;
	        }
	        table.playfield {
	            border-collapse: collapse;
	            color: gray;
	            padding: 10%;
	            float: left;
	            -ms-user-select: None;
	            -moz-user-select: None;
	            -webkit-user-select: None;
	            user-select: None;
	        }
	        table.playfield td {
	            width: 50px;
	            height: 50px;
	            border: thick solid black;
	            text-align: center;
	            vertical-align: middle;
	            background: 	#deb887;
	        }
	        table.playfield td.over {
	            background-color: black !important;
	        }
	        table.playfield tr:nth-child(odd) td:nth-child(odd){
	            background: #deb887;
	        }
	        table.playfield tr:nth-child(even) td:nth-child(even){
	            background: #deb887;
	        }
	        .playContainer {
	            padding: 10%;
	            min-height: 900px;
	        }
	        .graveyard {
	            border: thin solid gray;
	            height: 450px;
	            width: 200px;
	            float: left;
	            margin-left: 20px;
	        }
	        .player1 {
	            height: 50%;
	            width: 100%;
	            border: thin solid gray;
	        }
	        .player2 {
	            height: 50%;
	            width: 100%;
	            border: thin solid gray;
	        }
	        .piece {
	            height: 40px;
	            width: 40px;
	            color: black;
	            align-content: center;
	            align-self: center;
	            margin: auto;
	            background-size: contain;
	        }
	        #gravep1 .gravepiece {
	            height: 40px;
	            width: 40px;
	            color: black;
	            align-content: center;
	            align-self: center;
	            margin: auto;
	            background-size: contain;
	            float: left;
	        }
	        #gravep2 .gravepiece {
	            height: 40px;
	            width: 40px;
	            color: black;
	            align-content: center;
	            align-self: center;
	            margin: auto;
	            background-size: contain;
	            float: left;
	            transform: rotate(180deg);
	        }
	        .gLevel {
	            margin-top: 1em;
	        }
	        .king {background-image: url("../res/half/king.png");}
	        .rook {background-image: url("../res/half/rook.png");}
	        .bishop{background-image: url("../res/half/bishop.png")}
	        .silver{background-image: url("../res/half/silver.png")}
	        .gold{background-image: url("../res/half/gold.png")}
	        .lance{background-image: url("../res/half/lance.png")}
	        .pawn{background-image: url("../res/half/pawn.png")}
	        .knight {background-image: url("../res/half/knight.png");}
	        .horse {background-image: url("../res/half/horse.png");}
	        .p2Piece {
	            transform: rotate(180deg);
	        }
	        .pHighlight {
	            animation-name: backgroundHighlight;
	            animation-duration: 600ms;
	            background-color: gray !important;
	        }
	        @keyframes backgroundHighlight {
	            from {background-color: #deb887}
	            to {background-color: gray}
	        }
	    </style>
	
	</head>
	
	<body>

		<section class="section">
		
			<jsp:useBean id="g" class="beans.GameBean" />
		
		    <div class="playContainer container" draggable="false">
 
		        <table class="playfield" draggable="false">
			        <tr>
			            	
			            <form method="GET" action="http://localhost:8080/Shogi/Controller">
							<input name="undoMove" type="submit" value="Undo" class="Button" />
						</form>
		            	
		            </tr>
		            
		            <c:forEach begin="1" end="9" var="i" >
		            
		            	<tr>
		            	
		            		<th><c:out value="${i}" /></th>
		            		
		            		<c:forEach begin="1" end="9" var="j">
		                		
		                		<td id="${String.valueOf('ABCDEFGHI').charAt(j - 1)}${i}" ondragover="allowDrop(event)" ondrop="drop(event)">
		                			
		                			<div class="piece p${g.getPlayerTeam(j - 1,i - 1)}Piece ${g.getBoard().getBoard()[j - 1][i - 1].getType()}" onclick="upgrade(event, this.parentNode)" ondragstart="drag(event)" draggable="true" 
		                			style=" background-image: url(${g.getBoard().getBoard()[j - 1][i - 1].getPicturePath()})"></div>
		                		
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
		                        <div id="knight2" class="gravepiece knight" draggable="true" ondragstart="drag(event)">0</div>
		                    </div>
		                    <div class="level-item">
		                        <div id="pawn2" class="gravepiece pawn" draggable="true" ondragstart="drag(event)">0</div>
		                    </div>
		                    <div class="level-item">
		                        <div id="lance2" class="gravepiece lance" draggable="true" ondragstart="drag(event)">0</div>
		                    </div>
		                </nav>
		                <nav class="level">
		                    <div class="level-item">
		                        <div id="gold2" class="gravepiece gold" draggable="true" ondragstart="drag(event)">0</div>
		                    </div>
		                    <div class="level-item">
		                        <div id="silver2" class="gravepiece silver" draggable="true" ondragstart="drag(event)">0</div>
		                    </div>
		                </nav>
		                <nav class="level">
		                    <div class="level-item">
		                    
		                        <div id="bishop2" class="gravepiece bishop" draggable="true" ondragstart="drag(event)">0</div>
		                    </div>
		                    <div class="level-item">
		                        <div id="rook2" class="gravepiece rook" draggable="true" ondragstart="drag(event)">0</div>
		                    </div>
		                </nav>
		            </div>
		            <div id="gravep1" class="player1">
		                <nav class="level gLevel">
		                    <div class="level-item">
		                        <div id="bishop1" class="gravepiece bishop" draggable="true" ondragstart="drag(event)">0</div>
		                    </div>
		                    <div class="level-item">
		                        <div id="rook1" class="gravepiece rook" draggable="true" ondragstart="drag(event)">0</div>
		                    </div>
		                </nav>
		                <nav class="level">
		                    <div class="level-item">
		                        <div id="gold1" class="gravepiece gold" draggable="true" ondragstart="drag(event)">0</div>
		                    </div>
		                    <div class="level-item">
		                        <div id="silver1" class="gravepiece silver" draggable="true" ondragstart="drag(event)">0</div>
		                    </div>
		                </nav>
		                <nav class="level">
		                    <div class="level-item">
		                        <div id="knight1" class="gravepiece knight" draggable="true" ondragstart="drag(event)">0</div>
		                    </div>
		                    <div class="level-item">
		                        <div id="pawn1" class="gravepiece pawn" draggable="true" ondragstart="drag(event)">0</div>
		                    </div>
		                    <div class="level-item">
		                        <div id="lance1" class="gravepiece lance" draggable="true" ondragstart="drag(event)">0</div>
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
	        ev.dataTransfer.setData("text", ev.target.id);
	        fields.forEach(function (field) {
	            if(field.innerHTML == "") {
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
						
	        let xh = hitter.parentElement.Id.charAt(0);
	        let yh = hitter.parentElement.Id.charAt(1);
	        
	        let xt = target.parentElement.Id.charAt(0);
	        let yt = target.parentElement.Id.charAt(1);
	        
	        location.assign("http://localhost:8080/Shogi/Controller?Xh=" + xh + "&Yh=" + yh + "&Xt=" + xt + "&Yt=" + yt + "&Xc=" + xc + "&Yc=" + yc);
	    }
	    
	    function checkTarget(target, hitter) {
	        if(target === hitter){
	            return false;
	        } else if(target.classList.contains('p1Piece') && hitter.classList.contains('p1Piece')) {
	            return;
	        } else if (target.hasChildNodes()) {
	            if(target.firstChild.classList.contains('p1Piece') && hitter.classList.contains('p1Piece')) {
	                return;
	            }
	        } else return true;
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
	    
	    function upgrade(event, parent){
	    	if(xc == "Z" && yc == -1){
				let id = parent.id;
		    	xc = id.charAt(0);
		    	yc = id.charAt(1);
			}
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