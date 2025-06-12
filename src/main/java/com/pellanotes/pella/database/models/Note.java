package com.pellanotes.pella.database.models;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String title;

    String content; 
    
    @Column(name="note_book_id",insertable=false,updatable=false)
    private Long noteBookId;

    @OneToOne
    @JoinColumn(name="note_book_id")
    NoteBook noteBook;

    @Column(name="created_at")
    @CreationTimestamp
    private LocalDate createdAt;


    public Note(){ // for JPA

    }

    public Note(String title,String content,NoteBook noteBook){ 

        this.title=title;
        this.content=content;
        this.noteBook=noteBook;
    }



    Long getId(){
        return this.id;
    }


}
