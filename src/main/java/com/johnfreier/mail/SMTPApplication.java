package com.johnfreier.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.johnfreier.mail.config.SMTPConfig;
import com.johnfreier.mail.server.SMTPServer;

@Component
public class SMTPApplication extends Thread {

    @Autowired
    private SMTPConfig smtpConfig;
    
    @Override
    public void run() {
        
        try {
            
            SMTPServer.start(smtpConfig);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
