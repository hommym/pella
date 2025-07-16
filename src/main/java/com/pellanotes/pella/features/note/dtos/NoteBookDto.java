package com.pellanotes.pella.features.note.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NoteBookDto {
 

    @NotBlank(message="No value found for title")
    @Size(min=5,message="title must have  min length of 5")
    public  String title;
    

}
