package com.security.API_App.email;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");
            message.setFrom("adrian@ciu.com");
            message.setTo(to);
            message.setSubject("Confirm your email");
            message.setText(email, true);
            mailSender.send(mimeMessage);
        }
        catch (Exception e) {
            System.out.println("Error while Sending Mail");
        }
    }
}