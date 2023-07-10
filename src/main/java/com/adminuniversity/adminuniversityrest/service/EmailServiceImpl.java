package com.adminuniversity.adminuniversityrest.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendEmail(String destination, String title, String content) throws UnirestException {

        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/sandboxff6c9cd81e314358a6ed6be11b29e8a6.mailgun.org" + "/messages")
			.basicAuth("api", "0567a20d296e9c16c766e364e80b6441-262b213e-48cc93a6")
                .queryString("from", "AdminUniversity <admin@university.com>")
                .queryString("to", destination)
                .queryString("subject", title)
                .queryString("text", content)
                .asJson();

        System.out.println(request.getBody());
    }
}
