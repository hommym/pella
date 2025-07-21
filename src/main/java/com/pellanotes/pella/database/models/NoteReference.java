package com.pellanotes.pella.database.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.pellanotes.pella.database.enums.FileType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="note_reference")
public class NoteReference {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="note_id",insertable=false,updatable=false)
    private Long noteId;


    @ManyToOne
    @JoinColumn(name="note_id")
    @OnDelete(action=OnDeleteAction.CASCADE)
    private Note note;


    public String link;


    @Column(name="file_type")
    @Enumerated(EnumType.STRING) 
    public   FileType fileType;


    public  String reference;  // part of the note been referenced


    public NoteReference(){// for JPA

    }

    public NoteReference(Note note,String link,FileType fileType,String reference){
        this.note=note;
        this.noteId=note.getId();
        this.link=link;
        this.fileType=fileType;
        this.reference=reference;
    }


    public Long getId(){
      return this.id;
    }

    public Long getNoteId(){
      return this.noteId;
    }

}
