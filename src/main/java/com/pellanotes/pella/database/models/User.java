package com.pellanotes.pella.database.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="full_name",nullable=false)
    String fullName;

    @Column(unique = true)
    String username;

    @Column(nullable=false, unique = true)
    String email;

    @Column(nullable=false)
    String password;

    public String profile;

    @Column(name="is_verfied",nullable=false)
    private  boolean isVerified=false;

    @Column(name="is2fa_on",nullable=false)
    private  boolean is2FAOn=false;

    @OneToOne(mappedBy="user",cascade=CascadeType.PERSIST)
    private Otp otp;

    //for JPA
    public User(){

    }


    public User(String fullName,String email,String endcPasswd,String username,Otp otp){
        this.fullName=fullName;
        this.email=email;
        this.password=endcPasswd;
        this.username=username;
        this.otp=otp;
        this.saveUserToOtp();
    }


    // getter , setters and validator 

    // private String isEmailValid(){
    //     if (this.email == null || !this.email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
    //         throw new ValidatorExceptions("Email must be of the form username@domain.com");
    //     }
    //     return this.email;
    // }

    // private String isPasswdValid(){

    //     // Password must be at least 8 characters long, contain at least one uppercase letter,
    //     // one lowercase letter, one digit, and one special character.
    //     if (this.password == null || 
    //         !this.password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
    //         // If password is not valid, throw an exception
    //         throw new ValidatorExceptions("Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character.");
    //     }
    //     return this.password;
    // }

    private void saveUserToOtp(){
        this.otp.user=this;
    }

    public Long getId(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public boolean checkVerfStatus(){
        return this.isVerified;
    }

    public boolean check2FaStatus(){
        return this.is2FAOn;
    }

    public int getOtpCode(){
        return this.otp.getOtpCode();
    }

  
}
