package com.johnfreier.mail.storage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.config.SMTPConfig;
import com.johnfreier.mail.util.FileUtils;

public class FileSMTPStorage implements SMTPStorage {

    private static final Logger log = LoggerFactory.getLogger(FileSMTPStorage.class);

    private String mailFolderPath = "";

    private String from = "";

    private String to = "";

    private String data = "";
    
    private String fileExtension = "";

    private boolean recording = false;

    public FileSMTPStorage(SMTPConfig config) {

        // Setup the file extension.
        if (Strings.isBlank(config.getFileExtension()) == false) {
            
            fileExtension = config.getFileExtension();
            
        }
        
        // Setup the mail folder.
        if (Strings.isBlank(config.getMailFolder())) {

            Path path = Paths.get(System.getProperty("user.dir"));

            mailFolderPath = path.toAbsolutePath().toString();

        } else {

            mailFolderPath = config.getMailFolder();

        }

        log.debug("Setting up mail folder: {} {}", mailFolderPath, fileExtension);

    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public void setRecoring(boolean recording) {
        this.recording = recording;
    }

    @Override
    public boolean isRecording() {
        return recording;
    }

    @Override
    public void addData(String data) {
        this.data += data;
    }

    @Override
    public boolean commit() {

        long date = new Date().getTime();
        
        String fileName = new StringBuilder().append(date).append(fileExtension).toString();
        
        Path file = Paths.get(mailFolderPath, fileName);

        FileUtils.writeFile(file, data);

        log.info("Writing new email {}", file);
        
        return false;
    }

}
