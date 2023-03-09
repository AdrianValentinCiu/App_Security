package com.security.API_App.changepassword;

import com.security.API_App.config.JwtService;
import com.security.API_App.email.EmailResponse;
import com.security.API_App.email.EmailService;
import com.security.API_App.register.RegistrationRequest;
import com.security.API_App.register.token_registration.ConfirmationTokenService;
import com.security.API_App.token.TokenRepository;
import com.security.API_App.user.Role;
import com.security.API_App.user.User;
import com.security.API_App.user.UserRepository;
import com.security.API_App.user.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChangePasswordService {
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public boolean sendPasswordCode(EmailResponse emailResponse) {
        System.out.println("Inside");
        //System.out.println(userService.findUserByFirstName(emailResponse.getEmail()));
        emailService.sendMail(
                emailResponse.getEmail(),
                emailService.buildEmail(userService.findUserByFirstName(emailResponse.getEmail()), "TEST"));

        return true;
    }



}
