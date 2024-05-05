package com.example.utccroomreservation.security;

import com.example.utccroomreservation.student.LoginRequest;
import com.example.utccroomreservation.student.StudentResponse;
import com.example.utccroomreservation.student.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static javax.security.auth.callback.ConfirmationCallback.OK;

@RestController
@RequestMapping("/login")
@Slf4j
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> login(@RequestBody LoginRequest loginRequest){
            return ResponseEntity.status(200).body(
                    authService.authenticate(loginRequest)
            );
    }

    @GetMapping
    public String test(){
        return "ok";
    }
}

