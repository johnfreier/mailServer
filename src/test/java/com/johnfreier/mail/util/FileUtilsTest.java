package com.johnfreier.mail.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtilsTest {

    private static final Logger log = LoggerFactory.getLogger(FileUtilsTest.class);
    
    @Test
    public void test() throws Exception {
        
        Path path = Paths.get(System.getProperty("user.dir"));
        
        log.info("Path: " + path.toAbsolutePath().toString());
        
        List<Path> files = Files.list(path)
                .filter(Files::isRegularFile)
                .filter(FileUtils.isFileType(".md"))
                .sorted()
                .collect(Collectors.toList());
        
        for (Path file : files) {
            
            log.info("Found file:" + file.toAbsolutePath().toString());
            
        }
        
    }

}
