package com.security.API_App.changepassword;

import com.security.API_App.email.EmailResponse;
import com.security.API_App.email.EmailService;
import com.security.API_App.token.token_validate.TokenResponse;
import com.security.API_App.token.token_validate.ValidateToken;
import com.security.API_App.token.token_validate.ValidateTokenService;
import com.security.API_App.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class ChangePasswordService {
    private final ValidateTokenService validateTokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public boolean sendPasswordCode(EmailResponse emailResponse) {
        Random random = new Random();
        String chgPsdToken = String.format("%04d", random.nextInt(10000));
        emailService.sendMail(
                emailResponse.getEmail(),
                emailService.buildEmail(userService.getUserFirstName(emailResponse.getEmail()), chgPsdToken, "Change your account password\n", false),
                "Change your password");
        ValidateToken validateToken = new ValidateToken(
                chgPsdToken,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                userService.getUser(emailResponse.getEmail())
        );
        validateTokenService.saveValidateToken(validateToken);
        return true;
    }

    @Transactional
    public boolean confirmPasswordCode(TokenResponse tokenResponse){
        // Confirm the token in the DB
        ValidateToken validateToken = validateTokenService
                .getToken(tokenResponse.getToken())
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (validateToken.getConfirmedAt() != null) {
            //throw new IllegalStateException("email already confirmed");
            return false;
        }

        if (validateToken.getUser().getId() != userService.getUser(tokenResponse.getEmail()).getId()) { // ID matching
            //throw new IllegalStateException("email already confirmed");
            return false;
        }

        LocalDateTime expiredAt = validateToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            return false;
            //throw new IllegalStateException("token expired");
        }

        validateTokenService.setConfirmedAt(tokenResponse.getToken());

        return true;
    }



}
