<%@ page import="game.MatchPage" %>
<%@ page import="model.Match" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Matches</title>
    <style><%@ include file="/css/header.css"%></style>
    <style><%@ include file="/css/matches.css"%></style>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="center-container">
<table class="matches-table">
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
            <td class="${match.player1.name == match.winner.name ? 'win-player1' : 'win-player2'}">${match.winner.name}</td>
        </tr>
    </c:forEach>

</table>
</div>

<%
    String playerName = request.getParameter("filter_by_player_name");
    if (playerName == null) playerName = "";
    MatchPage matchPage = (MatchPage) request.getAttribute("matchPage");
    Integer currentPage = matchPage.getPageNumber();
    Integer lastPage = matchPage.getCountOfPages();

    Integer pageUp = currentPage + 1 > lastPage ? currentPage : currentPage + 1;
    Integer pageDown = currentPage - 1 < 1 ? currentPage : currentPage - 1;
%>
<div class="name-form">
<form method="get" action="matches" class="find-by-name-box">
    <input type="text" name="filter_by_player_name" placeholder="player name">
    <button type="submit">search</button>
</form>
</div>
<div class="page-form">
<form method="get" action="matches" class="find-by-page-box">
    <input type="hidden" name="filter_by_player_name" value=<%=playerName%>>
    <input type="text" name="page" placeholder="page">
    <button type="submit">page</button>
</form>
</div>
<div class="page-up-down">
<form method="get" action="matches">
    <input type="hidden" name="filter_by_player_name" value=<%=playerName%>>
    <input type="hidden" name="page" value=<%=pageDown%>>
    <button type="submit"><</button>
</form>
    <div>${matchPage.pageNumber}/${matchPage.countOfPages}</div>
    <form method="get" action="matches">
    <input type="hidden" name="filter_by_player_name" value=<%=playerName%>>
    <input type="hidden" name="page" value=<%=pageUp%>>
    <button type="submit">></button>
</form>
</div>
</body>
</html>
