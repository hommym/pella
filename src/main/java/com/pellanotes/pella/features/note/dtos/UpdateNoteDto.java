package com.pellanotes.pella.features.note.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class UpdateNoteDto {


      @NotBlank(message="No value found for newTitle")
      public  String updatedContent;

      @NotNull(message="No value passed for noteId")
      public Long noteId;
}
