package com.pellanotes.pella.features.note.dtos;

import jakarta.validation.constraints.NotNull;

public class DeleteNoteDto {
 
    @NotNull(message="No value passed for noteId")
    public Long noteId;

    

}
