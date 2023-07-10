package com.adminuniversity.adminuniversityrest.service;

import com.mashape.unirest.http.exceptions.UnirestException;

public interface EmailService {
    void sendEmail(String destination, String title, String content) throws UnirestException;
}
