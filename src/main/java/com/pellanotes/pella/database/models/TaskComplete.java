package com.pellanotes.pella.database.models;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "task_complete")
public class TaskComplete {
    
    @Id
    @Column(name = "task_id",insertable=false,updatable=false,nullable = false)    
    private Long taskId;

    @OneToOne
    @JoinColumn(name = "task_id")
    private Task task;


    @Column(name="completion_date",nullable = false)
    @CreationTimestamp
    private LocalDate completionDate;



    public TaskComplete(){ // jpa

    }



    public TaskComplete(Task task){ 
        this.task=task;
    }
    


    Long getId(){
        return this.taskId;
    }
}
