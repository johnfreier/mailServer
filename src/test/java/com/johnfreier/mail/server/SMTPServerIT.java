package com.johnfreier.mail.server;

import org.junit.Test;

import com.johnfreier.mail.config.SMTPConfig;
import com.johnfreier.mail.server.SMTPServer;

public class SMTPServerIT {

    @Test
    public void test() throws Exception {
        
        SMTPConfig smtpConfig = new SMTPConfig();
        
        SMTPServer.start(smtpConfig);
        
    }
    
}
