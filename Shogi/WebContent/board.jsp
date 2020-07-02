<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Shogi</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.0/css/bulma.css">

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

        .king {background-image: url("res/half/king.png");}
        .rook {background-image: url("res/half/rook.png");}
        .bishop{background-image: url("res/half/bishop.png")}
        .silver{background-image: url("res/half/silver.png")}
        .gold{background-image: url("res/half/gold.png")}
        .lance{background-image: url("res/half/lance.png")}
        .pawn{background-image: url("res/half/pawn.png")}
        .knight {background-image: url("res/half/knight.png");}
        .horse {background-image: url("res/half/horse.png");}

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

<h1>Shogi</h1>

<section class="section">

    <div class="playContainer container" draggable="false">
        <table class="playfield" draggable="false">
            <tr>
                <th>9</th>
                <td id="A9" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="B9" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="C9" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="D9" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="E9" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="F9" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="G9" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="H9" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="I9" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
            </tr>
            <tr>
                <th>8</th>
                <td id="A8" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="B8" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="C8" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="D8" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="E8" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="F8" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="G8" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="H8" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="I8" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
            </tr>
            <tr>
                <th>7</th>
                <td id="A7" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="B7" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="C7" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="D7" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="E7" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="F7" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="G7" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="H7" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="I7" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
            </tr>
            <tr>
                <th>6</th>
                <td id="A6" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="B6" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="C6" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="D6" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="E6" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="F6" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="G6" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="H6" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="I6" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
            </tr>
            <tr>
                <th>5</th>
                <td id="A5" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="B5" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="C5" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="D5" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="E5" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="F5" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="G5" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="H5" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="I5" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
            </tr>
            <tr>
                <th>4</th>
                <td id="A4" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="B4" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="C4" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="D4" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="E4" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="F4" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="G4" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="H4" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="I4" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
            </tr>
            <tr>
                <th>3</th>
                <td id="A3" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="B3" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="C3" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="D3" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="E3" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="F3" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="G3" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="H3" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="I3" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
            </tr>
            <tr>
                <th>2</th>
                <td id="A2" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="B2" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="C2" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="D2" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="E2" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="F2" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="G2" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="H2" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="I2" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
            </tr>
            <tr>
                <th>1</th>
                <td id="A1" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="B1" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="C1" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="D1" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="E1" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="F1" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="G1" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="H1" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
                <td id="I1" ondragover="allowDrop(event)" ondrop="drop(event)"></td>
            </tr>
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

    function newGame(){


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

    newGame();



</script>
</body>
</html>