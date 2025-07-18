package com.pellanotes.pella.features.note;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pellanotes.pella.database.models.User;
import com.pellanotes.pella.features.note.dtos.CreateNoteDto;
import com.pellanotes.pella.features.note.dtos.NoteBookDto;
import com.pellanotes.pella.features.note.dtos.NoteBookResponse;
import com.pellanotes.pella.features.note.dtos.NoteResponse;
import com.pellanotes.pella.features.note.dtos.RenameNoteBookDto;
import com.pellanotes.pella.features.note.dtos.RenameNoteDto;
import com.pellanotes.pella.features.note.dtos.UpdateNoteDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {
 

    private final NoteService noteService;
    

    public NoteController(NoteService noteService){
        this.noteService=noteService;
    }


    

    @PostMapping("/book")
    public ResponseEntity<NoteBookResponse> createNoteBook(@RequestBody @Valid NoteBookDto createDto,HttpServletRequest req) {
        return ResponseEntity.ok(this.noteService.createNoteBook(createDto, (User) req.getAttribute("user")));
    }
    
    @PatchMapping("/book/rename")
    public ResponseEntity<NoteBookResponse> renameNoteBook(@RequestBody @Valid RenameNoteBookDto createDto,HttpServletRequest req) {
        return ResponseEntity.ok(this.noteService.renameNoteBook(createDto, (User) req.getAttribute("user")));
    }
    
    @GetMapping("/books")
    public  ResponseEntity<List<NoteBookResponse>> getAllNoteBooks(HttpServletRequest req) {
        return ResponseEntity.ok(this.noteService.getAllNoteBooks((User) req.getAttribute("user")));
    }
    

    @PostMapping
    public ResponseEntity<NoteResponse> createNote(@RequestBody @Valid CreateNoteDto createNoteDto) {
        return ResponseEntity.ok(this.noteService.createNote(createNoteDto));
    }



    @PatchMapping
    public ResponseEntity<NoteResponse> updateNoteContent(@RequestBody @Valid UpdateNoteDto updateNoteDto){
        return ResponseEntity.ok(this.noteService.updateNote(updateNoteDto));
    }

    @PatchMapping("/rename")
    public ResponseEntity<NoteResponse> renameNote(@RequestBody @Valid RenameNoteDto renameNoteDto){
        return ResponseEntity.ok(this.noteService.renameNote(renameNoteDto));
    }

    // add endpoints for adding and managing note references 
    

  

}
