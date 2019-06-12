package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.command.POP3Command;
import com.johnfreier.mail.storage.POP3Storage;

/**
 * Quit the server session.
 * 
 * @author jfreier
 *
 */
public class QUITCommand implements POP3Command {

    private static final Logger log = LoggerFactory.getLogger(QUITCommand.class);
    
    @Override
    public void process(PrintWriter out, String in, POP3Storage storage) {
        
        log.debug("** Quit session.");
        
        out.println(".\n\r");
    }

    @Override
    public String command() {
        return POP3CommandType.QUIT;
    }

}
