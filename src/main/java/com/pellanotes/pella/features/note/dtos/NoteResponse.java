package com.pellanotes.pella.features.note.dtos;

import com.pellanotes.pella.database.models.Note;

public class NoteResponse {
    

    public Long noteId;
    public String title;

    public String content; 

    public Long noteBookId;


    public NoteResponse(Note note){

        this.noteId=note.getId();
        this.title=note.getTitle();
        this.content=note.getContent();
        this.noteBookId=note.getNoteBookId();
    }


}
