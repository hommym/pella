package com.pellanotes.pella.common.handlers.filters;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.servlet.HandlerExceptionResolver;

import com.pellanotes.pella.common.exceptions.UnauthorizeRequest;
import com.pellanotes.pella.common.utils.JwtService;
import com.pellanotes.pella.database.models.User;
import com.pellanotes.pella.database.repositories.UserRepo;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;




public class AuthFilter implements  Filter {




    private final JwtService jwtService;
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final UserRepo userRepo;


    public AuthFilter(JwtService jwt,HandlerExceptionResolver handlerExceptionResolver,UserRepo userRepo){
        this.jwtService=jwt;
        this.handlerExceptionResolver=handlerExceptionResolver;
        this.userRepo=userRepo;

    }


    @Override
    @Transactional
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
               
                HttpServletRequest req = (HttpServletRequest) request;
                HttpServletResponse res= ( HttpServletResponse) response;

                String token= req.getHeader("Authorization");

                try {

                if(token==null)throw new UnauthorizeRequest("Authorization Header not set"); 
                else if(!(token.startsWith("Bearer ")))throw new UnauthorizeRequest("Token must in the format Bearer <token>"); 

                
                String[] parts = token.split("Bearer\\s+");
                String actualToken = parts.length > 1 ? parts[1] : "";

                if(!this.jwtService.isTokenValid(actualToken))throw new UnauthorizeRequest("Invalid Auth token");

                //extract email and add it to the request(N/A)
                String userEmail=this.jwtService.extractEmail(actualToken);
                Optional<User> user= this.userRepo.getUserByEmail(userEmail);
                
                req.setAttribute("user", user.get());
                chain.doFilter(request, response);
                    
                } catch (Exception ex) {
                    this.handlerExceptionResolver.resolveException(req, res, null, ex);
                }
    }

 
    
    




}
