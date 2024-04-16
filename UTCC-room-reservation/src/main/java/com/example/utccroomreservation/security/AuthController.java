package com.example.utccroomreservation.security;

import com.example.utccroomreservation.student.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@Slf4j
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest){
        try {
            return authService.authenticate(loginRequest);
        }
        catch (Exception e){
            log.info(String.valueOf(e));
        }
        return "ok";
    }

    @GetMapping
    public String test(){
        return "ok";
    }
}

