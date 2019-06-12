package com.johnfreier.mail.command.pop3;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

public class QUITCommandTest {

    @Test
    public void test() {

        // Data Setup
        StringWriter stringOut = new StringWriter();
        PrintWriter printOut = new PrintWriter(stringOut);

        QUITCommand command = new QUITCommand();
        
        // Test
        command.process(printOut, null, null);
        
        // Asserts
        printOut.flush();
        
        String[] response = stringOut.toString().split("\n");
        
        Assert.assertEquals("Should respond with an OK message.", ".", response[0]);
        
    }

}
