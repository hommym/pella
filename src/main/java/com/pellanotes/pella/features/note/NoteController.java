package com.pellanotes.pella.features.note;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {
 

    private final NoteService noteService;
    

    public NoteController(NoteService noteService){
        this.noteService=noteService;
    }


    //crud for notebooks
    
    //crud for note

}
