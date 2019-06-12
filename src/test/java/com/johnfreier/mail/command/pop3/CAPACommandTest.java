package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

import com.johnfreier.mail.storage.TestPOP3Storage;

public class CAPACommandTest {

    @Test
    public void process() {
        
        StringWriter stringOut = new StringWriter();
        PrintWriter printOut = new PrintWriter(stringOut);

        String in = "CAPA";

        TestPOP3Storage storage = new TestPOP3Storage();
        
        CAPACommand command = new CAPACommand();
        
        command.process(printOut, in, storage);
        
        printOut.flush();
        
        String response[] = stringOut.toString().split(System.lineSeparator());
        
        Assert.assertTrue("Should respond with an OK message.", response[0].startsWith(POP3ResponseType.OK));
        
        Assert.assertTrue("Should close with an dot return.", response[1].endsWith(POP3ResponseType.DOT_RETURN));

        
    }

}
