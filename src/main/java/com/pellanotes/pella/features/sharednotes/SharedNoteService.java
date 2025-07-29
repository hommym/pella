package com.pellanotes.pella.features.sharednotes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pellanotes.pella.common.dtos.SimpleResponse;
import com.pellanotes.pella.common.exceptions.ResourceConflict;
import com.pellanotes.pella.common.exceptions.ResourceNotFound;
import com.pellanotes.pella.common.exceptions.UnauthorizeRequest;
import com.pellanotes.pella.database.embedables.SharedNoteId;
import com.pellanotes.pella.database.models.Note;
import com.pellanotes.pella.database.models.SharedNote;
import com.pellanotes.pella.database.models.User;
import com.pellanotes.pella.database.repositories.SharedNoteRepo;
import com.pellanotes.pella.features.auth.AuthService;
import com.pellanotes.pella.features.note.NoteService;
import com.pellanotes.pella.features.sharednotes.dto.RecievedSharedNoteResponse;
import com.pellanotes.pella.features.sharednotes.dto.SharedNoteDto;
import com.pellanotes.pella.features.sharednotes.dto.SharedNoteResponse;

import jakarta.transaction.Transactional;

@Service
public class SharedNoteService {
    

private final AuthService authService;
private final NoteService noteService;
private final SharedNoteRepo sharedNoteRepo;


public SharedNoteService(AuthService authService,NoteService noteService,SharedNoteRepo sharedNoteRepo){
this.authService=authService;
this.noteService=noteService;
this.sharedNoteRepo=sharedNoteRepo;
} 


@Transactional
private void hasNoteBeenShared(SharedNoteId sharedId,boolean throwIfExist){
    Optional<SharedNote> shared= this.sharedNoteRepo.findById(sharedId);
    if(throwIfExist && shared.isPresent()) throw new ResourceConflict("Note has already been shared with this user");
    else if(!throwIfExist && shared.isEmpty())throw new ResourceNotFound("This note has not been shared with this user");

}


@Transactional
public SharedNoteResponse shareNote(SharedNoteDto dto, User owner, boolean update){
    User recipient= this.authService.isAccountValid(dto.recipientEmail);
    Note note = this.noteService.isNoteIdValid(dto.noteId);
    if(!note.getBook().getOwnerId().equals(owner.getId()))throw new UnauthorizeRequest("Cannot share notes you do not own");
    else if(owner.getEmail().equals(dto.recipientEmail))throw new ResourceConflict("Cannot share notes with yourself");

    SharedNoteId sharedId= new SharedNoteId(recipient.getId(),dto.noteId);
    this.hasNoteBeenShared(sharedId,!update);
    SharedNote shared= new SharedNote(sharedId,dto.accessType,note,recipient,owner);
    if(update){
        this.sharedNoteRepo.updateAccessType(recipient.getId(), note.getId(), dto.accessType.toString());
    }
    else shared=this.sharedNoteRepo.save(shared);
    return new SharedNoteResponse(shared);
}


@Transactional
public SimpleResponse unshareNote(SharedNoteDto dto, User owner){
    User recipient= this.authService.isAccountValid(dto.recipientEmail);
    Note note = this.noteService.isNoteIdValid(dto.noteId);
    if(!note.getBook().getOwnerId().equals(owner.getId()))throw new UnauthorizeRequest("Cannot unshare notes you do not own");
    else if(owner.getEmail().equals(dto.recipientEmail))throw new ResourceConflict("Cannot unshare notes with yourself");

    SharedNoteId sharedId= new SharedNoteId(recipient.getId(),dto.noteId);
    this.hasNoteBeenShared(sharedId, false);
    this.sharedNoteRepo.deleteById(sharedId);

    return new SimpleResponse("Note Successfully Unshared");
}


// @Transactional
// public SharedNoteResponse updateAccessType(SharedNoteDto dto, User owner){
//  User recipient= this.authService.isAccountValid(dto.recipientEmail);
//  Note note = this.noteService.isNoteIdValid(dto.noteId);

// if(!note.getBook().getOwnerId().equals(owner.getId()))throw new UnauthorizeRequest("Cannot share notes you do not own");
// else if(owner.getEmail().equals(dto.recipientEmail))throw new ResourceConflict("Cannot share notes with yourself");

// }



@Transactional
public List<SharedNoteResponse> getSharedNote(User owner){
    List<SharedNote> sharedNotes= this.sharedNoteRepo.getSharedNotes(owner.getId());
    List<SharedNoteResponse> response=new ArrayList<>();
    for(SharedNote item:sharedNotes){
        response.add(new SharedNoteResponse(item));
    }
    return response;
}

@Transactional
public List<RecievedSharedNoteResponse>  getRecievedSharedNote(User recipeint){
    List<SharedNote> sharedNotes= this.sharedNoteRepo.getRecievedSharedNotes(recipeint.getId());
    List<RecievedSharedNoteResponse> response=new ArrayList<>();
     for(SharedNote item:sharedNotes){
        response.add(new RecievedSharedNoteResponse(item));
    }
    return response;
}


}
