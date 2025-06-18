package com.pellanotes.pella.database.models;

import java.util.List;

import com.pellanotes.pella.database.embedables.SharedNoteId;
import com.pellanotes.pella.database.enums.NoteAccessType;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;


@Entity
@Table(name="shared_note")
public class SharedNote {
    

   @EmbeddedId 
   private SharedNoteId id;


    @Column(name="access_type")
    @Enumerated(EnumType.STRING)    
    private NoteAccessType accessType;


    @ElementCollection
    private List<Long> allowedNotes;  // ids of notes recipient is allowed to access

    @ManyToOne
    @MapsId("noteBookId")
    @JoinColumn(name="note_book_id")
    NoteBook noteBook;


    @ManyToOne
    @MapsId("recipientId")
    @JoinColumn(name="recipient_id")
    User recipient;

    public SharedNote(){// for jpa

    }

    public SharedNote(SharedNoteId sharedNoteId,NoteAccessType accessType,List<Long> allowedNotesId,
    NoteBook noteBook, User recipient){
        this.id=sharedNoteId;
        this.accessType=accessType;
        this.allowedNotes=allowedNotesId;
        this.noteBook=noteBook;
        this.recipient=recipient;
    }


    SharedNoteId getId(){
        return this.id;
    }

   

}
