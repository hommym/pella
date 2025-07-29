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
@Table(name = "goal_complete")
public class GoalComplete {
 
    
     @Id
    @Column(name = "goal_id",insertable=false,updatable=false,nullable = false)    
    private Long goalId;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;


    @Column(name="completion_date",nullable = false)
    @CreationTimestamp
    private LocalDate completionDate;


    public GoalComplete(){//jpa

    }

    public GoalComplete(Goal goal){
        this.goal=goal;

    }


    Long getId(){
        return this.goalId;
    }


}
