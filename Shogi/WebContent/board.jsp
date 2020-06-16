<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Shogi</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.0/css/bulma.css">

    <style>

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
            border: thin solid;
        }

        table.playfield td.over {
            background-color: black !important;
        }

        table.playfield tr:nth-child(odd) td:nth-child(odd){
            background: rgba(50, 50, 50, 0.3);
        }

        table.playfield tr:nth-child(even) td:nth-child(even){
            background: rgba(50, 50, 50, 0.3);
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
            width: 20px;
            border: thin solid gray;
            color: black;
            align-content: center;
            align-self: center;
        }


    </style>

</head>
<body>

<h1>Shogi</h1>

<section class="section">

    <div class="playContainer container">
        <table class="playfield" draggable="false">
            <tr>
                <th>9</th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <th>8</th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <th>7</th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <th>6</th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <th>5</th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <th>4</th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <th>3</th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <th>2</th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <th>1</th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
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
                <div class="piece king" draggable="true">K</div>
            </div>
            <div class="player2"></div>
        </div>
    </div>
</section>

<script>
    var dragSrcEl = null;

    function dragStartHandler(e) {
        this.style.opacity = '0.8';

        dragSrcEl = this;

        e.dataTransfer.effectAllowed = 'move';
        e.dataTransfer.setData('text/html', this.outerHTML);
    }

    function dragOverHandler(e) {
        if (e.preventDefault) {
            e.preventDefault();
        }

        e.dataTransfer.dropEffect = "move";

        return false;
    }

    function dragEnterHandler(e) {
        this.classList.add('over');
        console.log("over");
    }

    function dragLeaveHandler(e) {
        this.classList.remove('over');
    }

    function dropHandler(e) {
        if (e.stopPropagation) {
            e.stopPropagation();
        }

        if ( dragSrcEl != this) {
            dragSrcEl.innerHTHML = this.innerHTML;
            this.innerHTML = e.dataTransfer.getData('text/html');
        }

        return false;
    }

    function dragEndHandler(e) {
        console.log("end");
        [].forEach.call(fields, function (field) {
            field.classList.remove('over');
        });
    }

    var pieces = document.querySelectorAll('.piece');
    console.log(pieces);
    [].forEach.call(pieces, function (piece) {
        piece.addEventListener('dragstart', dragStartHandler, false);
        piece.addEventListener('dragend', dragEndHandler, false);
    });

    var fields = document.querySelectorAll('.playfield tr td');
    console.log(fields);
    [].forEach.call(fields, function (field) {
        field.addEventListener('dragenter', dragEnterHandler, false);
        field.addEventListener('dragover', dragOverHandler, false);
        field.addEventListener('dragleave', dragLeaveHandler, false);
        field.addEventListener('drop', dropHandler, false);
        field.draggable = false;
    });

</script>
</body>
</html>