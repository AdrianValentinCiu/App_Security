package com.security.API_App.demo;

import com.security.API_App.user.User;
import com.security.API_App.user.UserRepository;
import com.security.API_App.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/demo-controller")
@AllArgsConstructor
public class DemoController {
    private final UserService userService;

    @GetMapping("/test")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("logged in!");
    }

    @GetMapping("/user_data/{user_id}")
    @ResponseBody
    public User retreiveUserData(@PathVariable Integer user_id){
        return userService.getUser(user_id);

    }
}
