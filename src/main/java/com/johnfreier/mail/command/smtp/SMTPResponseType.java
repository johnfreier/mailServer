package com.johnfreier.mail.command.smtp;

/**
 * Here are the response from the server.
 * 
 * @author jfreier
 *
 */
public final class SMTPResponseType {

    public static final String CLOSE_CONNECTION = "221";

    public static final String DATA_START = "354";
    
    public static final String OK = "250";
    
    public static final String READY = "220";
    
}
