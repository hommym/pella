package com.pellanotes.pella.database.models;

import com.pellanotes.pella.database.enums.SubStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="user_sub")
public class UserSub {  // user subscription


    @Id
    @Column(name = "sub_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subId;    // subscription id

    @Column(name = "plan_id",insertable = false,updatable = false)
    private Long planId;


    @Column(name = "user_id",insertable = false,updatable = false)
    private Long userId;  // id of user who is on the subscription



    @Enumerated(EnumType.STRING) 
    SubStatus status=SubStatus.Pending;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    SubPlan plan;



    public UserSub(){ //jpa


    }


    public UserSub(User user,SubPlan plan){ 
    this.user = user;
    this.plan = plan;
    }    

    Long getId(){
        return this.subId;
    }



    
}
