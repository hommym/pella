package com.pellanotes.pella.common.configs;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.pellanotes.pella.common.handlers.filters.AuthFilter;
import com.pellanotes.pella.common.utils.JwtService;
import com.pellanotes.pella.database.repositories.UserRepo;

@Configuration
public class FiltersConfigs {
    

        private final JwtService jwt;
        private final HandlerExceptionResolver handlerExceptionResolver;
        private final UserRepo userRepo;


        public FiltersConfigs(JwtService jwt,HandlerExceptionResolver handlerExceptionResolver,UserRepo userRepo){
            this.jwt=jwt;
            this.handlerExceptionResolver=handlerExceptionResolver;
            this.userRepo=userRepo;
        }



        @Bean
        public FilterRegistrationBean<AuthFilter> authFilterRegistrationBean(){
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthFilter(this.jwt,this.handlerExceptionResolver,this.userRepo));
        registrationBean.addUrlPatterns("/api/v1/account/*"); 
        // more urls can be added
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
