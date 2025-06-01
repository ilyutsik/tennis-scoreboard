<%@ page import="model.OngoingMatch" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match score</title>
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
<h2 style="text-align: center;">Текущий счёт</h2>

<table>

    <tr>
        <th></th>
        <th>${ongoingMatch.player1.name}</th>
        <th>${ongoingMatch.player2.name}</th>
    </tr>
    <%
        OngoingMatch ongoingMatch = (OngoingMatch) request.getAttribute("ongoingMatch");
        if (ongoingMatch.getGameScore().getPlayer1().getExtraPoints() == 0 && ongoingMatch.getGameScore().getPlayer2().getExtraPoints() == 0) {
    %>
    <tr>
        <td>points</td>
        <td>${ongoingMatch.gameScore.player1.points.value}</td>
        <td>${ongoingMatch.gameScore.player2.points.value}</td>
    </tr>
    <%
    } else {
    %>
    <tr>
        <td>tie-break</td>
        <td>${ongoingMatch.gameScore.player1.extraPoints}</td>
        <td>${ongoingMatch.gameScore.player2.extraPoints}</td>
    </tr>
    <%
        }
    %>
    <tr>
        <td>games</td>
        <td>${gameScore.player1.games}</td>
        <td>${gameScore.player2.games}</td>
    </tr>
    <tr>
        <td>sets</td>
        <td>${gameScore.player1.sets}</td>
        <td>${gameScore.player2.sets}</td>
    </tr>
    <tr>
        <td>Выйграл</td>
        <td>
            <form method="post" action="match-score?uuid=${param.uuid}">
                <input type="hidden" name="pointWinnerID" value=${ongoingMatch.player1.id}><br><br>
            <button type="submit">win</button>
            </form>
        </td>
        <td>
            <form method="post" action="match-score?uuid=${param.uuid}">
                <input type="hidden" name="pointWinnerID" value=${ongoingMatch.player2.id}><br><br>
            <button type="submit">win</button>
        </form>
        </td>
    </tr>
</table>
</body>
</html>
