package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

import com.johnfreier.mail.storage.TestPOP3Storage;

public class UIDLCommandTest {

    @Test
    public void process() {

        // Data Setup
        StringWriter stringOut = new StringWriter();
        PrintWriter printOut = new PrintWriter(stringOut);

        String in = "UIDL";

        TestPOP3Storage storage = new TestPOP3Storage();

        UIDLCommand command = new UIDLCommand();

        // Test
        command.process(printOut, in, storage);

        // Asserts
        printOut.flush();

        String response[] = stringOut.toString().split(System.lineSeparator());
        
        Assert.assertEquals("Should respond with the message index and hash code.", "1 1912014101", response[0]);
        //Assert.assertEquals("Should respond with a dot return after the 1st message hash.", POP3ResponseType.DOT_RETURN, response[1]);
        Assert.assertEquals("Should respond with the message index and hash code.", "2 1912014101", response[1]);
        Assert.assertEquals("Should respond with a dot return after the 2st message hash.", POP3ResponseType.DOT_RETURN, response[2]);

    }

}
