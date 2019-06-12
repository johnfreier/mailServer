package com.johnfreier.mail.server;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfreier.mail.command.SMTPCommand;
import com.johnfreier.mail.command.smtp.DATACommand;
import com.johnfreier.mail.command.smtp.EHLOCommand;
import com.johnfreier.mail.command.smtp.MAILCommand;
import com.johnfreier.mail.command.smtp.OTHERCommand;
import com.johnfreier.mail.command.smtp.QUITCommand;
import com.johnfreier.mail.command.smtp.RCPTCommand;
import com.johnfreier.mail.command.smtp.RSETCommand;
import com.johnfreier.mail.command.smtp.SMTPResponseType;
import com.johnfreier.mail.config.SMTPConfig;
import com.johnfreier.mail.storage.FileSMTPStorage;
import com.johnfreier.mail.storage.SMTPStorage;

public class SMTPServer {

    private static Logger log = LoggerFactory.getLogger(SMTPServer.class);
    
    public static void start(SMTPConfig config) throws Exception {

        if (config.isEnabled() == false) {
            log.info("SMTP Server is disabled");
            return;
        }

        List<SMTPCommand> commands = new ArrayList<>();
        commands.add(new EHLOCommand());
        commands.add(new MAILCommand());
        commands.add(new RCPTCommand());
        commands.add(new DATACommand());
        commands.add(new OTHERCommand());
        commands.add(new RSETCommand());
        commands.add(new QUITCommand());

        try (ServerSocket listener = new ServerSocket(config.port())) {
            
            log.info("The SMTP server is listening on port {}...", config.port());
            
            while (true) {
                
                Scanner scanner = null;
                
                SMTPStorage storage = new FileSMTPStorage(config);
                
                try (Socket socket = listener.accept()) {

                    log.info("A client has connected. {}", socket.getRemoteSocketAddress());

                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(SMTPResponseType.READY);

                    scanner = new Scanner(socket.getInputStream());
                    while (scanner.hasNext()) {

                        String line = scanner.nextLine();
                        log.debug("{}>{}", socket.getRemoteSocketAddress(), line);

                        boolean isFound = false;
                        for (SMTPCommand smtpCommand : commands) {
                            
                            if (storage.isRecording()) {
                                
                                if ("OTHER".startsWith(smtpCommand.command())) {
                                    smtpCommand.process(out, line, storage);
                                    isFound = true;
                                    break;
                                }
                                
                            } else if (line.startsWith(smtpCommand.command())) {
                                smtpCommand.process(out, line, storage);
                                isFound = true;
                                break;
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
