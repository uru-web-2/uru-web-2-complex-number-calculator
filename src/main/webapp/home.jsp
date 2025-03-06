<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Complex Number Calculator</title>
    <style>
        /* Root styles */
        :root {
            --font-family: Arial, sans-serif;
            --color-primary: #3b5bdb;
            --color-secondary: #91a7ff;
            --background-color: #f8f9fa;
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
          background-color: var(--color-secondary);
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
          background-color: var(--color-primary);
        }

        input:focus + .slider {
          box-shadow: 0 0 1px var(--color-primary);
        }

        input:checked + .slider:before {
            --diff:calc(var(--switch--width) - var(--switch--slider--size) - (var(--switch--height) - var(--switch--slider--size)));
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
            background-color: var(--color-primary);
            color: var(--background-color);
        }

        .main-container,
        .main-container__header-container{
            border-radius: 0.5rem;
        }

        /* Content container styles */
        .main-container__content-container {
            display: grid;
            grid-template-columns: 1fr;
            align-items: center;
            justify-items: left;
            gap: 2rem;
            padding: 2rem;
            width: 100%;
        }

        /* Complex numbers container styles */
        .main-container__content-container__complex-numbers-container {
            display: grid;
            grid-template-columns: 1fr;
            gap: 2rem;
            width: 100%;
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
            border: 2px solid var(--color-secondary);
            border-radius: var(--input--border-radius--size);
            width: 100%;
        }

        .main-container__content-container__complex-number-container__input-container__input:focus {
            border-color: var(--color-primary);
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
    </style>

    <script>
        // Update input type text
        function updateInputTypeText() {
            const checkbox = document.getElementById("input-type-switch");
            const textElement = document.getElementById("input-type-text");
            textElement.innerText = checkbox.checked ? "Polar":"Rectangular";

            // Update input type
            const labelAFirstPart = document.querySelector('label[for="a-first-part"]');
            const labelASecondPart = document.querySelector('label[for="a-second-part"]');
            const labelBFirstPart = document.querySelector('label[for="b-first-part"]');
            const labelBSecondPart = document.querySelector('label[for="b-second-part"]');
            for (const element of [labelAFirstPart, labelBFirstPart])
                element.innerHTML = checkbox.checked ? "Magnitude":"Real";
            for (const element of [labelASecondPart, labelBSecondPart])
                element.innerHTML = checkbox.checked ? "Angle (rad)":"Imaginary";
        }

        // Update output type text
        function updateOutputTypeText() {
            const checkbox = document.getElementById("output-type-switch");
            const textElement = document.getElementById("output-type-text");
            textElement.innerText = checkbox.checked ? "Polar":"Rectangular";
        }
    </script>
</head>
<body>
    <div class="main-container">
        <div class="main-container__header-container">
            <h1>Complex Number Calculator</h1>
        </div>

        <div class="main-container__content-container">
            <div class="main-container__content-container__input-type-container">
                <div class="main-container__content-container__input-type-container__header">
                    <h2>Input Type</h2>
                </div>
                <div id="input-type-text" class="label">Rectangular</div>
                <label class="switch">
                    <input type="checkbox" onclick="updateInputTypeText()" id="input-type-switch">
                    <span class="slider round"></span>
                </label>
            </div>

            <form class="main-container__content-container__complex-numbers-container">
                <div class="main-container__content-container__complex-number-container">
                    <div class="main-container__content-container__complex-number-container__header">
                        <h2>First Number</h2>
                    </div>
                    <div class="main-container__content-container__complex-number-container__input-container">
                        <label for="a-first-part">Real</label>
                        <input type="number" id="a-first-part" name="real" required class="main-container__content-container__complex-number-container__input-container__input">
                    </div>
                    <div class="main-container__content-container__complex-number-container__input-container">
                        <label for="a-second-part">Imaginary</label>
                        <input type="number" id="a-second-part" name="imaginary" required class="main-container__content-container__complex-number-container__input-container__input">
                    </div>
                </div>

                <div class="main-container__content-container__complex-number-container">
                    <div class="main-container__content-container__complex-number-container__header">
                        <h2>Second Number</h2>
                    </div>
                    <div class="main-container__content-container__complex-number-container__input-container">
                        <label for="b-first-part">Real</label>
                        <input type="number" id="b-first-part" name="real" required class="main-container__content-container__complex-number-container__input-container__input">
                    </div>
                    <div class="main-container__content-container__complex-number-container__input-container">
                        <label for="b-second-part">Imaginary</label>
                        <input type="number" id="b-second-part" name="imaginary" required class="main-container__content-container__complex-number-container__input-container__input">
                    </div>
                </div>
            </form>

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
        </form>
    </div>
</body>
</html>