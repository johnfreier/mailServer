package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

import com.johnfreier.mail.storage.TestPOP3Storage;

public class DELECommandTest {

    @Test
    public void test() {

        StringWriter stringOut = new StringWriter();
        PrintWriter printOut = new PrintWriter(stringOut);

        String in = "DELE 1";
        
        TestPOP3Storage storage = new TestPOP3Storage();
        
        DELECommand command = new DELECommand();
        
        command.process(printOut, in, storage);
        
        printOut.flush();
        
        String response = stringOut.toString();
        
        Assert.assertTrue("Should respond with an OK message.", response.startsWith(POP3ResponseType.OK + System.lineSeparator()));
        
        long deletedMessasge = storage.getDeletedMessages().get(0);
        
        Assert.assertEquals("Should have added the deleted message index to the list.", 0L, deletedMessasge);
        
    }

}
