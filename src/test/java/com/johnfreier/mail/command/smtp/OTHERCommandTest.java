package com.johnfreier.mail.command.smtp;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

import com.johnfreier.mail.storage.TestSMTPStorage;

public class OTHERCommandTest {

    @Test
    public void process() {

        // Data Setup
        StringWriter stringOut = new StringWriter();
        PrintWriter printOut = new PrintWriter(stringOut);

        String in = "Hello will this get added?";

        TestSMTPStorage storage = new TestSMTPStorage();
        storage.setRecoring(true);

        OTHERCommand command = new OTHERCommand();

        // Test
        command.process(printOut, in, storage);

        // Asserts
        Assert.assertEquals("The data should have been captured.", in + System.lineSeparator(), storage.getData());
        Assert.assertEquals("Recording should be on.", true, storage.isRecording());

    }
    
    @Test
    public void processEndRecording() {

        // Data Setup
        StringWriter stringOut = new StringWriter();
        PrintWriter printOut = new PrintWriter(stringOut);

        String inData = "Hello will this get added?";

        TestSMTPStorage storage = new TestSMTPStorage();
        storage.setRecoring(true);

        OTHERCommand command = new OTHERCommand();

        // Test
        command.process(printOut, inData, storage);
        
        String inStop = ".";
        
        command.process(printOut, inStop, storage);

        // Asserts
        printOut.flush();

        String response[] = stringOut.toString().split(System.lineSeparator());
        
        Assert.assertEquals("Should return an OK.", SMTPResponseType.OK, response[0]);

        Assert.assertEquals("Recorder should be stopped", false, storage.isRecording());
        
        Assert.assertEquals("The data should have been captured.", inData + System.lineSeparator(), storage.getData());

    }

}
