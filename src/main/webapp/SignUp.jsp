<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/SignUp.css">
    <script defer src="${pageContext.request.contextPath}/static/js/SignUp.js"></script>
</head>
<body>
<div class="main-container">
    <div class="main-container__header-container">
        <h1>Sign Up</h1>
    </div>

    <form class="main-container__content-container" data-baseurl="${pageContext.request.contextPath}">
        <div class="input-container">
            <label for="first-name">First Name</label>
            <input type="text" id="first-name" name="first-name" required
                   class="input-container__input">
        </div>
        <div class="input-container">
            <label for="last-name">Last Name</label>
            <input type="text" id="last-name" name="last-name" required
                   class="input-container__input">
        </div>
        <div class="input-container">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required
                   class="input-container__input">
        </div>
        <div class="input-container">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required
                   class="input-container__input">
        </div>
        <div class="input-container">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required
                       class="input-container__input">
        </div>
        <div class="input-container">
            <label for="confirm-password">Confirm Password</label>
            <input type="password" id="confirm-password" name="confirm-password" required
                       class="input-container__input">
        </div>
        <div class="main-container__content-container__footer-container">
            <a href="${pageContext.request.contextPath}/sign-in" class="link button--link">Sign In</a>
            <button id="submit-button" class="button--primary" type="submit">Sign Up</button>
        </div>
    </form>
</div>
</body>
</html>
