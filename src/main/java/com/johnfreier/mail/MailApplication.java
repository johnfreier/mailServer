package com.johnfreier.mail;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.johnfreier.mail"})
public class MailApplication implements CommandLineRunner {
    
	public static void main(String[] args) throws Exception {
	    	    
	    SpringApplication app = new SpringApplication(MailApplication.class);
	    
	    app.run(args);

	}

    @Override
    public void run(String... args) throws Exception {

        
        POP3Application pop3 = createPOP3Application(); 
        pop3.start();
        
        SMTPApplication smtp = createSMTPApplication();
        smtp.start();
        
    }
    
    @Bean
    public POP3Application createPOP3Application() {
        return new POP3Application();
    }
    
    @Bean
    public SMTPApplication createSMTPApplication() {
        return new SMTPApplication();
    }


}
