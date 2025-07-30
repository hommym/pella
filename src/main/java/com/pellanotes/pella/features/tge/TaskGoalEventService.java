package com.pellanotes.pella.features.tge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pellanotes.pella.common.dtos.SimpleResponse;
import com.pellanotes.pella.common.exceptions.ResourceNotFound;
import com.pellanotes.pella.database.models.Event;
import com.pellanotes.pella.database.models.User;
import com.pellanotes.pella.database.repositories.EventRepo;
import com.pellanotes.pella.features.tge.dtos.EventDto;
import com.pellanotes.pella.features.tge.dtos.EventResponse;
import com.pellanotes.pella.features.tge.dtos.UpdateEventDto;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class TaskGoalEventService {
    




    private final EventRepo eventRepo;
    private final EntityManager entityManager;



 public TaskGoalEventService(EventRepo eventRepo,EntityManager entityManager){
    this.eventRepo=eventRepo;
    this.entityManager=entityManager;


 }


 @Transactional
 private Event isEventValid(Long eventId){
    Optional<Event> event = this.eventRepo.findById(eventId);
    if(event.isEmpty())throw new ResourceNotFound("No event with this id exist");

    return event.get();
 }


@Transactional
public EventResponse addEvent(User user,EventDto dto){
    Event event= new Event(dto.title,dto.description,user,dto.eventDate,dto.eventTime,dto.repeat);
    event= this.eventRepo.save(event);
    return new EventResponse(event);
}    

@Transactional
public EventResponse updateEvent(UpdateEventDto dto){
    Event event = this.isEventValid(dto.eventId);
    this.eventRepo.updateEvent(dto.eventId, dto.title, dto.description, dto.eventDate, dto.eventTime,dto.repeat);
    this.entityManager.refresh(event);
    return new EventResponse(event);
}


@Transactional
public SimpleResponse removeEvent(Long eventId){
    this.isEventValid(eventId);
    this.eventRepo.deleteById(eventId);
    return new SimpleResponse("Event Successfully Removed");
}



@Transactional
public List<EventResponse> getAllEventsForUser(User user){
    List<Event> events=this.eventRepo.getAllEventsOfUser(user.getId());
    List<EventResponse> response=new ArrayList<>();
    for(Event event:events)response.add(new EventResponse(event));
    return response;
}



}
