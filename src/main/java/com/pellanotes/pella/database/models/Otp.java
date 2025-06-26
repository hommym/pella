package com.pellanotes.pella.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="otp")
public class Otp {
    @Id
    @Column(name="user_id")
    private  Long userId;


    @Column(nullable=false)
    private Integer code;


    @OneToOne
    @MapsId
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

    public Long getId(){
        return this.userId;
    }

    public int getOtpCode(){
        return code.intValue();
    }


}
