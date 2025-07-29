package com.pellanotes.pella.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "task_reminder")
public class TaskReminder {
    @Id
    @Column(name = "task_id",insertable=false,updatable=false,nullable = false)    
    private Long taskId;

    @OneToOne
    @JoinColumn(name = "task_id")
    private Task task;


    @Column(nullable = false)
    private String repe;


    public TaskReminder(){// jpa

    }

     public TaskReminder(Task task,String repeat){
        this.task=task;
        this.repe=repeat;
    }


    Long getId(){
        return this.taskId;
    }
}
