package com.adminuniversity.adminuniversityrest.service;

import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendEmail(String destination, String title, String content) throws MessagingException {
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.mailgun.org");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("kodigo@mail2.jmarango.co",
                        "kodigo12345678");

            }
        });


        MimeMessage message = new MimeMessage(session);
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destination));
        message.setSubject(title);
        message.setText(content);
    }
}
