package com.pellanotes.pella.features.note.dtos;

import java.time.LocalDate;


import com.pellanotes.pella.database.models.NoteBook;

public class NoteBookResponse {
    

    public Long noteBookId;

    public LocalDate  createdAt;

    public String title;


    public NoteBookResponse(NoteBook book){
        
        this.noteBookId=book.getId();
        this.createdAt=book.getCreationDate();
        this.title=book.getTitle();

    }



}
