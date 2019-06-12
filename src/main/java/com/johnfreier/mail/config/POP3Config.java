package com.johnfreier.mail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class POP3Config implements Config {

    @Value("${pop3.enabled:true}")
    private boolean enabled = true;

    @Value("${pop3.port:1100}")
    private int port = 1100;

    @Value("${pop3.mail.new:}")
    private String mailNewFolder;

    @Value("${pop3.mail.deleted:}")
    private String mailDeletedFolder;

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
     * The POP3 server needs to read and write to files so what file extension should it use.
     * 
     * @return
     */
    public String getFileExtension() {
        return fileExtension;
    }

    /**
     * The file system folder new mail should be saved under.
     * 
     * @return
     */
    public String getMailNewFolder() {
        return mailNewFolder;
    }

    /**
     * The file system folder deleted mail should be moved to.
     * 
     * @return
     */
    public String getMailDeletedFolder() {
        return mailDeletedFolder;
    }

}
