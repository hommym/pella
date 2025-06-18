package com.pellanotes.pella.database.models;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.pellanotes.pella.database.enums.EventType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    String title;

    String description;

    @Enumerated(EnumType.STRING) 
    private EventType type;

    @Column(name="user_id",insertable=false,updatable=false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;


    @Column(name="created_at")
    @CreationTimestamp
    private LocalDate createdAt;


    public Event(){// jpa

    }

    public Event(String title,String description,EventType type,User user){
        this.title = title;
        this.description = description;
        this.type = type;
        this.user = user;
    }


    Long getId(){
        return this.id;
    }

}
