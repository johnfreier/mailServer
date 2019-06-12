package com.johnfreier.mail.command.smtp;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

import com.johnfreier.mail.storage.TestSMTPStorage;

public class QUITCommandTest {

    @Test
    public void processEndRecording() {

        // Data Setup
        StringWriter stringOut = new StringWriter();
        PrintWriter printOut = new PrintWriter(stringOut);

        String in = "QUIT";

        TestSMTPStorage storage = new TestSMTPStorage();

        QUITCommand command = new QUITCommand();

        // Test
        command.process(printOut, in, storage);

        // Asserts
        printOut.flush();

        String response[] = stringOut.toString().split(System.lineSeparator());
        
        Assert.assertEquals("Should return an QUIT response.", SMTPResponseType.CLOSE_CONNECTION, response[0]);


    }

}
