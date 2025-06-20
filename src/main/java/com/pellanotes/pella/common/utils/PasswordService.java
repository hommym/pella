package com.pellanotes.pella.common.utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    
    
    
    private final PasswordEncoder bcrypt;


    public PasswordService(PasswordEncoder bcrypt){
        this.bcrypt=bcrypt;
    }

    public String encryptPasswd(String rawPasswd){
        return bcrypt.encode(rawPasswd);
    }

    public boolean matchPasswd(String rawPasswd, String encryptedPasswd){
        return bcrypt.matches(rawPasswd, encryptedPasswd);
    }


}
