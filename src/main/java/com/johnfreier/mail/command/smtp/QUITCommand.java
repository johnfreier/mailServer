package com.johnfreier.mail.command.smtp;

import java.io.PrintWriter;

import com.johnfreier.mail.command.SMTPCommand;
import com.johnfreier.mail.storage.SMTPStorage;

public class QUITCommand implements SMTPCommand {

    @Override
    public void process(PrintWriter out, String in, SMTPStorage storage) {
        out.println(SMTPResponseType.CLOSE_CONNECTION);
    }

    @Override
    public String command() {
        return SMTPCommandType.QUIT;
    }

}
