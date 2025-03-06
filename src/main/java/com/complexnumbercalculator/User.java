package com.complexnumbercalculator;

public class User {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String username;
    private final String password;

    public User(String firstName, String lastName, String email, String username, String password) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }



    // Getter for the first name field
    public String getFirstName() {
        return firstName;
    }

    // Getter for the last name field
    public String getLastName() {
        return lastName;
    }

    // Getter for the email field
    public String getEmail() {
        return email;
    }

    // Getter for the username field
    public String getUsername() {
        return username;
    }

    // Getter for the password field
    public String getPassword() {
        return password;
    }

}
