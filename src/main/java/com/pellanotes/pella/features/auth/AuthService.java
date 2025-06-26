package com.pellanotes.pella.features.auth;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pellanotes.pella.common.dtos.SimpleResponse;
import com.pellanotes.pella.common.exceptions.ResourceConflict;
import com.pellanotes.pella.common.exceptions.ResourceNotFound;
import com.pellanotes.pella.common.exceptions.UnauthorizeRequest;
import com.pellanotes.pella.common.utils.EmailService;
import com.pellanotes.pella.common.utils.PasswordService;
import com.pellanotes.pella.database.models.Otp;
import com.pellanotes.pella.database.models.User;
import com.pellanotes.pella.database.repositories.OtpRepo;
import com.pellanotes.pella.database.repositories.UserRepo;
import com.pellanotes.pella.features.auth.dtos.SignUpDto;
import com.pellanotes.pella.features.auth.dtos.VerifyAccountDto;


import jakarta.transaction.Transactional;

@Service
public class AuthService {
 


    private final UserRepo userRepo;
    private final OtpRepo otpRepo;
    private final PasswordService passwdService;
    private final EmailService   emailService;


    public AuthService(UserRepo userRepo,OtpRepo otpRepo,PasswordService passwdService,EmailService   emailService){
        this.userRepo=userRepo;
        this.otpRepo=otpRepo;
        this.passwdService=passwdService;
        this.emailService=emailService;
    }

    @Transactional
    SimpleResponse signup(SignUpDto dto){

        // check if email and username exist
        if(userRepo.checkEmail(dto.email).isPresent())throw new ResourceConflict("User with this email already exist");
        else if (userRepo.checkUserName(dto.username).isPresent())throw new ResourceConflict("User with this username already exist");

        User user= new User(dto.fullName,dto.email,passwdService.encryptPasswd(dto.password),dto.username);
        user= userRepo.save(user);
        // set up otp 
        Otp otp= new Otp(user);
        otp= otpRepo.save(otp);

        // sending congrats email
        emailService.sendSignUpEmail(dto.email, dto.username);

        //send otp to user
        emailService.sendOtpEmail(dto.email, dto.username, otp.getOtpCode());

        return new SimpleResponse("Account creation successful, verify account to login");
    }

    
    @Transactional
    SimpleResponse verifyAccount(VerifyAccountDto dto){

        // task
        Optional<User> user= this.userRepo.getUserByEmail(dto.email);

        if(user.isEmpty()) throw new ResourceNotFound("No account with this "+dto.email+" exit");

        Optional<Integer> otpCode= this.otpRepo.getOtpCodeByUserId((user.get()).getId());

        if(otpCode.isEmpty())throw new ResourceNotFound("No Otp code has been sent for this account");
        else if(otpCode.get().intValue()!=dto.otpCode.intValue())throw new UnauthorizeRequest("Invalid Otp, please check otp and try again");

        this.otpRepo.deleteById((user.get()).getId());

        return new SimpleResponse("Account verified successfully");
    }

    

}
