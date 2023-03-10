package com.security.API_App.changepassword;

import com.security.API_App.email.EmailResponse;
import com.security.API_App.register.RegistrationRequest;
import com.security.API_App.token.token_validate.TokenResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/change_password")
@RequiredArgsConstructor
@CrossOrigin
public class ChangePasswordController {
    private final ChangePasswordService changePasswordService;

    @PostMapping("/send_email_code")
    public ResponseEntity<String> sendPasswordCode(@RequestBody EmailResponse emailResponse){
        return ResponseEntity.ok(changePasswordService.sendPasswordCode(emailResponse) + " ");
    }


    @PostMapping("/validate_email_code")
    public ResponseEntity<String> validatePasswordCode(@RequestBody TokenResponse tokenResponse){
        System.out.println(tokenResponse.getToken());
        return ResponseEntity.ok(changePasswordService.confirmPasswordCode(tokenResponse) + " ");
    }
}
