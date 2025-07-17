package com.pellanotes.pella.database.models;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="note",uniqueConstraints={@UniqueConstraint(columnNames={"note_book_id","title"})})
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content; 
    
    @Column(name="note_book_id",insertable=false,updatable=false)
    private Long noteBookId;

    @ManyToOne
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



    public   Long getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getContent(){
        return this.content;
    }

    public Long getNoteBookId(){
        return this.noteBookId;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setContent(String updatedContent){
        this.content=updatedContent;
    }

}
