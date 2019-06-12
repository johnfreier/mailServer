package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.command.POP3Command;
import com.johnfreier.mail.storage.POP3Storage;

/**
 * This command sends a list of message indexes and hash code of the message, or a message id.
 * 
 * Example
 * 
 * c:UIDL s:1 jfk3fd4df4 s:2 lek4jdme43 s:.
 * 
 * @author jfreier
 *
 */
public class UIDLCommand implements POP3Command {

    private static final Logger log = LoggerFactory.getLogger(UIDLCommand.class);

    @Override
    public void process(PrintWriter out, String in, POP3Storage storage) {

        log.debug("** Received UIDL.");

        List<String> messages = storage.getMessages();

        for (int i = 0; i < messages.size(); i++) {

            StringBuilder response = new StringBuilder()
                    .append(i + 1).append(" ")
                    .append(messages.get(0).hashCode());
            
            out.println(response.toString());

        }
        
        out.println(POP3ResponseType.DOT_RETURN);
    }

    @Override
    public String command() {
        return POP3CommandType.UIDL;
    }

}
