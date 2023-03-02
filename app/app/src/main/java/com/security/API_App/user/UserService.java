package com.security.API_App.user;

import com.security.API_App.register.token_registration.ConfirmationToken;
import com.security.API_App.register.token_registration.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public String signUpUser(User appUser) {

        if (userRepository.findByEmail(appUser.getEmail()).isPresent()) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        //String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        //appUser.setPassword(encodedPassword);

        userRepository.save(appUser); // POST

        String token = UUID.randomUUID().toString(); // create token

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token; // generated token for email confirmation
    }

    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }
}
