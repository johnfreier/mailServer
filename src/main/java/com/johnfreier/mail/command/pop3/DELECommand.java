package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.command.POP3Command;
import com.johnfreier.mail.storage.POP3Storage;

/**
 * Deletes a message with an index of n.
 * 
 * Example
 * c:DELE 1
 * s:+OK
 * 
 * @author jfreier
 *
 */
public class DELECommand implements POP3Command {

    private static final Logger log = LoggerFactory.getLogger(DELECommand.class);
    
    @Override
    public void process(PrintWriter out, String in, POP3Storage storage) {
        
        String messageNumber = in.replace("DELE ", "");
        int messageIndex = Integer.valueOf(messageNumber) - 1;

        log.debug("** Delete message {}", messageIndex);        
        
        storage.deleteMessage(messageIndex);

        out.println(POP3ResponseType.OK);

    }

    @Override
    public String command() {
        return POP3CommandType.DELE;
    }

}
