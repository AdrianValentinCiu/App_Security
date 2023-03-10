package com.security.API_App.register;

import com.security.API_App.email.EmailService;
import com.security.API_App.register.token_registration.ConfirmationToken;
import com.security.API_App.register.token_registration.ConfirmationTokenService;
import com.security.API_App.user.Role;
import com.security.API_App.user.User;
import com.security.API_App.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final ConfirmationTokenService confirmationTokenService;
    private EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public boolean register(RegistrationRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .enabled(false)
                .build();

        String token = userService.signUpUser(user);

        String link = "http://localhost:8080/api/v1/auth/register/confirm?token=" + token;
        emailService.sendMail(
                request.getEmail(),
                emailService.buildEmail(request.getFirstName(), link, "Confirm your email\n",true));

        return true;
    }

    @Transactional
    public boolean confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            //throw new IllegalStateException("email already confirmed");
            return false;
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            return false;
            //throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableAppUser(
                confirmationToken.getUser().getEmail());
        return true;
    }
}
