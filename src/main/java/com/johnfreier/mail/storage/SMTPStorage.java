package com.johnfreier.mail.storage;

public interface SMTPStorage {
    
    /**
     * Set whom the email is from.
     * 
     * @param from
     */
    void setFrom(String from);
    
    /**
     * Set the recipient of the email.
     * 
     * @param to
     */
    void setTo(String to);
    
    /**
     * Set the data from the body of the email, this includes headers and all data.
     * @param data
     */
    void addData(String data);
    
    /**
     * SMTP starts a recording process when consuming the email data(body).  So this flag is to indicate when the recording in transaction.
     * 
     * @param recording
     */
    void setRecoring(boolean recording);
    
    /**
     * Check to see if the recording is in transaction.
     * 
     * @return
     */
    boolean isRecording();
    
    /**
     * After all the information is captured we need to commit the email, a.k.a. save the email to a file.
     * @return
     */
    boolean commit();
    
}
