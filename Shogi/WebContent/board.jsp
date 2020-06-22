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
        }

        .king {
            background-image: url("../img/king.png");
            background-size: contain;
        }

        .knight {
            background-image: url("../img/knight.png");
            background-size: contain;
        }

        .horse {
            background-image: url("../img/horse.png");
            background-size: contain;
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
            <div class="player1">
                <div id="king1" class="piece king" draggable="true" ondragstart="drag(event)"></div>
                <div id="knight1" class="piece knight" draggable="true" ondragstart="drag(event)"></div>
                <div id="horse1" class="piece horse" draggable="true" ondragstart="drag(event)"></div>
            </div>
            <div class="player2"></div>
        </div>
    </div>
</section>

<script>
    let fields = document.querySelectorAll('.playfield tr td')

    function allowDrop(ev) {
        ev.preventDefault();
    }

    function drag(ev) {
        ev.dataTransfer.setData("text", ev.target.id);
        fields.forEach(function (field) {
            if(field.innerHTML == "") {
                field.style.backgroundColor = "#ffffff";
            }
        });
    }
    function drop(ev) {
        ev.preventDefault();
        var data = ev.dataTransfer.getData('text');
        ev.target.appendChild(document.getElementById(data));
        fields.forEach(function (field) {
            field.style.backgroundColor = "#deb887";
        })
    }

    //Function for testing piece creation
    function newGame(){
        var piece = document.createElement('div');
        piece.classList.add('piece');
        piece.classList.add('knight');
        piece.id = "knight2";
        piece.draggable = true;
        piece.setAttribute("ondragstart", "drag(event)");
        document.getElementById("A5").appendChild(piece);
    }

    newGame();

</script>
</body>
</html>