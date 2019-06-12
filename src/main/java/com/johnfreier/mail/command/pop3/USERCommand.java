package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.command.POP3Command;
import com.johnfreier.mail.storage.POP3Storage;

/**
 * Check to see if the user is correct.
 * 
 * @author jfreier
 *
 */
public class USERCommand implements POP3Command {

    private static final Logger log = LoggerFactory.getLogger(USERCommand.class);
    
    @Override
    public void process(PrintWriter out, String in, POP3Storage storage) {

        log.debug("** Received username.");
        
        out.println(POP3ResponseType.OK);

    }

    @Override
    public String command() {
        return POP3CommandType.USER;
    }

}
