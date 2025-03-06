<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Complex Number Calculator</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/Dashboard.css">
    <script defer src="${pageContext.request.contextPath}/static/js/Dashboard.js"></script>
</head>
<body>
<div class="main-container">
    <div class="main-container__header-container">
        <h1>Complex Number Calculator</h1>
    </div>

    <form class="main-container__content-container">
        <div class=" main-container__content-container__input-type-container">
            <div class="main-container__content-container__input-type-container__header">
                <h2>Input Type</h2>
            </div>
            <div id="input-type-text" class="label">Rectangular</div>
            <label class="switch">
                <input type="checkbox" onclick="updateInputTypeText()" id="input-type-switch">
                <span class="slider round"></span>
            </label>
        </div>

        <div class="main-container__content-container__complex-number-container">
            <div class="main-container__content-container__complex-number-container__header">
                <h2>First Number</h2>
            </div>
            <div class="main-container__content-container__complex-number-container__input-container">
                <label for="a-first-part">Real</label>
                <input type="number" id="a-first-part" name="real" required
                       class="main-container__content-container__complex-number-container__input-container__input">
            </div>
            <div class="main-container__content-container__complex-number-container__input-container">
                <label for="a-second-part">Imaginary</label>
                <input type="number" id="a-second-part" name="imaginary" required
                       class="main-container__content-container__complex-number-container__input-container__input">
            </div>
        </div>

        <div class="main-container__content-container__operators-container">
            <button id="add"
                    class="button--secondary button--secondary--operator  button--secondary--operator--selected"
                    type="button">+
            </button>
            <button id="subtract" class="button--secondary button--secondary--operator" type="button">-</button>
            <button id="multiply" class="button--secondary button--secondary--operator" type="button">&times;</button>
            <button id="divide" class="button--secondary button--secondary--operator" type="button">&divide;</button>
        </div>

        <div class="main-container__content-container__complex-number-container">
            <div class="main-container__content-container__complex-number-container__header">
                <h2>Second Number</h2>
            </div>
            <div class="main-container__content-container__complex-number-container__input-container">
                <label for="b-first-part">Real</label>
                <input type="number" id="b-first-part" name="real" required
                       class="main-container__content-container__complex-number-container__input-container__input">
            </div>
            <div class="main-container__content-container__complex-number-container__input-container">
                <label for="b-second-part">Imaginary</label>
                <input type="number" id="b-second-part" name="imaginary" required
                       class="main-container__content-container__complex-number-container__input-container__input">
            </div>
        </div>

        <div class="main-container__content-container__output-type-container">
            <div class="main-container__content-container__output-type-container__header">
                <h2>Output Type</h2>
            </div>
            <div id="output-type-text" class="label">Rectangular</div>
            <label class="switch">
                <input type="checkbox" onclick="updateOutputTypeText()" id="output-type-switch">
                <span class="slider round"></span>
            </label>
        </div>

        <div class="main-container__content-container__footer-container">
            <button class="button--secondary" type="reset">Clear</button>
            <button id="submit-button" class="button--primary" type="submit">Calculate</button>
        </div>
    </form>
</div>
</body>
</html>