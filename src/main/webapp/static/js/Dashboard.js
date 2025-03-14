const API_BASE_URL = "http://localhost:8080/complex-number-calculator-1.0-SNAPSHOT/api"
let selectedOperation = "add"

// Sign out handler
function handleSignOut(baseURL) {
    fetch(API_BASE_URL + "/sign-out", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(
            response => {
                if (response.ok)
                    window.location.href = baseURL+"/sign-in"
                else
                    alert("Failed to sign out.")
            }
        )
}

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

// Clear form handler
function handleClearForm() {
    document.getElementById("a-first-part").value = "";
    document.getElementById("a-second-part").value = "";
    document.getElementById("b-first-part").value = "";
    document.getElementById("b-second-part").value = "";
}

// Calculate handler
function handleCalculate(event) {
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
    const jsonBody = JSON.stringify(body)
    console.log("JSON: " + jsonBody)

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: jsonBody,
    })
        .then(
            response => response.json()
        ).then(
        response => {
            let result
            if (outputType)
                result = response?.data?.magnitude + " " + response?.data?.angle + " " + "radians"
            else
                result = response?.data?.real + " " + response?.data?.imaginary + "i"

            console.log("Result: " + result)
            alert("Result: " + result)
            handleClearForm()
        }
    )
}

// Add event listener to the form
document.addEventListener("DOMContentLoaded", () => {
    // Get the form element
    const form = document.querySelector("form");

    // Attach an event listener to the form's submit event
    form.addEventListener("submit", handleCalculate);

    // Attach an event listener to the form's reset event
    form.addEventListener("reset", handleClearForm);

    // Attach an event listener to the operator buttons
    const operatorButtons = document.querySelectorAll(".button--secondary--operator")
    operatorButtons.forEach(button => {
        button.addEventListener("click", () => {
            updateSelectedOperation(button)
        })
    })
});