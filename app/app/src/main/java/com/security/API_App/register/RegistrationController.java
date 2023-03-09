package com.security.API_App.register;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth/register")
@AllArgsConstructor
@CrossOrigin
public class RegistrationController {

    private final RegistrationService registrationService;
    // @RequestBody --> decodes JSON file
    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(registrationService.register(request) + "");
    }

    @GetMapping(path = "confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        return ResponseEntity.ok(registrationService.confirmToken(token) + "");
    }

}
