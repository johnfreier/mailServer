package com.johnfreier.mail.command.smtp;

import java.io.PrintWriter;

import com.johnfreier.mail.command.SMTPCommand;
import com.johnfreier.mail.storage.SMTPStorage;

public class DATACommand implements SMTPCommand {

    @Override
    public void process(PrintWriter out, String in, SMTPStorage storage) {

        out.println(SMTPResponseType.DATA_START);
        
        storage.setRecoring(true);
        
    }

    @Override
    public String command() {
        return SMTPCommandType.DATA;
    }

}
