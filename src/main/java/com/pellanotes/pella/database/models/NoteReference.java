package com.pellanotes.pella.database.models;

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
    Long noteId;


    @ManyToOne
    @JoinColumn(name="note_id")
    Note note;


    private String link;


    @Column(name="file_type")
    @Enumerated(EnumType.STRING) 
    FileType fileType;


    public NoteReference(){// for JPA

    }

    public NoteReference(Note note,String link,FileType fileType){// for JPA
        this.note=note;
        this.link=link;
        this.fileType=fileType;

    }


    Long getId(){
      return this.id;
  }

}
