package com.fanset.dms.user.emails.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(
            String toEmail,
            String body,
            String subject
    ){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("genuslee.panashe@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);
        System.out.println("Mail Send Successfully .........");
    }
}
