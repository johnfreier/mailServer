package com.johnfreier.mail.server;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMTPClientIT {

    private static final Logger log = LoggerFactory.getLogger(SMTPClientIT.class);
    
    @Test
    public void test() throws Exception {
        
        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", "localhost");
        properties.setProperty("mail.smtp.port", "2323");

        Session session = Session.getDefaultInstance(properties);
        
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("test@client.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("test@server.com"));
        message.setSubject("This is the Subject Line!");
        message.setText("This is actual message");
        
        Transport.send(message);
        
        log.info("Sent message successfully....");
        
    }
    
}
