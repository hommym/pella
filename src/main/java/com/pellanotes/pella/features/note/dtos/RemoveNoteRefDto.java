package com.pellanotes.pella.features.note.dtos;

import jakarta.validation.constraints.NotNull;

public class RemoveNoteRefDto {
    

    @NotNull(message="No value passed for refId")
    public Long refId;

}
