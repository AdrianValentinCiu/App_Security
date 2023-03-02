package com.security.API_App.register;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth/registration")
@AllArgsConstructor
@CrossOrigin
public class RegistrationController {

    private final RegistrationService registrationService;
    // @RequestBody --> decodes JSON file
    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
