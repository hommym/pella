package com.pellanotes.pella.features.note.dtos;

import java.util.ArrayList;
import java.util.List;

import com.pellanotes.pella.database.models.Note;
import com.pellanotes.pella.database.models.NoteReference;

public class NoteResponse {
    

    public Long noteId;
    public String title;

    public String content; 

    public Long noteBookId;

    public List<NoteRefResponse> noteReferences=new ArrayList<>();


    public NoteResponse(Note note){

        this.noteId=note.getId();
        this.title=note.getTitle();
        this.content=note.getContent();
        this.noteBookId=note.getNoteBookId();

        for(NoteReference ref:note.getReferences()){
            this.noteReferences.add(new NoteRefResponse(ref));
        }
    }


}
