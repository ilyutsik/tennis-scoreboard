<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match end</title>
    <style><%@ include file="/css/header.css"%></style>
    <style><%@ include file="css/finished-match.css"%></style>
</head>
<body>
<%@ include file="header.jsp" %>

<div class="center-container">
    <h2 class="winner-name">${winerName} win</h2>
<table class="score-table">
    <tr>
        <th></th>
        <th>${ongoingMatch.player1.name}</th>
        <th>${ongoingMatch.player2.name}</th>
    </tr>
    <tr>
        <td>Sets</td>
        <td>${ongoingMatch.player1Score.sets}</td>
        <td>${ongoingMatch.player2Score.sets}</td>
    </tr>
</table>
</div>
</body>
</html>
