package com.johnfreier.mail.command;

import java.io.PrintWriter;

import com.johnfreier.mail.storage.POP3Storage;

public interface POP3Command {

    /**
     * Process the command that was sent from the client.
     * 
     * @param out
     * @param in
     * @param storage
     */
    void process(PrintWriter out, String in, POP3Storage storage);
    
    /**
     * The command that is mapped from the client.
     * 
     * @return
     */
    String command();
    
}
