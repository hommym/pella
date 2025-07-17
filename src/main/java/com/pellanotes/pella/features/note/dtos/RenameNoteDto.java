package com.pellanotes.pella.features.note.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RenameNoteDto {
    

    @NotBlank(message="No value found for newTitle")
    @Size(min=5,message="newTitle must have  min length of 5")
    public  String newTitle;

    @NotNull(message="No value passed for noteId")
    public Long noteId;


}
