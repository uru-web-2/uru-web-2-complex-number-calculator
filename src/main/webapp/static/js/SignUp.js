const API_BASE_URL = "http://localhost:8080/complex-number-calculator-1.0-SNAPSHOT/api"

// Sign up handler
function handleSignUp(event) {
    // Prevent the default form submission
    event.preventDefault();

    // Get the base URL
    const baseURL=document.querySelector("form").dataset.baseurl

    // Get the form data
    const firstName = document.getElementById("first-name").value;
    const lastName = document.getElementById("last-name").value;
    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm-password").value;

    // Check if the password and confirm password match
    if (password !== confirmPassword) {
        alert("Passwords do not match.");
        return;
    }

    // Create the body
    const body = {
        first_name: firstName,
        last_name: lastName,
        username,
        email,
        password
    }
     const jsonBody = JSON.stringify(body)
    console.log("JSON: " + jsonBody)

    // Send the sign up request
    fetch(API_BASE_URL + "/sign-up", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: jsonBody
    })
        .then(
            response => {
                if (response.ok)
                    window.location.href = baseURL+"/sign-in"
                else
                    response.json().then(
                        data => {
                            console.log(data)
                            if (data?.status === "fail")
                                alert(data?.data?.username)
                            else
                                alert("Failed to sign up.")
                        }
                    )
            }
        )
}

// Add event listener to the form
document.addEventListener("DOMContentLoaded", () => {
    // Get the form element
    const form = document.querySelector("form");

    // Attach an event listener to the form's submit event
    form.addEventListener("submit", handleSignUp);
});