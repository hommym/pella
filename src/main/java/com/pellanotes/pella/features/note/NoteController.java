package com.pellanotes.pella.features.note;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pellanotes.pella.database.models.User;
import com.pellanotes.pella.features.note.dtos.NoteBookDto;
import com.pellanotes.pella.features.note.dtos.NoteBookResponse;
import com.pellanotes.pella.features.note.dtos.RenameNoteBookDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {
 

    private final NoteService noteService;
    

    public NoteController(NoteService noteService){
        this.noteService=noteService;
    }


    //crud for notebooks

    @PostMapping("/book")
    public ResponseEntity<NoteBookResponse> createNoteBook(@RequestBody @Valid NoteBookDto createDto,HttpServletRequest req) {
        return ResponseEntity.ok(this.noteService.createNoteBook(createDto, (User) req.getAttribute("user")));
    }
    
    @PatchMapping("/book/rename")
    public ResponseEntity<NoteBookResponse> renameNoteBook(@RequestBody @Valid RenameNoteBookDto createDto,HttpServletRequest req) {
        return ResponseEntity.ok(this.noteService.renameNoteBook(createDto, (User) req.getAttribute("user")));
    }
    


    //crud for note

}
