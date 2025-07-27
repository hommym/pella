package com.pellanotes.pella.database.models;

import com.pellanotes.pella.database.embedables.SharedNoteId;
import com.pellanotes.pella.database.enums.NoteAccessType;

import jakarta.persistence.Column;
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

    @Column(name="owner_id",nullable=false,insertable=false,updatable=false)
    private Long ownerId;

    @Column(name="access_type")
    @Enumerated(EnumType.STRING)    
    private NoteAccessType accessType;

    @ManyToOne
    @MapsId("noteId")
    @JoinColumn(name="note_id")
    Note note;


    @ManyToOne
    @MapsId("recipientId")
    @JoinColumn(name="recipient_id")
    User recipient;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    User owner;

    public SharedNote(){// for jpa

    }

    public SharedNote(SharedNoteId sharedNoteId,NoteAccessType accessType,
    Note note, User recipient,User owner){
        this.id=sharedNoteId;
        this.accessType=accessType;
        this.note=note;
        this.recipient=recipient;
        this.owner=owner;
        this.ownerId=owner.getId();
    }


    public  SharedNoteId getId(){
        return this.id;
    }

    public User getRecipeint(){
        return this.recipient;
    }

    public User getOwner(){
        return this.owner;
    }
    public Note getNote(){
        return this.note;
    }

    public NoteAccessType getAccessType(){
        return this.accessType;
    }
   

}
