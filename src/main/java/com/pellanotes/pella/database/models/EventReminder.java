package com.pellanotes.pella.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "event_reminder")
public class EventReminder {
    
@Id
@Column(name = "event_id",insertable=false,updatable=false)    
private Long eventId;

@OneToOne
@JoinColumn(name = "event_id")
Event event;


@Column(nullable = false)
Boolean rep=false;


public EventReminder(){//jpa
}

public EventReminder(Event event){//jpa
    this.event=event;
   
}

public EventReminder(Event event,Boolean repeat){//jpa
    this.event=event;
    this.rep=repeat;
}

Long getId(){
    return this.eventId;
}

}
