package com.pellanotes.pella.features.note.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.pellanotes.pella.database.models.Note;
import com.pellanotes.pella.database.models.NoteBook;

public class NoteBookResponse {
    

    public Long noteBookId;

    public LocalDate  createdAt;

    public String title;

    public List<NoteResponse> notes=new ArrayList<>();


    public NoteBookResponse(NoteBook book){
        
        this.noteBookId=book.getId();
        this.createdAt=book.getCreationDate();
        this.title=book.getTitle();

        for(Note item:book.getNotes()){
            this.notes.add(new NoteResponse(item));
        }
    }



}
