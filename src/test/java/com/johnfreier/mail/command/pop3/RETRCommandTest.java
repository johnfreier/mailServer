package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

import com.johnfreier.mail.storage.TestPOP3Storage;

public class RETRCommandTest {

    @Test
    public void process() {

        // Data Setup
        StringWriter stringOut = new StringWriter();
        PrintWriter printOut = new PrintWriter(stringOut);

        String in = "RETR 1";

        TestPOP3Storage storage = new TestPOP3Storage();

        RETRCommand command = new RETRCommand();

        // Test
        command.process(printOut, in, storage);

        // Asserts
        printOut.flush();

        String response = stringOut.toString();
        
        String expectedResponse = new StringBuilder()
                .append(POP3ResponseType.OK).append(System.lineSeparator())
                .append(TestPOP3Storage.message_1).append(System.lineSeparator())
                .append(POP3ResponseType.DOT_RETURN).append(System.lineSeparator())
                .toString();

        Assert.assertEquals("Should respond with an OK message.", expectedResponse, response);

    }

}
