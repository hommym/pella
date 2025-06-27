package com.pellanotes.pella.features.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pellanotes.pella.common.dtos.SimpleResponse;
import com.pellanotes.pella.features.auth.dtos.SignUpDto;
import com.pellanotes.pella.features.auth.dtos.VerifyAccountDto;
import jakarta.validation.Valid;





@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    
    private final AuthService authService;


    public AuthController(AuthService authService){
        this.authService=authService;

    }


    @PostMapping("/sign-up")
    public ResponseEntity<SimpleResponse> signUp(@RequestBody @Valid SignUpDto signUpDto) {
        return ResponseEntity.ok(this.authService.signup(signUpDto));
    }
    

    @PostMapping("/account/verify")
    public ResponseEntity<SimpleResponse> verifyAccount(@RequestBody @Valid VerifyAccountDto verfDto){
        return ResponseEntity.ok(this.authService.verifyAccount(verfDto));
    }
    

    @PostMapping("/resend-otp/{email}")
    public ResponseEntity<SimpleResponse> postMethodName(@PathVariable(name = "email") String email) {
        return ResponseEntity.ok(this.authService.resendOtp(email));
    }
    
 

    

    //account verification

    //otp resend 

    // account login 

    // account reset

    // password change
    
    //email change

    // account update (ie name ,username,)

    //profile pic updates

    





}
