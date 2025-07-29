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
    private String title;

    private String description;

    
    @Column(name = "event_date",nullable = false)
    private LocalDate eventDate;


    @Column(name = "event_time")
    private LocalTime eventTime;

    @Column(name="user_id",insertable=false,updatable=false,nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;


    @Column(name="created_at",nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;


    public Event(){// jpa

    }

    public Event(String title,String description,User user){
        this.title = title;
        this.description = description;
        this.user = user;
    }


    Long getId(){
        return this.id;
    }

}
