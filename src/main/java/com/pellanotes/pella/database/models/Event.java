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
@Table(name="event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    @Column(nullable =false)
    public String title;

    public String description;

    
    @Column(name = "event_date",nullable = false)
    public LocalDate eventDate;


    @Column(name = "event_time")
    public LocalTime eventTime;

    @Column(name="user_id",insertable=false,updatable=false,nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Column(name="created_at",nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;


    @Column(nullable=false)
    public Boolean repe;




    public Event(){// jpa

    }

    public Event(String title,String description,User user,LocalDate date,LocalTime time,Boolean repeat){
        this.title = title;
        this.description = description;
        this.user = user;
        this.userId=user.getId();
        this.eventDate=date;
        this.eventTime=time;
        this.repe= repeat!=null?repeat:false;
    }


    Long getId(){
        return this.id;
    }

  

}
