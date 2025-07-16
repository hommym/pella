package com.pellanotes.pella.features.note;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pellanotes.pella.common.dtos.SimpleResponse;
import com.pellanotes.pella.common.exceptions.ResourceConflict;
import com.pellanotes.pella.common.exceptions.ResourceNotFound;
import com.pellanotes.pella.database.models.NoteBook;
import com.pellanotes.pella.database.models.User;
import com.pellanotes.pella.database.repositories.NoteBookRepo;
import com.pellanotes.pella.database.repositories.UserRepo;
import com.pellanotes.pella.features.note.dtos.NoteBookDto;
import com.pellanotes.pella.features.note.dtos.NoteBookResponse;
import com.pellanotes.pella.features.note.dtos.RenameNoteBookDto;

import jakarta.transaction.Transactional;


@Service
public class NoteService {
    
    private final UserRepo userRepo;
    private final NoteBookRepo noteBookRepo;


    public NoteService(UserRepo userRepo,NoteBookRepo noteBookRepo){
        this.userRepo=userRepo;
        this.noteBookRepo=noteBookRepo;
    }


    @Transactional
    private NoteBook checkNoteBook(Long userId,String title, boolean throwErrIfPresent){
        Optional<NoteBook> book= this.noteBookRepo.getNoteBook(userId, title);
        if(book.isPresent() && throwErrIfPresent)throw new ResourceConflict("NoteBook with this tite:"+title+" exist for this account");
        else if(book.isEmpty()) throw new ResourceNotFound("No NoteBook with this title exist");

        return book.get();
    }
    
    @Transactional
    public NoteBookResponse createNoteBook(NoteBookDto dto,User user){
        this.checkNoteBook(user.getId(), dto.title,true);
        NoteBook book= new NoteBook(dto.title, user);
        this.noteBookRepo.save(book);
        return new NoteBookResponse(book);
    }


    @Transactional
    public NoteBookResponse renameNoteBook(RenameNoteBookDto dto,User user){
        NoteBook book= this.checkNoteBook(user.getId(), dto.oldTitle,false);
        this.checkNoteBook(user.getId(), dto.newTitle, true);
        this.noteBookRepo.renameNoteBook(user.getId(),dto.oldTitle,dto.newTitle);
        book.updateTitle(dto.newTitle);
        return new NoteBookResponse(book);
    }


    @Transactional
    public SimpleResponse deleteNoteBook (NoteBookDto dto,User user){
        // Not Done With implementation
        NoteBook book= this.checkNoteBook(user.getId(), dto.title,false);

        // remove all notes in the book
        // remove book

        // this.noteBookRepo.removeNoteBook(book.getId());
        return new SimpleResponse("NoteBook with title:"+dto.title+" has been deleted sucessfuly");
    }




}
