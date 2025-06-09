package com.pellanotes.pella.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="otp")
public class Otp {
    @Id
    @Column(name="user_id",insertable=false,updatable=false)
    private  Long userId;


    @Column(nullable=false)
    int code;


    @OneToOne
    @JoinColumn(name="user_id")
    User user;

    // for the JPA
    public  Otp(){

    }

    public Otp(User user){
        this.user=user;
        this.code=this.generateCode(6);
    }  

    private int generateCode(int numOfDigits){
        int min = (int) Math.pow(10, numOfDigits - 1);
        int max = (int) Math.pow(10, numOfDigits) - 1;
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    Long getId(){
        return this.userId;
    }

}
