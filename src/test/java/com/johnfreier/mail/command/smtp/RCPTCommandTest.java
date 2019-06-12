package com.johnfreier.mail.command.smtp;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

import com.johnfreier.mail.storage.TestSMTPStorage;

public class RCPTCommandTest {

    @Test
    public void process() {

        // Data Setup
        StringWriter stringOut = new StringWriter();
        PrintWriter printOut = new PrintWriter(stringOut);

        String in = "RCPT TO: test@test.com";

        TestSMTPStorage storage = new TestSMTPStorage();

        RCPTCommand command = new RCPTCommand();

        // Test
        command.process(printOut, in, storage);

        // Asserts
        printOut.flush();

        String response[] = stringOut.toString().split(System.lineSeparator());
        
        Assert.assertEquals("Should return a start recording response.", SMTPResponseType.OK, response[0]);
        
        Assert.assertEquals("The email address should be the same", "test@test.com", storage.getTo());

    }
    
    @Test
    public void process_differentFormat() {

        // Data Setup
        StringWriter stringOut = new StringWriter();
        PrintWriter printOut = new PrintWriter(stringOut);

        String in = "RCPT TO: <test@test.com>";

        TestSMTPStorage storage = new TestSMTPStorage();

        RCPTCommand command = new RCPTCommand();

        // Test
        command.process(printOut, in, storage);

        // Asserts
        printOut.flush();

        String response[] = stringOut.toString().split(System.lineSeparator());
        
        Assert.assertEquals("Should return a start recording response.", SMTPResponseType.OK, response[0]);
        
        Assert.assertEquals("The email address should be the same", "test@test.com", storage.getTo());

    }

}
