package com.johnfreier.mail.command.smtp;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.command.SMTPCommand;
import com.johnfreier.mail.storage.SMTPStorage;

public class MAILCommand implements SMTPCommand {

    private static final Logger log = LoggerFactory.getLogger(MAILCommand.class);
    
    @Override
    public void process(PrintWriter out, String in, SMTPStorage storage) {
        
        String email = in;
        email = email.replace("MAIL FROM:", "");
        email = email.replace("<", "");
        email = email.replace(">", "");
        email = email.trim();
        
        log.debug("Setting from:" + email);
        
        storage.setFrom(email);
        
        out.println(SMTPResponseType.OK);
        
    }

    @Override
    public String command() {
        return SMTPCommandType.MAIL;
    }

}
