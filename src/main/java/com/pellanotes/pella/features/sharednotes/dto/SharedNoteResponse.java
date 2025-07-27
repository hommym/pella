package com.pellanotes.pella.features.sharednotes.dto;

import com.pellanotes.pella.database.enums.NoteAccessType;
import com.pellanotes.pella.database.models.SharedNote;

public class SharedNoteResponse {
    

    public String recipeintEmail;

    public String recipeintUsername;

    public NoteAccessType accessType;

    public Long  noteId;


    public SharedNoteResponse(SharedNote sharedNote){
        this.recipeintEmail=sharedNote.getRecipeint().getEmail();
        this.recipeintUsername=sharedNote.getRecipeint().getUsername();
        this.noteId=sharedNote.getNote().getId();
        this.accessType=sharedNote.getAccessType();
    }

}
