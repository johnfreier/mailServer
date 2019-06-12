package com.johnfreier.mail.server;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.command.POP3Command;
import com.johnfreier.mail.command.pop3.AUTHCommand;
import com.johnfreier.mail.command.pop3.CAPACommand;
import com.johnfreier.mail.command.pop3.DELECommand;
import com.johnfreier.mail.command.pop3.LISTCommand;
import com.johnfreier.mail.command.pop3.NOOPCommand;
import com.johnfreier.mail.command.pop3.PASSCommand;
import com.johnfreier.mail.command.pop3.QUITCommand;
import com.johnfreier.mail.command.pop3.RETRCommand;
import com.johnfreier.mail.command.pop3.STATCommand;
import com.johnfreier.mail.command.pop3.UIDLCommand;
import com.johnfreier.mail.command.pop3.USERCommand;
import com.johnfreier.mail.command.pop3.XTNDCommand;
import com.johnfreier.mail.config.POP3Config;
import com.johnfreier.mail.storage.FilePOP3Storage;
import com.johnfreier.mail.storage.POP3Storage;

public class POP3Server {
    
    private static Logger log = LoggerFactory.getLogger(POP3Server.class);
    
    public static void start(POP3Config config) throws Exception {
        
        if (config.isEnabled() == false) {
            log.info("POP3 Server is disabled");
            return;
        }

        List<POP3Command> commands = new ArrayList<>();
        commands.add(new AUTHCommand());
        commands.add(new CAPACommand());
        commands.add(new DELECommand());
        commands.add(new LISTCommand());
        commands.add(new NOOPCommand());
        commands.add(new PASSCommand());
        commands.add(new QUITCommand());
        commands.add(new RETRCommand());
        commands.add(new STATCommand());
        commands.add(new UIDLCommand());
        commands.add(new USERCommand());
        commands.add(new XTNDCommand());
        
        try (ServerSocket listener = new ServerSocket(config.port())) {
            
            log.info("The POP3 server is listening on port {}...", config.port());
            
            while (true) {
                
                Scanner scanner = null;
                
                POP3Storage storage = new FilePOP3Storage(config);
                
                try (Socket socket = listener.accept()) {

                    log.info("A client has connected. {}", socket.getRemoteSocketAddress());

                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("+OK POP3 server ready");

                    scanner = new Scanner(socket.getInputStream());
                    while (scanner.hasNext()) {

                        String line = scanner.nextLine();
                        log.debug("{}>{}", socket.getRemoteSocketAddress(), line);

                        boolean isFound = false;
                        for (POP3Command pop3Command : commands) {
                            if (line.startsWith(pop3Command.command())) {
                                pop3Command.process(out, line, storage);
                                isFound = true;
                            }
                        }

                        if (isFound == false) {
                            log.warn("{} Unsupported command:", socket.getRemoteSocketAddress(),  line);
                        }

                    }

                } catch (Exception e) {

                    e.printStackTrace();

                } finally {
                    scanner.close();
                    storage.commit();
                }

            }

        }

    }

}
