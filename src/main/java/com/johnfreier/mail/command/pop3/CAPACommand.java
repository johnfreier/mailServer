package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.command.POP3Command;
import com.johnfreier.mail.storage.POP3Storage;

/**
 * Send a list of capabilities to the client.
 * 
 * @author jfreier
 *
 */
public class CAPACommand implements POP3Command {
    
    private static final Logger log = LoggerFactory.getLogger(CAPACommand.class);
    
    @Override
    public void process(PrintWriter out, String in, POP3Storage storage) {

        log.debug("** Sending capability list.");
        
        StringBuilder response = new StringBuilder(POP3ResponseType.OK).append(" Capability list follows");
        
        out.println(response.toString());
        out.println(POP3ResponseType.DOT_RETURN);
    }

    @Override
    public String command() {
        return POP3CommandType.CAPA;
    }

}
