package com.github.psycomentis06.isatexbackendservice.service;

import com.github.psycomentis06.isatexbackendservice.form.EmailForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderUsername;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(EmailForm form) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(form.getSubject());
        mailMessage.setFrom(senderUsername);
        mailMessage.setText(form.getMessageBody());
        mailMessage.setTo(form.getRecipient());
        javaMailSender.send(mailMessage);
    }
}
