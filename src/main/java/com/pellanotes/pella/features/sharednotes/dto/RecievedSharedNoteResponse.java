package com.pellanotes.pella.features.sharednotes.dto;

import com.pellanotes.pella.database.enums.NoteAccessType;
import com.pellanotes.pella.database.models.SharedNote;
import com.pellanotes.pella.features.note.dtos.NoteResponse;

public class RecievedSharedNoteResponse {
    

    public String ownerEmail;

    public String ownerUsername;

    public NoteAccessType accessType;

    public NoteResponse note;



    public RecievedSharedNoteResponse(SharedNote shared){
        this.ownerEmail=shared.getOwner().getEmail();
        this.ownerUsername=shared.getOwner().getUsername();
        this.note= new NoteResponse(shared.getNote());
         this.accessType=shared.getAccessType();
    }
}
