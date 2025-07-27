package com.pellanotes.pella.features.auth;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pellanotes.pella.common.dtos.SimpleResponse;
import com.pellanotes.pella.common.exceptions.ResourceConflict;
import com.pellanotes.pella.common.exceptions.ResourceNotFound;
import com.pellanotes.pella.common.exceptions.UnauthorizeRequest;
import com.pellanotes.pella.common.utils.EmailService;
import com.pellanotes.pella.common.utils.JwtService;
import com.pellanotes.pella.common.utils.PasswordService;
import com.pellanotes.pella.database.models.Otp;
import com.pellanotes.pella.database.models.User;
import com.pellanotes.pella.database.repositories.OtpRepo;
import com.pellanotes.pella.database.repositories.UserRepo;
import com.pellanotes.pella.features.auth.dtos.LoginDto;
import com.pellanotes.pella.features.auth.dtos.LoginResponse;
import com.pellanotes.pella.features.auth.dtos.PasswordResetDto;
import com.pellanotes.pella.features.auth.dtos.SignUpDto;
import com.pellanotes.pella.features.auth.dtos.VerifyAccountDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class AuthService {
 


    private final UserRepo userRepo;
    private final OtpRepo otpRepo;
    private final PasswordService passwdService;
    private final EmailService   emailService;
    private final JwtService  jwtService;

    public AuthService(UserRepo userRepo,OtpRepo otpRepo,PasswordService passwdService,EmailService emailService,
    JwtService jwtService
    ){
        this.userRepo=userRepo;
        this.otpRepo=otpRepo;
        this.passwdService=passwdService;
        this.emailService=emailService;
        this.jwtService=jwtService;
    }

    @Transactional
    public  User isAccountValid(String email){
        Optional<User> user= this.userRepo.getUserByEmail(email);
        if(user.isEmpty()) throw new ResourceNotFound("No account with this "+email+" exit");
        return user.get();
    }


    @Transactional
    private void verifyOtp(Long userId ,Integer otp){
        Optional<Integer> otpCode= this.otpRepo.getOtpCodeByUserId(userId);

        if(otpCode.isEmpty())throw new ResourceNotFound("No Otp code has been sent for this account");
        else if(otpCode.get().intValue()!=otp.intValue())throw new UnauthorizeRequest("Invalid Otp, please check otp and try again");

        this.otpRepo.deleteById(userId);
    }

    @Transactional
    SimpleResponse signup(SignUpDto dto){

        // check if email and username exist
        if(userRepo.checkEmail(dto.email).isPresent())throw new ResourceConflict("User with this email already exist");
        else if (userRepo.checkUserName(dto.username).isPresent())throw new ResourceConflict("User with this username already exist");

        Otp otp= new Otp();
        User user= new User(dto.fullName,dto.email,passwdService.encryptPasswd(dto.password),dto.username,otp);
        userRepo.save(user);
        

        // sending congrats email
        emailService.sendSignUpEmail(dto.email, dto.username);

        //send otp to user
        emailService.sendOtpEmail(dto.email, dto.username, otp.getOtpCode(),false);

        return new SimpleResponse("Account creation successful, verify account to login");
    }

    
    @Transactional
    SimpleResponse verifyAccount(VerifyAccountDto dto,HttpServletRequest req){
        
        User user= this.isAccountValid(dto.email);
        Long userId=user.getId();

        if(user.checkVerfStatus())throw new ResourceConflict("Account has already been verified");
        this.verifyOtp(userId, dto.otpCode);

        // verifying user's account
        userRepo.verifyUser(userId);

        // adding user's account to the request object for post request processes
        req.setAttribute("user", user);

        return new SimpleResponse("Account verified successfully");
    }

    
    @Transactional
    SimpleResponse resendOtp(String email){

        User user= this.isAccountValid(email);
        Optional<Integer> otpCode= this.otpRepo.getOtpCodeByUserId(user.getId());

        if(otpCode.isEmpty()){
            Otp otp = new Otp(user);
            otpRepo.save(otp);
            otpCode = Optional.of(otp.getOtpCode());
        }
        //send otp to user
        emailService.sendOtpEmail(email, user.getUsername(), otpCode.get(),false);
        return new SimpleResponse("New Otp Code Sent"); 
    }


    @Transactional
    LoginResponse login(LoginDto dto){

        User user= this.isAccountValid(dto.email);
        String hashedPassword= user.getPassword();
        String username=user.getUsername();
        String profile=user.profile;
        boolean twoFAStatus=user.check2FaStatus();

        if(!this.passwdService.matchPasswd(dto.password, hashedPassword))throw new UnauthorizeRequest("Password Invalid");
        else if(!user.checkVerfStatus())throw new UnauthorizeRequest("Please verify account with otp to login");

        // creating jwt token with email
        String authToken= twoFAStatus?null:this.jwtService.getToken(dto.email);
        
        return new LoginResponse(dto.email,profile,username,authToken,twoFAStatus);  
    }


    @Transactional
    SimpleResponse accountReset(String email){
     User user= this.isAccountValid(email);
     String username= user.getUsername();    

     // set up otp 
     Otp otp= new Otp(user);
     otp= otpRepo.save(otp);
     
     this.emailService.sendOtpEmail(email, username, otp.getOtpCode(),true );
     return new SimpleResponse("Reset Otp has been sent ");
    }


    @Transactional
    SimpleResponse passwordReset(PasswordResetDto dto){

        User user= this.isAccountValid(dto.email);
        this.verifyOtp(user.getId(), dto.otpCode);

        // changing password 
        String hashedP= this.passwdService.encryptPasswd(dto.newPassword);
        this.userRepo.updatePassword(user.getId(),hashedP );

        return new SimpleResponse("Password has been reset successfully");   
    }

}
