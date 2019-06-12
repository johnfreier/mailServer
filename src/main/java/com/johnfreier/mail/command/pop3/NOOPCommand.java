package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.command.POP3Command;
import com.johnfreier.mail.storage.POP3Storage;

/**
 * Keep the transaction open between the client and server.
 * 
 * @author jfreier
 *
 */
public class NOOPCommand implements POP3Command {

    private static final Logger log = LoggerFactory.getLogger(NOOPCommand.class);
    
    @Override
    public void process(PrintWriter out, String in, POP3Storage storage) {
        
        log.debug("** Received noop.");
        
        out.println(POP3ResponseType.OK);
        
    }

    @Override
    public String command() {
        return POP3CommandType.NOOP;
    }

}
