package com.pellanotes.pella.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class Checkout {
    
    @Id
    @Column(name = "session_id")
    private String sessionId;


    @Column(name = "plan_id",insertable = false,updatable = false)
    private Long planId;


    @Column(name = "user_id",insertable = false,updatable = false)
    private Long userId;  // id of user who is on the subscription


    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    SubPlan plan;

    public Checkout(){ //jpa

    }



    public Checkout(String sessionId,User user,SubPlan plan){ 
    this.sessionId = sessionId;
    this.user = user;
    this.plan = plan;
    }



    String getId(){
        return this.sessionId;
    }

}
