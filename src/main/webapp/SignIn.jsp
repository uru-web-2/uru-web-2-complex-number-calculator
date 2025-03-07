<%--
  Created by IntelliJ IDEA.
  User: ralva
  Date: 3/6/2025
  Time: 5:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css">
    <script defer src="${pageContext.request.contextPath}/static/js/SignIn.js"></script>
</head>
<body>
<div class="main-container">
    <div class="main-container__header-container">
        <h1>Sign In</h1>
    </div>

    <form class="main-container__content-container" method="post">
        <div class="input-container">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required
                   class="input-container__input">
        </div>
        <div class="input-container">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required
                       class="input-container__input">
        </div>
        <div class="main-container__content-container__footer-container">
            <a href="${pageContext.request.contextPath}/sign-up" class="link">Sign Up</a>
            <button id="submit-button" class="button--primary" type="submit">Sign In</button>
        </div>
    </form>
</div>
</body>
</html>
