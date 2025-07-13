package com.pellanotes.pella.features.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pellanotes.pella.common.dtos.SimpleResponse;
import com.pellanotes.pella.database.models.User;
import com.pellanotes.pella.features.account.dtos.ChangePasswdDto;
import com.pellanotes.pella.features.account.dtos.UpdateAccountInfoDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;






@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    



    private final AccountService accountService;

    public AccountController(AccountService accountSerivce){
        this.accountService=accountSerivce;
    }





    @PatchMapping("/change-password")
    public ResponseEntity<SimpleResponse> changePassword(@RequestBody @Valid ChangePasswdDto passwdDto,HttpServletRequest req){
        return ResponseEntity.ok(this.accountService.changePassword(passwdDto,(User) req.getAttribute("user")));
    }


    @PatchMapping
    public ResponseEntity<SimpleResponse> updateAccountInfo(@RequestBody @Valid UpdateAccountInfoDto updateAccountDto,HttpServletRequest req){
        return ResponseEntity.ok(this.accountService.updateAccountInfo(updateAccountDto, (User)req.getAttribute("user")));
    }
    
    

    // account update (ie name ,username,profile)

    



}
