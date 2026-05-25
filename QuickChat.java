/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;
import java.util.Scanner;
/**
 *
 * @author Risima Maluleke
 */
public class QuickChat {
      
    public static void main(String[] args) {

          Scanner input = new Scanner(System.in);

        Login login = new Login();

        Message message = new Message();

        // ==========================
        // REGISTRATION
        // ==========================

        System.out.println("===== QUICKCHAT REGISTRATION =====");

        System.out.print("Enter Name: ");
        String firstName = input.nextLine();

        System.out.print("Enter Surname: ");
        String surname = input.nextLine();

        System.out.print("Enter Username: ");
        String username = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        System.out.print("Enter SA Cell Number (+27xxxxxxxxx): ");
        String cell = input.nextLine();

        String registration = login.registerUser(
                firstName,
                surname,
                username,
                password,
                cell
        );

        System.out.println(registration);

        // ==========================
        // LOGIN
        // ==========================

        System.out.println("\n===== LOGIN =====");

        System.out.print("Enter Username: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter Password: ");
        String loginPassword = input.nextLine();

        boolean loginStatus =
                login.loginUser(loginUsername, loginPassword);

        System.out.println(
                login.returnLoginStatus(loginStatus)
        );

        // ==========================
        // MESSAGING SYSTEM
        // ==========================

        if (loginStatus) {

            String option;

            do {

                System.out.println("\n===== QUICKCHAT MENU =====");

                System.out.println("1. Send Message");
                System.out.println("2. Quit");

                System.out.print("Choose option: ");

                option = input.nextLine();

                switch (option) {

                    case "1":

                        System.out.print("Enter recipient number: ");

                        String recipient = input.nextLine();

                        int checkRecipient =
                                message.CheckrecipientCell(recipient);

                        if (checkRecipient == 1) {

                            System.out.print("Enter message: ");

                            String text = input.nextLine();

                            String result =
                                    message.sentMesseges(
                                            recipient,
                                            text
                                    );

                            System.out.println(result);

                            if (!result.contains("exceeds")) {

                                message.messageSent();

                                message.deleteMessage();

                                System.out.println(
                                        "Total messages sent: "
                                                + message.returnTotalMessagess()
                                );
                            }

                        } else {

                            System.out.println(
                                    "Invalid recipient cellphone number."
                            );
                        }

                        break;

                    case "2":

                        System.out.println("Exiting QuickChat...");

                        break;

                    default:

                        System.out.println("Invalid option.");

                }

            } while (!option.equals("2"));
        }

        input.close();
    } 
    
}   
        
      