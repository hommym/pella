package com.pellanotes.pella.database.models;


import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="note_book")
public class NoteBook {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;


    String title;

    @Column(name="owner_id",insertable=false,updatable=false)
    private Long ownerId;


    private boolean isDefault=false;

    @CreationTimestamp
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;

    //For JPA
    public NoteBook() {
    }

    public NoteBook(String title,User owner) {
        this.title=title;
        this.owner=owner;
    }


    Long getId(){
        return this.id;
    }
    
    Long getOwnerId(){
        return this.ownerId;
    }
    

}
