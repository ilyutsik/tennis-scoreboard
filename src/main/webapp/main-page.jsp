<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<style><%@ include file="/css/header.css"%></style>
<style><%@ include file="/css/main-page.css"%></style>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main page</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="image-container">
    <h2>Welcome to the tennis match scoreboard</h2>
    <img src="${pageContext.request.contextPath}/images/tennis.jpg" alt="Теннис" class="center-image">
</div>
</body>
</html>
