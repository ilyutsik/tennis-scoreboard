<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match end</title>
    <style>
        table {
            border-collapse: collapse;
            width: 50%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #000;
            padding: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>
<h2 style="text-align: center;">Победитель ${winerName}</h2>
<table>
    <tr>
        <th></th>
        <th>${ongoingMatch.player1.name}</th>
        <th>${ongoingMatch.player2.name}</th>
    </tr>
    <tr>
        <td>Сеты (Sets)</td>
        <td>${ongoingMatch.gameScore.player1.sets}</td>
        <td>${ongoingMatch.gameScore.player2.sets}</td>
    </tr>
</table>
</body>
</html>
