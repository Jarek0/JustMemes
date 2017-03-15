package edu.pl.pollub.service;

import org.springframework.mail.SimpleMailMessage;

/**
 * Created by Dell on 2017-03-15.
 */
public interface MailService {
    void sendMail(String from, String to, String subject, String body);

    void sendMail(SimpleMailMessage message);
}
