package com.security.API_App.changepassword.token_change_password;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChangePasswordTokenService {

    private final ChangePasswordTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ChangePasswordToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ChangePasswordToken> getToken(Integer token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
