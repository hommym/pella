package com.pellanotes.pella.features.sharednotes;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pellanotes.pella.common.dtos.SimpleResponse;
import com.pellanotes.pella.database.models.User;
import com.pellanotes.pella.features.sharednotes.dto.RecievedSharedNoteResponse;
import com.pellanotes.pella.features.sharednotes.dto.SharedNoteDto;
import com.pellanotes.pella.features.sharednotes.dto.SharedNoteResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;





@RestController
@RequestMapping("/api/v1/share-note")
public class SharedNoteController {


    private final SharedNoteService sharedNoteService;


    public SharedNoteController(SharedNoteService sharedNoteService){

        this.sharedNoteService=sharedNoteService;
    }

    

    @PostMapping("/share")
    public ResponseEntity<SharedNoteResponse> shareNote(@RequestBody @Valid SharedNoteDto shareNoteDto,HttpServletRequest req) {
        return ResponseEntity.ok(this.sharedNoteService.shareNote(shareNoteDto,(User) req.getAttribute("user"),false));
    }


     @PatchMapping("/access-type")
    public ResponseEntity<SharedNoteResponse> updateAccessType(@RequestBody @Valid SharedNoteDto shareNoteDto,HttpServletRequest req) {
        return ResponseEntity.ok(this.sharedNoteService.shareNote(shareNoteDto,(User) req.getAttribute("user"),true));
    }


     @DeleteMapping("/unshare")
    public ResponseEntity<SimpleResponse> unShareNote(@RequestBody @Valid SharedNoteDto shareNoteDto,HttpServletRequest req) {
        return ResponseEntity.ok(this.sharedNoteService.unshareNote(shareNoteDto,(User) req.getAttribute("user")));
    }
    

    @GetMapping("/owner")
    public  ResponseEntity<List<SharedNoteResponse>> getSharedNotes(HttpServletRequest req) {
        return ResponseEntity.ok(this.sharedNoteService.getSharedNote((User) req.getAttribute("user")));    
    }

    @GetMapping("/recipient")
    public  ResponseEntity<List<RecievedSharedNoteResponse>> getRecievedSharedNotes(HttpServletRequest req) {
        return ResponseEntity.ok(this.sharedNoteService.getRecievedSharedNote((User) req.getAttribute("user")));    
    }

    



}
