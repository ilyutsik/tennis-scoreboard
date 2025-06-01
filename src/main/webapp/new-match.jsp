<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>New match</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Новый матч</h2>
<form method="post" action="new-match">
    Имя игрока 1: <input type="text" name="player1" required><br><br>
    Имя игрока 2: <input type="text" name="player2" required><br><br>
    <button type="submit">Начать</button>
</form>
</body>
</html>