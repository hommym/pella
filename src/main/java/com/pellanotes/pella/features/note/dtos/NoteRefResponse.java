package com.pellanotes.pella.features.note.dtos;

import com.pellanotes.pella.database.enums.FileType;
import com.pellanotes.pella.database.models.NoteReference;

public class NoteRefResponse {
    

    public Long refId;
    public Long noteId;

    public String refMaterial;

    public  FileType fileType;

    public String reference;



    public  NoteRefResponse(NoteReference ref){
        this.refId=ref.getId();
        this.noteId=ref.getNoteId();
        this.refMaterial=ref.link;
        this.fileType=ref.fileType;
        this.reference=ref.reference;
    }






}
