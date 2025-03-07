const API_BASE_URL = "http://localhost:8080/complex-number-calculator-1.0-SNAPSHOT/api"

// Sign in handler
function handleSignIn(event) {
    // Prevent the default form submission
    event.preventDefault();

    // Get the base URL
    const baseURL=document.querySelector("form").dataset.baseurl

    // Get the form data
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    // Create the body
    const body = {
        username,
        password
    }
    const jsonBody = JSON.stringify(body)
    console.log("JSON: " + jsonBody)

    // Send the sign in request
    fetch(API_BASE_URL + "/sign-in", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: jsonBody
    })
        .then(
            response => {
                if (response.ok)
                    window.location.href = baseURL + "/dashboard"
                else
                    response.json().then(
                        data => {
                            console.log(data)
                            if (data?.status === "fail")
                                alert(data?.data?.username ?? data?.data?.password)
                            else
                                alert("Failed to sign in.")
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
    form.addEventListener("submit", handleSignIn);
});