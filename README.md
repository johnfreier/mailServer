# Mail Servier
This is a simple POP3 and SMTP server.  It was developed to develop/test sending and receiving email.  It is super simple to setup and run.

It uses the file system to manage emails.  This allows a develoer to view the content of an email and even modifiy it.

## Requirements
Java 1.8

## Starting the server
Quick start

`./mail-0.0.1-SNAPSHOT.jar`

More complicated, add config properties on the end.

`./mail-0.0.1-SNAPSHOT.jar --pop3.enabled=false`

## Config

Here is a list of properties that can be used to help configure the servers.

| property           | default           | description                                       |
|--------------------|-------------------|---------------------------------------------------|
|pop3.enabled        | true              | Enable POP3 server.                               |
|pop3.port           | 1100              | Port POP3 Server listens on.                      |
|pop3.mail.new       | Current Directory | This is the directory where new mail gets placed. |
|pop3.mail.deleted   | Current Directory | This is the directory where new mail gets placed. |
|smtp.enabled        | true              | Enable SMTP server.                               |
|smtp.port           | 2323              | Port SMTP Sever listens on.                       |
|smtp.mail           | Current Directory | The current directory where mail is saved to.     |
|mail.fileExtension  | .mail             | This is the file extension mail is saved as.      |

## Debug
To see the debug log add the following command on the executable path

`--logging.level.com.johnfreier.mail=DEBUG`

## Tested With

Mail Applications
- Thunderbird

OSs
- OSX


# Development
This is a starts up with Spring-Boot.

## Requirements
 - Java 1.8
 - Maven 3.3.9

 ## Build Project
To run the project locally.

`mvn clean spring-boot-run`

To package the project in executable jar.

`mvn clean package`


## Resources
http://www.faqs.org/rfcs/rfc1939.html

