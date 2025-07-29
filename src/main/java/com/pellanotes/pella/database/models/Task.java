package com.pellanotes.pella.database.models;

import java.time.LocalDate;
import java.time.LocalTime;

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
@Table(name = "task")
public class Task {
 
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    @Column(nullable =false)
    private String title;

    private String description;

    @Column(name = "start_time",nullable = false)
    private LocalTime startTime;


    @Column(name = "end_time",nullable = false)    
    private LocalTime endTime;

    @Column(name="user_id",insertable=false,updatable=false,nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;


    @Column(name="created_at",nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;



    public Task(){ // for jpa

    }


      public Task(String title,String description,User user,LocalTime starTime,LocalTime endTime){
        this.title = title;
        this.description = description;
        this.user = user;
        this.startTime=starTime;
        this.endTime=endTime;
    }


     Long getId(){
        return this.id;
    }

}
