package com.johnfreier.mail.server;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class POP3ClientIT {

    private static final Logger log = LoggerFactory.getLogger(POP3ClientIT.class);
    
    @Test
    public void test() throws Exception {
        
        String host = "localhost";
        String user = "USERNAME";
        String password = "PASSWORD";
        
        Properties properties = System.getProperties();
        Session session = Session.getDefaultInstance(properties);
        Store store = session.getStore("pop3");
        store.connect(host, 2002, user, password);
        Folder inbox = store.getFolder("Inbox");
        inbox.open(Folder.READ_ONLY);
        
        Message[] messages = inbox.getMessages();
        
        log.info("Total messages:" + messages.length);
        
        for (int i = 0; i < messages.length; i++) {
            log.info("=======================================");
            log.info("Message " + (i + 1));
            log.info("Data:" + messages[i]);
            log.info("From : " + messages[i].getFrom()[0]);
            log.info("Subject : " + messages[i].getSubject());
            log.info("Sent Date : " + messages[i].getContent());
            log.info("=======================================");
          }

        
        
        log.info("Done!");
        
    }
}
