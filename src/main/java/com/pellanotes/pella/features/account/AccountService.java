package com.pellanotes.pella.features.account;

import org.springframework.stereotype.Service;

import com.pellanotes.pella.common.dtos.SimpleResponse;
import com.pellanotes.pella.common.exceptions.ForbiddenRequest;
import com.pellanotes.pella.common.exceptions.ResourceConflict;
import com.pellanotes.pella.common.utils.PasswordService;
import com.pellanotes.pella.database.models.User;
import com.pellanotes.pella.database.repositories.UserRepo;
import com.pellanotes.pella.features.account.dtos.ChangePasswdDto;
import com.pellanotes.pella.features.account.dtos.UpdateAccountInfoDto;

import jakarta.transaction.Transactional;



@Service
public class AccountService {
    

    private final PasswordService passwdService;
    private final UserRepo userRepo;



    public AccountService(PasswordService passwdService,UserRepo userRepo){
        this.passwdService=passwdService;
        this.userRepo=userRepo;
    }



    @Transactional
    public SimpleResponse changePassword(ChangePasswdDto dto,User user) {

        boolean isOldPasswdValid= this.passwdService.matchPasswd(dto.oldPasswd, user.getPassword());

        if(!isOldPasswdValid) throw new ForbiddenRequest("Wrong oldPassword");

        String hashedPasswd= this.passwdService.encryptPasswd(dto.newPasswd);

        this.userRepo.updatePassword(user.getId(), hashedPasswd);
        
        return new SimpleResponse("Password has been changed sucessfully");
    }

    @Transactional
    public SimpleResponse updateAccountInfo(UpdateAccountInfoDto dto,User user) {

        // checking if there is any 
        if((this.userRepo.checkEmail(user.getId(),dto.email)).isPresent())throw new ResourceConflict("An account this email already exist");
        else if((this.userRepo.checkUserName(user.getId(),dto.username)).isPresent())throw new ResourceConflict("An account this username already exist");

        this.userRepo.updateInfo(user.getId(), dto.username, dto.email, dto.profile);
        return new SimpleResponse("Acount Info Update sucessful");
    }    





}
