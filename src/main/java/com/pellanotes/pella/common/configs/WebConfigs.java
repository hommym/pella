package com.pellanotes.pella.common.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.pellanotes.pella.common.handlers.interceptors.DefaultNoteBookInterceptor;


@Configuration
public class WebConfigs implements WebMvcConfigurer{
 
    
    private final DefaultNoteBookInterceptor dNoteBookInterceptor;

    public WebConfigs(DefaultNoteBookInterceptor dNoteBookInterceptor){
        this.dNoteBookInterceptor=dNoteBookInterceptor;   
    }


     @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(this.dNoteBookInterceptor)
                .addPathPatterns("/api/v1/auth/account/verify")   
                .order(1);         

                // add other interceptors 
              
    }

}
