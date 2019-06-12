package com.johnfreier.mail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SMTPConfig implements Config {

    @Value("${smtp.enabled:true}")
    private boolean enabled = true;

    @Value("${smtp.port:2525}")
    private int port = 2525;

    @Value("${smtp.mail:}")
    private String mailFolder;

    @Value("${mail.fileExtension:}")
    private String fileExtension;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public int port() {
        return port;
    }

    /**
     * What file extension should mail file be saved as.
     * 
     * @return
     */
    public String getFileExtension() {
        return fileExtension;
    }

    /**
     * What file system path should new mail be saved under.
     * 
     * @return
     */
    public String getMailFolder() {
        return mailFolder;
    }

}
