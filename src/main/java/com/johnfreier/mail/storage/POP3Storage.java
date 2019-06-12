package com.johnfreier.mail.storage;

import java.util.List;

public interface POP3Storage {

    /**
     * Get the total message count.
     * 
     * @return
     */
    long getMessageCount();
    
    /**
     * Get the total size of all the messages.
     * 
     * @return
     */
    long getTotalMessageSize();
    
    /**
     * Get all the messages as a {@link List} of {@link String}.
     * @return
     */
    List<String> getMessages();
    
    /**
     * Get a message at an index.
     * 
     * @param index
     * @return
     */
    String getMessage(long index);
    
    /**
     * Delete a message.
     * 
     * @param index
     * @return
     */
    boolean deleteMessage(long index);
    
    /**
     * Connection is closed commit any outstanding transactions.
     * 
     * @return
     */
    boolean commit();
    
}
