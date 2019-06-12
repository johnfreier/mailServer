package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.command.POP3Command;
import com.johnfreier.mail.storage.POP3Storage;

/**
 * This command list all of the messages and the size of them.
 * 
 * Example
 * 
 * c: LIST
 * s: +OK 1 88
 * s: +OK 2 112
 * s: .
 * 
 * @author jfreier
 *
 */
public class LISTCommand implements POP3Command {

    private static final Logger log = LoggerFactory.getLogger(LISTCommand.class);
    
    @Override
    public void process(PrintWriter out, String in, POP3Storage storage) {
        int size = 0;

        List<String> messages = storage.getMessages();
        
        for (String message : messages) {
            size += message.getBytes().length;
        }

        log.debug("** Received stat. {}", size);

        out.println(new StringBuilder(POP3ResponseType.OK).append(" ").append(messages.size()).append(" ").append(size).toString());

        for (int i = 0; i < messages.size(); i++) {

            out.println((i + 1) + " " + messages.get(0).getBytes().length);

        }

        out.println(POP3ResponseType.DOT_RETURN);

    }

    @Override
    public String command() {
        return POP3CommandType.LIST;
    }

}
