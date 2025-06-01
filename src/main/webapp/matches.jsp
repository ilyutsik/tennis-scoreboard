<%@ page import="game.MatchPage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Matches</title>
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

<table>
    <tr>
        <th>ID</th>
        <th>player 1</th>
        <th>player 2</th>
        <th>winner</th>
    </tr>
    <c:forEach var="match" items="${matchPage.matches}">
        <tr>
            <td>${match.id}</td>
            <td>${match.player1.name}</td>
            <td>${match.player2.name}</td>
            <td>${match.winner.name}</td>
        </tr>
    </c:forEach>

</table>
<%
    String playerName = request.getParameter("filter_by_player_name");
    if (playerName == null) playerName = "";
    MatchPage matchPage = (MatchPage) request.getAttribute("matchPage");
    Integer currentPage = matchPage.getPageNumber();
    Integer lastPage = matchPage.getCountOfPages();

    Integer pageUp = currentPage + 1 > lastPage ? currentPage : currentPage + 1;
    Integer pageDown = currentPage - 1 < 1 ? currentPage : currentPage - 1;
%>
<form method="get" action="matches">
    <input type="text" name="filter_by_player_name" placeholder="player name"><br><br>
    <button type="submit">search</button>
</form>
<form method="get" action="matches">
    <input type="hidden" name="filter_by_player_name" value=<%=playerName%>><br><br>
    <input type="text" name="page" placeholder="page"><br><br>
    <button type="submit">page</button>
</form>
<form method="get" action="matches">
    <input type="hidden" name="filter_by_player_name" value=<%=playerName%>><br><br>
    <input type="hidden" name="page" value=<%=pageDown%> ><br><br>
    <button type="submit"><</button>
</form>
<form method="get" action="matches">
    <input type="hidden" name="filter_by_player_name" value=<%=playerName%>><br><br>
    <input type="hidden" name="page" value=<%=pageUp%> ><br><br>
    <button type="submit">></button>
</form>

<div>${matchPage.pageNumber}/${matchPage.countOfPages}</div>

</body>
</html>
