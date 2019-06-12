package com.johnfreier.mail.command.smtp;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.command.SMTPCommand;
import com.johnfreier.mail.storage.SMTPStorage;

public class RSETCommand implements SMTPCommand {

    private static final Logger log = LoggerFactory.getLogger(RSETCommand.class);
    
    @Override
    public void process(PrintWriter out, String in, SMTPStorage storage) {
        
        log.warn("** Reseting data - Most likey an issue occured.");
        
        storage.setRecoring(false);
        
    }

    @Override
    public String command() {
        return SMTPCommandType.RSET;
    }

}
