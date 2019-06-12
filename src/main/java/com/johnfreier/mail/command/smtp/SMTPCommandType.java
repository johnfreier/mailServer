package com.johnfreier.mail.command.smtp;

/**
 * Typical commands that be received from the client.
 *  
 * @author jfreier
 *
 */
public final class SMTPCommandType {

    public static final String DATA = "DATA";
    
    public static final String EHLO = "EHLO";
    
    public static final String MAIL = "MAIL";
    
    public static final String QUIT = "QUIT";
    
    public static final String RCPT = "RCPT";
    
    public static final String RSET = "RSET";
    
}
