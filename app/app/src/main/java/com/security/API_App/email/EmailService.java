package com.security.API_App.email;

public interface EmailService {
    void sendMail(String to, String email, String title);
    String buildEmail(String name, String data, String email_title, boolean isLink);
}
