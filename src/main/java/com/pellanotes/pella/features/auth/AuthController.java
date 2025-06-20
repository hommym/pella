package com.pellanotes.pella.features.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pellanotes.pella.features.auth.dtos.SignUpDto;
import com.pellanotes.pella.features.auth.dtos.SignUpResponse;

import jakarta.validation.Valid;





@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    
    private final AuthService authService;


    public AuthController(AuthService authService){
        this.authService=authService;

    }


    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody @Valid SignUpDto signUpDto) {
        return ResponseEntity.ok(this.authService.signup(signUpDto));
    }
    


    

    // account creation

    // user name checker

    //account verification

    // account login 

    // account reset

    // password change
    
    //email change

    // account update (ie name ,username,)

    //profile pic updates

    





}
