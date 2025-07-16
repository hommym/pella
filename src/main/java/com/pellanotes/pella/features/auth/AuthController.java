package com.pellanotes.pella.features.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pellanotes.pella.common.dtos.SimpleResponse;
import com.pellanotes.pella.features.auth.dtos.LoginDto;
import com.pellanotes.pella.features.auth.dtos.LoginResponse;
import com.pellanotes.pella.features.auth.dtos.PasswordResetDto;
import com.pellanotes.pella.features.auth.dtos.SignUpDto;
import com.pellanotes.pella.features.auth.dtos.VerifyAccountDto;

import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<SimpleResponse> verifyAccount(@RequestBody @Valid VerifyAccountDto verfDto,HttpServletRequest req){
        return ResponseEntity.ok(this.authService.verifyAccount(verfDto,req));
    }
    

    @PostMapping("/resend-otp/{email}")
    public ResponseEntity<SimpleResponse> resendOtp(@PathVariable(name = "email") String email) {
        return ResponseEntity.ok(this.authService.resendOtp(email));
    }
    
 

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginDto loginDto) {
        return ResponseEntity.ok(this.authService.login(loginDto));
    }
    
    

    @PostMapping("/account-reset/{email}")
    public ResponseEntity<SimpleResponse> accountReset(@PathVariable(name = "email") String email) {
        return ResponseEntity.ok(this.authService.accountReset(email));
    }
  


    @PostMapping("/password-reset")
    public ResponseEntity<SimpleResponse> postMethodName(@RequestBody @Valid PasswordResetDto passwdDto) {
        return ResponseEntity.ok(this.authService.passwordReset(passwdDto));
    }

    



    


}
