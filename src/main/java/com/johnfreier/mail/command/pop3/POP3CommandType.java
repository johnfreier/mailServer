package com.johnfreier.mail.command.pop3;

/**
 * Typical commands that be received from the client.
 *  
 * @author jfreier
 *
 */
public final class POP3CommandType {

    public static final String AUTH = "AUTH";
    
    public static final String CAPA = "CAPA";

    public static final String DELE = "DELE";
    
    public static final String LIST = "LIST";
    
    public static final String NOOP = "NOOP";
    
    public static final String PASS = "PASS";
    
    public static final String QUIT = "QUIT";
    
    public static final String RETR = "RETR";
    
    public static final String STAT = "STAT";
    
    public static final String UIDL = "UIDL";
    
    public static final String USER = "USER";
    
    public static final String XTND = "XTND";
    
}
