package com.pellanotes.pella.database.models;

import com.pellanotes.pella.common.exceptions.ValidatorExceptions;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="full_name",nullable=false)
    String fullName;

    @Column(name="user_name")
    String userName;

    @Column(nullable=false)
    String email;

    @Column(nullable=false)
    String password;

    String profile;

    @Column(name="is_verfied",nullable=false)
    private  boolean isVerified=false;

    @Column(name="is2fa_on",nullable=false)
    private  boolean is2FAOn=false;

    

    //for JPA
    public User(){

    }


    public User(String fullName,String email,String passwd){
        this.fullName=fullName;
        this.email=this.isEmailValid();
        this.password=this.isPasswdValid();
        
    }


    // getter , setters and validator 

    private String isEmailValid(){
        if (this.email == null || !this.email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new ValidatorExceptions("Email must be of the form username@domain.com");
        }
        return this.email;
    }

    private String isPasswdValid(){

        // Password must be at least 8 characters long, contain at least one uppercase letter,
        // one lowercase letter, one digit, and one special character.
        if (this.password == null || 
            !this.password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            // If password is not valid, throw an exception
            throw new ValidatorExceptions("Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character.");
        }
        return this.password;
    }

    Long getId(){
        return this.id;
    }

  
}
