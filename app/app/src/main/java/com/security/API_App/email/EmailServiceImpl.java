package com.security.API_App.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

//    public EmailServiceImpl(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }

    @Override
    @Async
    public void sendMail(String to, String email)
    {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("adrian@ciu.com");
            mailMessage.setTo(to);
            mailMessage.setText(email);
            mailMessage.setSubject("Confirm your email");
            mailSender.send(mailMessage);
        }
        catch (Exception e) {
            System.out.println("Error while Sending Mail");
        }
    }
}