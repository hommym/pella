package com.pellanotes.pella.database.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pellanotes.pella.database.models.Event;

public interface EventRepo extends JpaRepository<Event, Long>{
 


@Query(value = "SELECT * FROM event WHERE user_id=:ownerId",nativeQuery =true)    
List<Event> getAllEventsOfUser(@Param("ownerId") Long ownerId); 


@Modifying
@Query(value = "UPDATE event SET title=:title,description=:description,event_date=:eventDate,event_time=:eventTime,repe=:rep WHERE id=:eventId",nativeQuery =true)    
void updateEvent(@Param("eventId") Long eventId,@Param("title") String title,@Param("description") String description,@Param("eventDate") LocalDate eventDate,@Param("eventTime")LocalTime eventTime,@Param("rep")Boolean repeat);    


    
}
