package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.command.POP3Command;
import com.johnfreier.mail.storage.POP3Storage;

/**
 * Get the statistics of the current messages
 * 
 * Example:
 * c:STAT
 * s:+OK 2 123
 * 
 * @author jfreier
 *
 */
public class STATCommand implements POP3Command {

    private static final Logger log = LoggerFactory.getLogger(STATCommand.class);

    @Override
    public void process(PrintWriter out, String in, POP3Storage storage) {

        long size = storage.getTotalMessageSize();

        long messageCount = storage.getMessageCount();

        log.debug("** Received stat. {}", size);
        
        StringBuilder response = new StringBuilder()
                .append(POP3ResponseType.OK).append(" ")
                .append(messageCount).append(" ")
                .append(size);

        out.println(response.toString());

    }

    @Override
    public String command() {
        return POP3CommandType.STAT;
    }

}
