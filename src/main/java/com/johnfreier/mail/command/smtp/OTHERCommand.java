package com.johnfreier.mail.command.smtp;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.command.SMTPCommand;
import com.johnfreier.mail.storage.SMTPStorage;

/**
 * This is not actually a command but is used for capturing data, or the body of the email.
 * 
 * It only starts capturing after record is trigger on in the storage.
 *  
 * @author jfreier
 *
 */
public class OTHERCommand implements SMTPCommand {

    private static final Logger log = LoggerFactory.getLogger(OTHERCommand.class);
    
    @Override
    public void process(PrintWriter out, String in, SMTPStorage storage) {

        if (".".equals(in)) {
            
            storage.setRecoring(false);
            
            out.println(SMTPResponseType.OK);
            
            log.debug("** Stop recoding data.");
            
        } else {
            
            storage.addData(in + "\n");
            
            log.debug("** Adding data:" + in);
            
        }
        
    }

    @Override
    public String command() {
        return "OTHER";
    }

}
