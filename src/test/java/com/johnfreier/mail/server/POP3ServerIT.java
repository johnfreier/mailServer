package com.johnfreier.mail.server;

import org.junit.Test;

import com.johnfreier.mail.config.POP3Config;
import com.johnfreier.mail.server.POP3Server;

public class POP3ServerIT {

    @Test
    public void test() throws Exception {
        
        POP3Config pop3Config = new POP3Config();
        
        POP3Server.start(pop3Config);
        
    }

}
