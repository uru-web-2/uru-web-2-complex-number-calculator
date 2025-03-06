<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Complex Number Calculator</title>
    <style>
        /* Root styles */
        :root {
            --font-family: Arial, sans-serif;
            --color-dark-primary: #364fc7;
            --color-medium-primary: #4c6ef5;
            --color-light-primary: #91a7ff;
            --background-color: #f8f9fa;
            --color: #212529;
            --switch--height: 22px;
            --switch--width: 40px;
            --switch--slider--size: 16px;
            --input--border-radius--size: 0.5rem;
            --animation--movement--duration: 0.4s;
        }

        /* Global styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* Body styles */
        body {
            font-family: var(--font-family);
            background-color: var(--background-color);
            color: var(--color);
            display: grid;
            place-items: center;
            height: 100vh;
            width: 100vw;
        }

        /* Switch styles */
        .switch {
            position: relative;
            display: inline-block;
            width: var(--switch--width);
            height: var(--switch--height);
        }

        /* Hide default HTML checkbox */
        .switch input {
            opacity: 0;
            width: 0;
            height: 0;
        }

        /* Slider styles */
        .slider {
            position: absolute;
            cursor: pointer;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: var(--color-light-primary);
            -webkit-transition: var(--animation--movement--duration);
            transition: var(--animation--movement--duration);
        }

        .slider:before {
            position: absolute;
            content: "";
            height: var(--switch--slider--size);
            width: var(--switch--slider--size);
            left: calc((var(--switch--height) - var(--switch--slider--size)) / 2);
            bottom: calc((var(--switch--height) - var(--switch--slider--size)) / 2);
            background-color: white;
            -webkit-transition: var(--animation--movement--duration);
            transition: var(--animation--movement--duration);
        }

        input:checked + .slider {
            background-color: var(--color-medium-primary);
        }

        input:focus + .slider {
            box-shadow: 0 0 1px var(--color-medium-primary);
        }

        input:checked + .slider:before {
            --diff: calc(var(--switch--width) - var(--switch--slider--size) - (var(--switch--height) - var(--switch--slider--size)));
            -webkit-transform: translateX(var(--diff));
            -ms-transform: translateX(var(--diff));
            transform: translateX(var(--diff));
        }

        /* Rounded sliders */
        .slider.round {
            border-radius: var(--switch--height);
        }

        .slider.round:before {
            border-radius: 50%;
        }

        /* H1 styles */
        h1 {
            font-size: 2rem;
            font-weight: 500;
        }

        /* H2 styles */
        h2 {
            font-size: 1.2rem;
            font-weight: 500;
        }

        /* Label styles */
        label {
            font-size: 1rem;
            font-weight: 500;
        }

        /* Main container styles */
        .main-container {
            display: grid;
            grid-template-columns: 1fr;
            max-width: 600px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        /* Header container styles */
        .main-container__header-container {
            grid-column: 1/-1;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 1rem 2rem;
            background-color: var(--color-medium-primary);
            color: var(--background-color);
        }

        .main-container,
        .main-container__header-container {
            border-radius: 0.5rem;
        }

        /* Content container styles */
        .main-container__content-container {
            display: grid;
            grid-template-columns: 1fr;
            gap: 2rem;
            width: 100%;
            align-items: center;
            justify-items: left;
            padding: 2rem;
        }

        /* Complex number container styles */
        .main-container__content-container__complex-number-container {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 0.4rem 2rem;
            width: 100%;
        }

        /* Complex number header styles */
        .main-container__content-container__complex-number-container__header {
            grid-column: 1/-1;
            display: flex;
            justify-content: left;
            align-items: center;
            padding-left: var(--input--border-radius--size);
        }

        /* Complex number input container styles */
        .main-container__content-container__complex-number-container__input-container {
            display: grid;
            grid-template-columns: 1fr;
            gap: 0.4rem;
            justify-content: left;
            align-items: center;
        }

        /* Complex number input label styles */
        .main-container__content-container__complex-number-container__input-container label {
            padding-left: var(--input--border-radius--size);
        }

        /* Complex number input styles */
        .main-container__content-container__complex-number-container__input-container__input {
            font-family: var(--font-family);
            padding: 0.5rem;
            outline: none;
            border: 2px solid var(--color-light-primary);
            border-radius: var(--input--border-radius--size);
            width: 100%;
        }

        .main-container__content-container__complex-number-container__input-container__input:focus {
            border-color: var(--color-medium-primary);
        }

        /* Operators container styles */
        .main-container__content-container__operators-container {
            grid-column: 1/-1;
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 0.4rem 2rem;
            width: 100%;
            justify-content: center;
            align-items: center;
            padding-left: var(--input--border-radius--size);
        }

        /* Input/Output type container styles */
        .main-container__content-container__input-type-container,
        .main-container__content-container__output-type-container {
            grid-column: 1/-1;
            display: grid;
            grid-template-columns: 6rem auto;
            justify-content: left;
            align-items: center;
            gap: 0.4rem 2rem;
            width: 100%;
            padding-left: var(--input--border-radius--size);
        }

        /* Input/Output type container header styles */
        .main-container__content-container__input-type-container__header,
        .main-container__content-container__output-type-container__header {
            grid-column: 1/-1;
            display: flex;
            justify-content: left;
            align-items: center;
        }

        /* Footer container styles */
        .main-container__content-container__footer-container {
            grid-column: 1/-1;
            display: grid;
            grid-template-columns: 1fr 1fr;
            width: 100%;
            place-items: center;
        }

        /* Button styles */
        button {
            padding: 0.5rem 1rem;
            border: none;
            border-radius: var(--input--border-radius--size);
            width: 8rem;
            font-family: var(--font-family);
            font-size: 1rem;
            font-weight: 500;
            cursor: pointer;
            outline: none;
        }

        .button--primary {
            background-color: var(--color-medium-primary);
            color: var(--background-color);
        }

        .button--secondary {
            background-color: var(--color-light-primary);
            color: var(--background-color);
        }

        .button--primary:hover,
        .button--secondary:hover {
            background-color: var(--color-dark-primary);
        }

        .button--primary:active,
        .button--secondary:active {
            background-color: var(--color);
        }

        .button--secondary--operator {
            width: auto;
            padding: 0.5rem 1rem;
        }

        .button--secondary--operator--selected {
            background-color: var(--color-dark-primary);
            color: var(--background-color);
        }
    </style>

    <script>
        const API_BASE_URL = "http://localhost:8080/complex-number-calculator-1.0-SNAPSHOT/api"
        let selectedOperation = "add"

        // Update input type text
        function updateInputTypeText() {
            const checkbox = document.getElementById("input-type-switch");
            const textElement = document.getElementById("input-type-text");
            textElement.innerText = checkbox.checked ? "Polar" : "Rectangular";

            // Update input type
            const labelAFirstPart = document.querySelector('label[for="a-first-part"]');
            const labelASecondPart = document.querySelector('label[for="a-second-part"]');
            const labelBFirstPart = document.querySelector('label[for="b-first-part"]');
            const labelBSecondPart = document.querySelector('label[for="b-second-part"]');
            for (const element of [labelAFirstPart, labelBFirstPart])
                element.innerHTML = checkbox.checked ? "Magnitude" : "Real";
            for (const element of [labelASecondPart, labelBSecondPart])
                element.innerHTML = checkbox.checked ? "Angle (radians)" : "Imaginary";
        }

        // Update output type text
        function updateOutputTypeText() {
            const checkbox = document.getElementById("output-type-switch");
            const textElement = document.getElementById("output-type-text");
            textElement.innerText = checkbox.checked ? "Polar" : "Rectangular";
        }

        // Update selected operation
        function updateSelectedOperation(button) {
            const operation = button.id

            if (selectedOperation === operation) return

            // Update classes
            const oldSelectedButton = document.getElementById(selectedOperation)
            oldSelectedButton.classList.remove("button--secondary--operator--selected")
            button.classList.add("button--secondary--operator--selected")

            // Update selected operation
            selectedOperation = operation
        }

        // Clear form
        function clearForm() {
            document.getElementById("a-first-part").value = "";
            document.getElementById("a-second-part").value = "";
            document.getElementById("b-first-part").value = "";
            document.getElementById("b-second-part").value = "";
        }

        // Calculate
        function calculate(event) {
            event.preventDefault();

            const aFirstPart = document.getElementById("a-first-part").value;
            const aSecondPart = document.getElementById("a-second-part").value;
            const bFirstPart = document.getElementById("b-first-part").value;
            const bSecondPart = document.getElementById("b-second-part").value;
            const inputType = document.getElementById("input-type-switch").checked;
            const outputType = document.getElementById("output-type-switch").checked;
            const returnType = outputType ? "polar" : "rectangular";

            /*
            // Check if any input field is empty
            if (!aFirstPart || !aSecondPart || !bFirstPart || !bSecondPart) {
                alert("Please fill in all input fields.");
                return;
            }
            */

            // Calculate
            let url, body;
            if (inputType === true) {
                url = API_BASE_URL + "/polar/" + selectedOperation
                body = {
                    a_magnitude: aFirstPart,
                    a_angle: aSecondPart,
                    b_magnitude: bFirstPart,
                    b_angle: bSecondPart,
                    return_type: returnType
                }
            } else {
                url = API_BASE_URL + "/rectangular/" + selectedOperation
                body = {
                    a_real: aFirstPart,
                    a_imaginary: aSecondPart,
                    b_real: bFirstPart,
                    b_imaginary: bSecondPart,
                    return_type: returnType
                }
            }

            fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(body)
            })
                .then(
                    response => response.json()
                ).then(
                data => {
                    if (outputType)
                        console.log(
                            "Result: "+ data?.magnitude+" "+data?.angle+"+" +"radians"
                        )
                    else
                        console.log(
                            "Result: "+data?.real+" "+data?.imaginary+"i"
                        )
                    clearForm()
                }
            )
        }

        // Add event listener to the form
        document.addEventListener("DOMContentLoaded", () => {
            // Get the form element
            const form = document.querySelector("form");

            // Attach an event listener to the form's submit event
            form.addEventListener("submit", calculate);

            // Attach an event listener to the form's reset event
            form.addEventListener("reset", clearForm);

            // Attach an event listener to the operator buttons
            const operatorButtons = document.querySelectorAll(".button--secondary--operator")
            operatorButtons.forEach(button => {
                button.addEventListener("click", () => {
                    updateSelectedOperation(button)
                })
            })
        });
    </script>
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
            <button id="add"  class="button--secondary button--secondary--operator  button--secondary--operator--selected" type="button">+</button>
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