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
                System.out.println("2. Show recently sent messages");
                System.out.println("3. Stored Messages");
                System.out.println("4. Quit Quickchat");

                System.out.print("Choose option: ");

                option = input.nextLine();

                switch (option) {

                    case "1":

                        System.out.print("Enter recipient number: ");

                        String recipient = input.nextLine();

                        // Temporary Message object just to check recipient
                        Message tempMessage = new Message();
                        int checkRecipient = tempMessage.CheckrecipientCell(recipient);

                        if (checkRecipient == 1) {

                            System.out.print("Enter message: ");

                            String text = input.nextLine();

                            // sendMessage now returns a NEW Message object
                            Message sentMessage = tempMessage.sendMessage(recipient, text, username);

                            if (sentMessage == null) {
                                System.out.println("Message exceeds 250 characters by "
                                        + (text.length() - 250)
                                        + " characters.");
                                break;
                            }

                            // Message 
                            sentMessage.messageSent();
                            System.out.println(sentMessage.messagePayload());

                            // Store or Disregard options
                            System.out.println("\n===== MESSAGE OPTIONS =====");
                            System.out.println("1. Store message");
                            System.out.println("2. Disregard message");
                            System.out.print("Choose option: ");
                            String storeOption = input.nextLine();

                            if (storeOption.equals("1")) {
                                sentMessage.storeMessage();
                            } else if (storeOption.equals("2")) {
                                sentMessage.disregardMessage();
                            } else {
                                System.out.println("Invalid option. Message disregarded.");
                            }

                            System.out.println(
                                    "Total messages sent: "
                                            + tempMessage.returnTotalMessagess()
                            );

                        } else {

                            System.out.println(
                                    "Invalid recipient cellphone number."
                            );
                        }
                        break;

                    case "2":

                        System.out.println("\n===== RECENTLY SENT MESSAGES =====");
                        if (Message.getStoredMessages().isEmpty()) {
                            System.out.println("No messages stored yet.");
                        } else {
                            // Show last 5 messages (or fewer if less exist)
                            int count = 0;
                            for (int i = Message.getStoredMessages().size() - 1; i >= 0 && count < 5; i--) {
                                Message msg = Message.getStoredMessages().get(i);
                                System.out.println("Sender: " + msg.getSender() + " | Recipient: " + msg.getRecipient());
                                System.out.println("Message: " + msg.getMessageText());
                                System.out.println("---");
                                count++;
                            }
                        }
                        break;

                    case "3":

                        // Stored Messages Sub-menu
                        String storedOption;
                        do {
                            System.out.println("\n===== STORED MESSAGES MENU =====");
                            System.out.println("1. Display all stored messages (Sender & Recipient)");
                            System.out.println("2. Display longest stored message");
                            System.out.println("3. Search message by ID");
                            System.out.println("4. Search messages by recipient");
                            System.out.println("5. Delete message by hash");
                            System.out.println("6. Display full report");
                            System.out.println("7. Back to main menu");
                            System.out.print("Choose option: ");
                            storedOption = input.nextLine();

                            switch (storedOption) {
                                case "1":
                                    Message.displayAllStoredMessages();
                                    break;
                                case "2":
                                    Message.displayLongestMessage();
                                    break;
                                case "3":
                                    System.out.print("Enter Message ID: ");
                                    String searchID = input.nextLine();
                                    Message.searchByMessageID(searchID);
                                    break;
                                case "4":
                                    System.out.print("Enter recipient number: ");
                                    String searchRecipient = input.nextLine();
                                    Message.searchByRecipient(searchRecipient);
                                    break;
                                case "5":
                                    System.out.print("Enter message hash to delete: ");
                                    String hashToDelete = input.nextLine();
                                    boolean deleted = Message.deleteMessageByHash(hashToDelete);
                                    if (deleted) {
                                        System.out.println("Message successfully deleted.");
                                    } else {
                                        System.out.println("Message hash not found.");
                                    }
                                    break;
                                case "6":
                                    Message.displayFullReport();
                                    break;
                                case "7":
                                    System.out.println("Returning to main menu...");
                                    break;
                                default:
                                    System.out.println("Invalid option.");
                            }
                        } while (!storedOption.equals("7"));
                        break;

                    case "4":

                        System.out.println("Exiting QuickChat...");
                        break;

                    default:

                        System.out.println("Invalid option.");

                }

            } while (!option.equals("4"));
        }

        input.close();
    }

}

        

