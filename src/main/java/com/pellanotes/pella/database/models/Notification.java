package com.pellanotes.pella.database.models;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String message;

    boolean isRead=false;


    @Column(name="created_at")
    @CreationTimestamp
    private LocalDate createdAt;


    @Column(name="user_id",insertable=false,updatable=false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    


    public Notification(){ //jpa

    }


    public Notification(String message,User user){ //jpa
        this.message=message;
        this.user=user;
    }


    Long getId(){
        return this.id;
    }


}
