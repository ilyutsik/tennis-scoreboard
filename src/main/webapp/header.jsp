<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" />

<div class="header">
    <h2></h2>
    <div class="header-buttons">
        <form action="new-match" method="get">
            <button type="submit">New Match</button>
        </form>
        <form action="match-list" method="get">
            <button type="submit">Matches</button>
        </form>
    </div>
</div>
