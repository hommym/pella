package com.pellanotes.pella.features.tge;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pellanotes.pella.common.dtos.SimpleResponse;
import com.pellanotes.pella.database.models.User;
import com.pellanotes.pella.features.tge.dtos.EventDto;
import com.pellanotes.pella.features.tge.dtos.EventResponse;
import com.pellanotes.pella.features.tge.dtos.UpdateEventDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/tge")
public class TaskGoalEventController {
    


    private final TaskGoalEventService tgeService;


    public TaskGoalEventController( TaskGoalEventService tgeService){

        this.tgeService=tgeService;
    }

    //crud for events event reminders
    //crude for task, task reminders and complete,
    //crud for goals and completion


    @PostMapping("/events")
    public ResponseEntity<EventResponse> addEvent(@RequestBody @Valid EventDto eventDto,HttpServletRequest req) {
        return ResponseEntity.ok(this.tgeService.addEvent((User) req.getAttribute("user"), eventDto));
    }
    
    @PatchMapping("/events")
    public ResponseEntity<EventResponse> updateEvent(@RequestBody @Valid UpdateEventDto eventDto,HttpServletRequest req) {
        return ResponseEntity.ok(this.tgeService.updateEvent(eventDto));
    }



    @GetMapping("/events")
    public ResponseEntity<List<EventResponse>> getEvents(HttpServletRequest req) {
        return ResponseEntity.ok(this.tgeService.getAllEventsForUser((User) req.getAttribute("user")));
    }


     @DeleteMapping("/events/{eventId}")
    public ResponseEntity<SimpleResponse> removeEvent(@PathVariable(name = "eventId")Long eventId) {
        return ResponseEntity.ok(this.tgeService.removeEvent(eventId));
    }

}
