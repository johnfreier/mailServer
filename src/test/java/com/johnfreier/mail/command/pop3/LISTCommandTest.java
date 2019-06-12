package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

import com.johnfreier.mail.storage.TestPOP3Storage;

public class LISTCommandTest {

    @Test
    public void test() {

        // Data Setup
        StringWriter stringOut = new StringWriter();
        PrintWriter printOut = new PrintWriter(stringOut);

        TestPOP3Storage storage = new TestPOP3Storage();
        
        LISTCommand command = new LISTCommand();
        
        // Test
        command.process(printOut, null, storage);
        
        // Asserts
        printOut.flush();
        
        String[] response = stringOut.toString().split("\n");
        
        Assert.assertTrue("Should respond with an OK message.", response[0].startsWith(POP3ResponseType.OK));
        Assert.assertEquals("Should return the first message", "1 88", response[1]);
        Assert.assertEquals("Should return the first message", "2 88", response[2]);
        Assert.assertEquals("Should return the first message", ".\r", response[3]);

        
    }

}
