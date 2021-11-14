package Modelo;
// File Name SendEmail.java
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    public static void Send (String to, String msg){
       try {
           //String to = "jcervantesg05@gmail.com";
           String subject = "subject";
           //String msg ="email text....";
           //final String from ="enviarcorreoproyecto@gmail.com";
           final String from = "enviarcorreoproyecto@gmail.com";
           final String password = "jarodCervantes";


           Properties props = new Properties();
           props.setProperty("mail.transport.protocol", "smtp");
           props.setProperty("mail.host", "smtp.gmail.com");
           props.put("mail.smtp.auth", "true");
           props.put("mail.smtp.port", "465");
           props.put("mail.debug", "true");
           props.put("mail.smtp.socketFactory.port", "465");
           props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
           props.put("mail.smtp.socketFactory.fallback", "false");
           Session session = Session.getDefaultInstance(props,
                   new javax.mail.Authenticator() {
                       protected PasswordAuthentication getPasswordAuthentication() {
                           return new PasswordAuthentication(from, password);
                       }
                   });

           //session.setDebug(true);
           Transport transport = session.getTransport();
           InternetAddress addressFrom = new InternetAddress(from);

           MimeMessage message = new MimeMessage(session);
           message.setSender(addressFrom);
           message.setSubject(subject);
           //message.setContent(msg, "text/plain");
           // Send the actual HTML message.
           /*message.setContent(
                   "<h1>This is actual message embedded in HTML tags</h1>",
                   "text/html");*/
           message.setContent(
                   msg,
                   "text/html");
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

           transport.connect();
           Transport.send(message);
           transport.close();
       } catch (Exception e){
           System.out.println("no funca");
        }
    }
}
