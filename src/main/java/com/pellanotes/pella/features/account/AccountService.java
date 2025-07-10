package com.pellanotes.pella.features.account;

import org.springframework.stereotype.Service;

import com.pellanotes.pella.common.dtos.SimpleResponse;
import com.pellanotes.pella.common.exceptions.ForbiddenRequest;
import com.pellanotes.pella.common.utils.PasswordService;
import com.pellanotes.pella.database.models.User;
import com.pellanotes.pella.database.repositories.UserRepo;
import com.pellanotes.pella.features.account.dtos.ChangePasswdDto;



@Service
public class AccountService {
    

    private final PasswordService passwdService;
    private final UserRepo userRepo;



    public AccountService(PasswordService passwdService,UserRepo userRepo){
        this.passwdService=passwdService;
        this.userRepo=userRepo;
    }



    public SimpleResponse changePassword(ChangePasswdDto dto,User user) {

        boolean isOldPasswdValid= this.passwdService.matchPasswd(dto.oldPasswd, user.getPassword());

        if(!isOldPasswdValid) throw new ForbiddenRequest("Wrong oldPassword");

        String hashedPasswd= this.passwdService.encryptPasswd(dto.newPasswd);

        this.userRepo.updatePassword(user.getId(), hashedPasswd);
        
        return new SimpleResponse("Password has been changed sucessfully");
    }





}
