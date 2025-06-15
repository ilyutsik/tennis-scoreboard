<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>New match</title>
    <style><%@ include file="/css/header.css"%></style>
    <style><%@include file="/css/new-match.css"%></style>
</head>
<body>
<%@ include file="header.jsp" %>


<div class = "center-container">
    <h2 class="new-match-text">New match</h2>
    <div class="error">${error}</div>
    <form method="post" action="new-match" class="new-match-box">
    Player 1<br>
        <input type="text" name="player1" required><br><br>
    Player 2<br>
        <input type="text" name="player2" required><br><br>
    <button type="submit">Start</button>
    </form>
</div>
</body>
</html>