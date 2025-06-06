<%@ page import="game.OngoingMatch" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match score</title>
    <style><%@ include file="/css/match-score.css"%></style>


</head>
<body>
<div class = "center-container">
<h2 class="match-score-text">Match score</h2>

<table class="match-table">

    <tr>
        <th></th>
        <th>${ongoingMatch.player1.name}</th>
        <th>${ongoingMatch.player2.name}</th>
    </tr>
    <%
        OngoingMatch ongoingMatch = (OngoingMatch) request.getAttribute("ongoingMatch");
        if (ongoingMatch != null && ongoingMatch.getPlayer1Score().getExtraPoints() == 0 && ongoingMatch.getPlayer2Score().getExtraPoints() == 0) {
    %>
    <tr>
        <td>points</td>
        <td>${ongoingMatch.player1Score.points.value}</td>
        <td>${ongoingMatch.player2Score.points.value}</td>
    </tr>
    <%
    } else {
    %>
    <tr>
        <td>tie-break</td>
        <td>${ongoingMatch.player1Score.extraPoints}</td>
        <td>${ongoingMatch.player2Score.extraPoints}</td>
    </tr>
    <%
        }
    %>
    <tr>
        <td>games</td>
        <td>${ongoingMatch.player1Score.games}</td>
        <td>${ongoingMatch.player2Score.games}</td>
    </tr>
    <tr>
        <td>sets</td>
        <td>${ongoingMatch.player1Score.sets}</td>
        <td>${ongoingMatch.player2Score.sets}</td>
    </tr>

</table>

    <div class="win-form">
    <form method="post" action="match-score?uuid=${param.uuid}">
        <input type="hidden" name="pointWinnerID" value=${ongoingMatch.player1.id}><br><br>
        <button type="submit">win</button>
    </form>

    <form method="post" action="match-score?uuid=${param.uuid}">
        <input type="hidden" name="pointWinnerID" value=${ongoingMatch.player2.id}><br><br>
        <button type="submit">win</button>
    </form>
    </div>

</div>
</body>
</html>
