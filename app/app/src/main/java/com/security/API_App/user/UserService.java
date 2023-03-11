package com.security.API_App.user;

import com.security.API_App.token.token_validate.ValidateToken;
import com.security.API_App.token.token_validate.ValidateTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ValidateTokenService confirmationTokenService;

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

        ValidateToken confirmationToken = new ValidateToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveValidateToken(confirmationToken);
        return token; // generated token for email confirmation
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }

    public String getUserFirstName(String email){
        Optional<User> user = userRepository.findByEmail(email);
        String name = "not found";
        if(user.isPresent()) {
            name = user.get().getFirstName();
        }
        return name;
    }

    public User getUser(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            return  user.get();
        }
        return null;
    }

    @Transactional(readOnly = true)
    public User getUser(Integer user_id){
        System.out.println("Trying to find the user!");
        Optional<User> user = userRepository.findById(user_id);
        if(user.isPresent()) {
            System.out.println("User found!");
            return  user.get();
        }
        System.out.println("User not found!");
        return null;
    }
}


