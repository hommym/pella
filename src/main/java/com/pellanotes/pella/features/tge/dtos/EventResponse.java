package com.pellanotes.pella.features.tge.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.pellanotes.pella.database.models.Event;

public class EventResponse {

    public String title;

    public  LocalDate eventDate;


    public  LocalTime eventTime;

    public String description;

    public Boolean repeat;

    public EventResponse(Event event){
        this.title=event.title;
        this.description=event.description;
        this.eventDate=event.eventDate;
        this.eventTime=event.eventTime;
        this.repeat=event.repe;
    }

}
