package com.pellanotes.pella.features.sharednotes.dto;

import com.pellanotes.pella.database.enums.NoteAccessType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SharedNoteDto {
 
    @NotBlank(message="No value passed for recipientEmail")
    @Email(message="Value for recipentEmail must be a valid email")
    public String recipientEmail;


  
    @NotNull(message="No value passed for accessType")
    public NoteAccessType accessType;

    @NotNull(message="No value passed for noteId")
    public Long noteId;

}
