package com.adminuniversity.adminuniversityrest.service;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(String destination, String title, String content) throws MessagingException;
}
