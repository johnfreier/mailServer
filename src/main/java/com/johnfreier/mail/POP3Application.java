package com.johnfreier.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.johnfreier.mail.config.POP3Config;
import com.johnfreier.mail.server.POP3Server;

@Component
public class POP3Application extends Thread {

    @Autowired
    private POP3Config pop3Config;
    
    @Override
    public void run() {
        
        try {
            
            POP3Server.start(pop3Config);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
