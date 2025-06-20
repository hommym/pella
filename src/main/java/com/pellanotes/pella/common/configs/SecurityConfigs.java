package com.pellanotes.pella.common.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SecurityConfigs {
    
    @Bean
    public PasswordEncoder bCrypt(){
        return new BCryptPasswordEncoder();
    }

}
