package com.johnfreier.mail.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestPOP3Storage implements POP3Storage {

    private List<Long> deletedMessages = new ArrayList<>();
    
    public static String message_1 = "From: test@client.com\nTo: test@server.com\nSubject: What is up?\n\nHello this is a message.";
    public static String message_2 = "From: test@client.com\nTo: test@server.com\nSubject: Another Subject?\n\nThis is another message.";

    public static List<String> messages = Arrays.asList(message_1, message_2);
    
    @Override
    public long getMessageCount() {
        return messages.size();
    }

    @Override
    public long getTotalMessageSize() {
        
        long totalSize = 0;
        
        totalSize += message_1.getBytes().length;
        totalSize += message_2.getBytes().length;
        
        return totalSize;
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }

    @Override
    public String getMessage(long index) {
        return null;
    }

    @Override
    public boolean deleteMessage(long index) {
        
        deletedMessages.add(index);
        
        return true;
    }
    
    public List<Long> getDeletedMessages() {
        return deletedMessages;
    }

    @Override
    public boolean commit() {
        return false;
    }

}
