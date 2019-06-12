package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.command.POP3Command;
import com.johnfreier.mail.storage.POP3Storage;

/**
 * Authenticate the password, to validate the login in correct.
 * 
 * @author jfreier
 *
 */
public class PASSCommand implements POP3Command {
    
    private static final Logger log = LoggerFactory.getLogger(PASSCommand.class);
    
    @Override
    public void process(PrintWriter out, String in, POP3Storage storage) {

        log.debug("** Received password.");
        
        out.println(POP3ResponseType.OK);
        
    }

    @Override
    public String command() {
        return POP3CommandType.PASS;
    }



}
