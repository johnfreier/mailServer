package com.johnfreier.mail.server;

import java.io.OutputStream;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class MessageTest {

    @Test
    public void test() throws Exception {
        
        MimeMessage message = new MimeMessage(Session.getDefaultInstance(System.getProperties()));
        
        message.setFrom(new InternetAddress("test@client.com"));
        message.setSubject("This is the subject");
        message.setText("What up?");
        
        OutputStream os = System.out;
        message.writeTo(os);
        
    }

}
