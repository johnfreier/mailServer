package com.johnfreier.mail.storage;

import java.util.Arrays;
import java.util.List;

/**
 * This was created for testing purposes when first setting up the project.
 * There is a lot of dummy data and could be deleted.
 * 
 * @author jfreier
 *
 */
public class StaticPOP3Storage implements POP3Storage {

    static String message_1 = "From: test@client.com\nTo: test@server.com\nSubject: What is up?\n\nHello this is a message.";
    static String message_2 = "From: test@client.com\nTo: test@server.com\nSubject: Another Subject?\n\nThis is another message.";

    static List<String> messages = Arrays.asList(message_1, message_2);
    
    @Override
    public long getMessageCount() {
        return 2L;
    }

    @Override
    public long getTotalMessageSize() {
        
        long size = 0;
        
        for (String message : messages) {
            size += message.getBytes().length;
        }
        
        return size;
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }

    @Override
    public String getMessage(long index) {

        String message = messages.get(new Long(index).intValue());
        
        return message;
    }

    @Override
    public boolean deleteMessage(long index) {
        return true;
    }

    @Override
    public boolean commit() {
        return true;
    }

}
