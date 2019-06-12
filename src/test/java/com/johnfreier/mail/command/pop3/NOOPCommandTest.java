package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

public class NOOPCommandTest {

    @Test
    public void test() {

        // Data Setup
        StringWriter stringOut = new StringWriter();
        PrintWriter printOut = new PrintWriter(stringOut);

        NOOPCommand command = new NOOPCommand();
        
        // Test
        command.process(printOut, null, null);
        
        // Asserts
        printOut.flush();
        
        String[] response = stringOut.toString().split("\n");
        
        Assert.assertEquals("Should respond with an OK message.", POP3ResponseType.OK, response[0]);
        
    }

}
