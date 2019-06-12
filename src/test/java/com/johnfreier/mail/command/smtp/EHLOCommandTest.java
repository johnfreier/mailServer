package com.johnfreier.mail.command.smtp;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

import com.johnfreier.mail.storage.TestSMTPStorage;

public class EHLOCommandTest {

    @Test
    public void process() {

        // Data Setup
        StringWriter stringOut = new StringWriter();
        PrintWriter printOut = new PrintWriter(stringOut);

        String in = "EHLO";

        TestSMTPStorage storage = new TestSMTPStorage();

        EHLOCommand command = new EHLOCommand();

        // Test
        command.process(printOut, in, storage);

        // Asserts
        printOut.flush();

        String response[] = stringOut.toString().split(System.lineSeparator());
        
        Assert.assertEquals("Should return a start recording response.", SMTPResponseType.OK, response[0]);

    }
}
