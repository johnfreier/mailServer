package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

import com.johnfreier.mail.storage.TestPOP3Storage;

public class STATCommandTest {

    @Test
    public void process() {

        // Data Setup
        StringWriter stringOut = new StringWriter();
        PrintWriter printOut = new PrintWriter(stringOut);

        String in = "STAT";

        TestPOP3Storage storage = new TestPOP3Storage();

        STATCommand command = new STATCommand();

        // Test
        command.process(printOut, in, storage);

        // Asserts
        printOut.flush();

        String response[] = stringOut.toString().split(System.lineSeparator());
        
        String expectedResponse = new StringBuilder()
                .append(POP3ResponseType.OK).append(" ")
                .append(2).append(" ")
                .append("181").toString();

        Assert.assertEquals("Should respond with an OK message.", expectedResponse, response[0]);

    }

}
