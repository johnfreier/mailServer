package com.johnfreier.mail.storage;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.config.POP3Config;
import com.johnfreier.mail.util.FileUtils;

public class FilePOP3Storage implements POP3Storage {

    private static final Logger log = LoggerFactory.getLogger(FilePOP3Storage.class);

    private String mailFolderPath = "";

    private String deleteFolderPath = "delete";
    
    private String fileExtension = "";

    private List<Long> deletes = new ArrayList<>();

    public FilePOP3Storage(POP3Config config) {

        // Setup the file extension.
        if (Strings.isBlank(config.getFileExtension()) == false) {
            
            fileExtension = config.getFileExtension();
            
        }
        
        // Setup the new message folder.
        if (Strings.isBlank(config.getMailNewFolder())) {

            Path path = Paths.get(System.getProperty("user.dir"));

            mailFolderPath = path.toAbsolutePath().toString();

        } else {

            mailFolderPath = config.getMailNewFolder();

        }

        log.debug("Setting up new mail folder: {} {}", mailFolderPath, fileExtension);

        // Setup the delete message folder.
        if (Strings.isBlank(config.getMailDeletedFolder())) {

            try {

                Path parent = Paths.get(System.getProperty("user.dir"));
                Path child = parent.resolve("deleted");

                Files.createDirectories(child);

                deleteFolderPath = child.toAbsolutePath().toString();

            } catch (Exception e) {

                log.error("Unable to set the file path for the deleted mail folder.");

                e.printStackTrace();
            }

        } else {

            deleteFolderPath = config.getMailDeletedFolder();

        }

        log.debug("Setting up deleted mail folder: {} {}", deleteFolderPath, fileExtension);

        
    }

    @Override
    public long getMessageCount() {

        long count = 0;

        try {
            count = FileUtils.getFileCount(mailFolderPath, fileExtension);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;

    }

    @Override
    public long getTotalMessageSize() {

        long size = 0;

        try {

            List<Path> files = FileUtils.getFiles(mailFolderPath, fileExtension);

            for (Path path : files) {

                size += Files.size(path);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return size;
    }

    @Override
    public List<String> getMessages() {

        List<String> messages = new ArrayList<>();

        try {

            List<Path> files = FileUtils.getFiles(mailFolderPath, fileExtension);

            for (Path path : files) {

                String contents = Files.readAllLines(path, StandardCharsets.UTF_8).stream().collect(Collectors.joining("\n"));

                messages.add(contents);
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return messages;
    }

    public String getMessage(long index) {

        String message = null;

        try {

            List<Path> files = FileUtils.getFiles(mailFolderPath, fileExtension);

            Path path = files.get(new Long(index).intValue());

            message = Files.readAllLines(path, StandardCharsets.UTF_8).stream().collect(Collectors.joining("\n"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;

    }

    @Override
    public boolean deleteMessage(long index) {

        deletes.add(index);

        return true;

    }

    @Override
    public boolean commit() {

        boolean result = true;

        try {

            List<Path> files = FileUtils.getFiles(mailFolderPath, fileExtension);

            for (long deleteIndex : deletes) {

                Path target = files.get(new Long(deleteIndex).intValue());

                Path destination = Paths.get(deleteFolderPath, target.getFileName().toString());

                log.info("Moving file from: {} to: {}", target.toAbsolutePath().toString(), destination.toAbsolutePath().toString());

                Files.move(target, destination, StandardCopyOption.REPLACE_EXISTING);

            }

        } catch (Exception e) {

            e.printStackTrace();

            result = false;

        }

        return result;
    }

}
