/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 *
 * @author Risima Maluleke
 */
public class Login {
   
     private String firstName;
    private String surname;
    private String username;
    private String password;
    private String cellNumber;

    // Username validation
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() == 5;
    }

    // Password validation
    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8;
    }

    // South African cellphone validation
    public boolean checkCellPhoneNumber(String cellNumber) {

        String regex = "^\\+27[6-8][0-9]{8}$";

        return cellNumber.matches(regex);
    }

    // Register user
    public String registerUser(String firstName, String surname,
                               String username, String password,
                               String cellNumber) {

        if (!checkUserName(username)) {
            return "Username is incorrectly formatted.\n"
                    + "Username must contain an underscore and be 5 characters long.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password must be at least 8 characters long.";
        }

        if (!checkCellPhoneNumber(cellNumber)) {
            return "Invalid South African cellphone number.";
        }

        this.firstName = firstName;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;

        return "User successfully registered.";
    }

    // Login user
    public boolean loginUser(String username, String password) {

        return this.username.equals(username)
                && this.password.equals(password);
    }

    // Login message
    public String returnLoginStatus(boolean status) {

        if (status) {
            return "Welcome " + firstName + " " + surname
                    + ", it is great to see you.";
        } else {
            return "Username or password incorrect.";
        }
    }
    
}
    
    