/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author Risima Maluleke
 */
public class Message {
  
  private static int totalMessages = 0;

    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Check Message ID
    public boolean checkMessageID(String messageID) {

        return messageID.length() == 10;
    }

    // Check recipient cellphone
    public int CheckrecipientCell(String recipient) {

        if (recipient.matches("^\\+27[6-8][0-9]{8}$")) {
            return 1;
        }

        return 0;
    }

    // Create message hash
    public String createMessageHash(String messageID,
                                    int messageNumber,
                                    String message) {

        String firstTwoDigits = messageID.substring(0, 2);

        String[] words = message.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return (firstTwoDigits + ":" + messageNumber + ":"
                + firstWord + lastWord).toUpperCase();
    }

    // Message payload
    public String messagePayload() {

        return """
                Message ID: %s
                Message Hash: %s
                Recipient: %s
                Message: %s
                """.formatted(messageID, messageHash,
                recipient, messageText);
    }

    // Send message
    public String sentMesseges(String recipient,
                               String messageText) {

        if (messageText.length() > 250) {

            return "Message exceeds 250 characters by "
                    + (messageText.length() - 250)
                    + " characters.";
        }

        totalMessages++;

        this.recipient = recipient;
        this.messageText = messageText;

        this.messageID = generateMessageID();

        this.messageHash = createMessageHash(
                messageID,
                totalMessages,
                messageText
        );

        return """
                Message sent successfully.
                %s
                """.formatted(messagePayload());
    }

    // Message sent
    public void messageSent() {

        System.out.println("Message successfully sent.");
    }
    
    // Delete message
    public void deleteMessage() {

        System.out.println("Press 0 to delete the message.");
        System.out.println("Press 1 to store message");

        Scanner input = new Scanner(System.in);

        int choice = input.nextInt();

        if (choice == 0) {
            System.out.println("Message successfully deleted.");
        } else {
            System.out.println("Message successfully stored.");
        }
    }

    // Return total messages
    public int returnTotalMessagess() {

        return totalMessages;
    }

    // Generate random Message ID
    private String generateMessageID() {

        Random random = new Random();

        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 10; i++) {

            id.append(random.nextInt(10));
        }

        return id.toString();
        
    }
    
    
}
   
     
