package com.security.API_App.token.token_validate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ValidateTokenService {

    private final ValidateTokenRepository validateTokenRepository;

    public void saveValidateToken(ValidateToken token) {
        validateTokenRepository.save(token);
    }

    public Optional<ValidateToken> getToken(String token) {
        return validateTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return validateTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
