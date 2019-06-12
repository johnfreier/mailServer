package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.command.POP3Command;
import com.johnfreier.mail.storage.POP3Storage;

/**
 * Retrieve message from the server command.
 * 
 * Example:
 * c:RETR 1
 * s:blah blah blah...
 * s:.
 * 
 * @author jfreier
 *
 */
public class RETRCommand implements POP3Command {

    private static final Logger log = LoggerFactory.getLogger(RETRCommand.class);
    
    @Override
    public void process(PrintWriter out, String in, POP3Storage storage) {

        List<String> messages = storage.getMessages();
        
        out.println(POP3ResponseType.OK);

        String messageNumber = in.replace("RETR ", "");
        int messageIndex = Integer.valueOf(messageNumber) - 1;

        log.debug("** Retrieving message {}", messageIndex);
        log.debug("Messages: {}", messages);

        out.println(messages.get(messageIndex));
        out.println(POP3ResponseType.DOT_RETURN);

    }

    @Override
    public String command() {
        return POP3CommandType.RETR;
    }

}
