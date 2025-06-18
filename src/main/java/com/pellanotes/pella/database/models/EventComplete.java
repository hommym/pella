package com.pellanotes.pella.database.models;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "event_complete")
public class EventComplete {
    @Id
    @Column(name = "event_id",insertable=false,updatable=false)    
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;


    @Column(name="completion_date")
    @CreationTimestamp
    private LocalDate completionDate;

    public EventComplete(Event event){ //jpa
        this.event=event;
    }


    Long getId(){
        return this.eventId;
    }


}
