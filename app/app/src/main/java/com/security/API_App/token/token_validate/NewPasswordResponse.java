package com.security.API_App.token.token_validate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewPasswordResponse {
    private String token;
    private String email;
    private String newPassword;
}
