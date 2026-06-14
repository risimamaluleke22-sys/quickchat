/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
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
    private String sender;

    // ArrayLists to store all messages
    private static ArrayList<Message> storedMessages = new ArrayList<>();

    // Default constructor
    public Message() {
    }

    // Private constructor for creating sent messages
    private Message(String recipient, String messageText, String senderName, String messageID, String messageHash) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.sender = senderName;
        this.messageID = messageID;
        this.messageHash = messageHash;
    }

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
                Sender: %s
                Message: %s
                """.formatted(messageID, messageHash,
                recipient, sender, messageText);
    }

    // Send message - NOW RETURNS A NEW MESSAGE OBJECT
    public Message sendMessage(String recipient,
                              String messageText,
                              String senderName) {
        if (messageText.length() > 250) {
            return null; // Return null to indicate failure, check in caller
        }
        totalMessages++;
        
        String newMessageID = generateMessageID();
        String newMessageHash = createMessageHash(
                newMessageID,
                totalMessages,
                messageText
        );
        
        // Create and return a NEW Message object with its own state
        Message newMessage = new Message(recipient, messageText, senderName, newMessageID, newMessageHash);
        return newMessage;
    }

    // Store message
    public void storeMessage() {
        storedMessages.add(this);
        System.out.println("Message successfully stored.");
    }

    // Disregard message
    public void disregardMessage() {
        System.out.println("Message disregarded.");
    }

    // Message sent
    public void messageSent() {
        System.out.println("Message successfully sent.");
    }

    // Delete message using message hash
    public static boolean deleteMessageByHash(String hash) {
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).messageHash.equals(hash)) {
                storedMessages.remove(i);
                return true;
            }
        }
        return false;
    }

    // Return total messages
    public int returnTotalMessagess() {
        return totalMessages;
    }

    // Display all stored messages with sender and recipient
    public static void displayAllStoredMessages() {
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages.");
            return;
        }
        System.out.println("\n===== ALL STORED MESSAGES =====");
        for (Message msg : storedMessages) {
            System.out.println("Sender: " + msg.sender + " | Recipient: " + msg.recipient);
        }
    }

    // Display longest stored message
    public static void displayLongestMessage() {
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages.");
            return;
        }
        Message longest = storedMessages.get(0);
        for (Message msg : storedMessages) {
            if (msg.messageText.length() > longest.messageText.length()) {
                longest = msg;
            }
        }
        System.out.println("\n===== LONGEST STORED MESSAGE =====");
        System.out.println(longest.messagePayload());
    }

    // Search message by ID
    public static void searchByMessageID(String id) {
        for (Message msg : storedMessages) {
            if (msg.messageID.equals(id)) {
                System.out.println("\n===== MESSAGE FOUND =====");
                System.out.println("Recipient: " + msg.recipient);
                System.out.println("Message: " + msg.messageText);
                return;
            }
        }
        System.out.println("Message ID not found.");
    }

    // Search messages by recipient
    public static void searchByRecipient(String recipient) {
        boolean found = false;
        System.out.println("\n===== MESSAGES FOR " + recipient + " =====");
        for (Message msg : storedMessages) {
            if (msg.recipient.equals(recipient)) {
                System.out.println(msg.messagePayload());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found for this recipient.");
        }
    }

    // Display full report of all stored messages
    public static void displayFullReport() {
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages.");
            return;
        }
        System.out.println("\n===== FULL MESSAGE REPORT =====");
        for (Message msg : storedMessages) {
            System.out.println(msg.messagePayload());
            System.out.println("-----------------------------------");
        }
    }

    // Getters for stored messages access
    public static ArrayList<Message> getStoredMessages() {
        return storedMessages;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getSender() {
        return sender;
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