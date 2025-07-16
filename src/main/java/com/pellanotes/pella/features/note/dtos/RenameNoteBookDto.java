package com.pellanotes.pella.features.note.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RenameNoteBookDto {
    

    @NotBlank(message="No value found for oldTitle")
    @Size(min=5,message="oldTitle must have  min length of 5")
    public  String oldTitle;

    @NotBlank(message="No value found for newTitle")
    @Size(min=5,message="newTitle must have  min length of 5")
    public  String newTitle;

}
