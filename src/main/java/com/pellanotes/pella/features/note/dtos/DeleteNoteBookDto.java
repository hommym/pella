package com.pellanotes.pella.features.note.dtos;

import jakarta.validation.constraints.NotNull;

public class DeleteNoteBookDto {

    
    @NotNull(message="No value passed for noteBookId")
    public Long noteBookId;


}


