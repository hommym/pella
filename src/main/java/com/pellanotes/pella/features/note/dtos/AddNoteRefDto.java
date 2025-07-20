package com.pellanotes.pella.features.note.dtos;

import com.pellanotes.pella.database.enums.FileType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AddNoteRefDto {
    


    @NotNull(message="No value passed for noteId")
    public Long noteId;


    @NotBlank(message="No value passed for reference")
    public String reference;  // part of the note been referenced 

    
    @NotBlank(message="No value passed for refeMaterial")
    @Pattern(
        regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$",
        message = "refMaterial must be a valid URL"
    )
    public String refMaterial;

    @NotNull(message = "No value passed for fileType")
    public FileType fileType;   // add custom enum validator <Not done>


}
