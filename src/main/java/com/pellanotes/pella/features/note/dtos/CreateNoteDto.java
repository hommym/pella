package com.pellanotes.pella.features.note.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateNoteDto {
 
    @NotNull(message="No value passed for noteBookId")
    public Long noteBookId;

    @NotBlank(message="No value passed for title")
    @Size(min=5,message="newTitle must have  min length of 5")
    public String title;


    @NotBlank(message="No value passed for content")
    public String content;

}
