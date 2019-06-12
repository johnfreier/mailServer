package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

public class AUTHCommandTest {

    @Test
    public void process() {
        
        StringWriter stringOut = new StringWriter();
        PrintWriter printOut = new PrintWriter(stringOut);
        
        AUTHCommand command = new AUTHCommand();
        
        command.process(printOut, null, null);
        
        printOut.flush();
        
        String result = stringOut.toString().replace(POP3ResponseType.NEW_LINE, "");
        
        Assert.assertEquals("Should send an error.", POP3ResponseType.ERR, result);
        
    }

}
