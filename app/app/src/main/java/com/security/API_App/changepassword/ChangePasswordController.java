package com.security.API_App.changepassword;

import com.security.API_App.email.EmailResponse;
import com.security.API_App.register.RegistrationRequest;
import com.security.API_App.token.token_validate.NewPasswordResponse;
import com.security.API_App.token.token_validate.TokenResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/password/change_password")
@RequiredArgsConstructor
@CrossOrigin
public class ChangePasswordController {
    private final ChangePasswordService changePasswordService;

    @PostMapping("/send_email_code")
    public ResponseEntity<String> sendPasswordCode(@RequestBody EmailResponse emailResponse){
        return ResponseEntity.ok(changePasswordService.sendPasswordCode(emailResponse) + " ");
    }


    @PutMapping("/validate_email_code")
    public ResponseEntity<String> validatePasswordCode(@RequestBody TokenResponse tokenResponse){
        return ResponseEntity.ok(changePasswordService.confirmPasswordCode(tokenResponse) + " ");
    }

    @PutMapping("/new_password")
    public ResponseEntity<String> changePassword(@RequestBody NewPasswordResponse newPasswordResponse){
        return ResponseEntity.ok(changePasswordService.changePassword(newPasswordResponse) + " ");
    }
}
