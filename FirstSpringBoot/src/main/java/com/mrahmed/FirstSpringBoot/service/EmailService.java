package com.mrahmed.FirstSpringBoot.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage mes= javaMailSender.createMimeMessage();
        MimeMessageHelper message =new MimeMessageHelper(mes, true);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);


        javaMailSender.send(mes);
    }




}