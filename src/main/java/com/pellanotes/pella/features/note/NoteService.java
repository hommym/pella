package com.pellanotes.pella.features.note;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pellanotes.pella.common.dtos.SimpleResponse;
import com.pellanotes.pella.common.exceptions.ResourceConflict;
import com.pellanotes.pella.common.exceptions.ResourceNotFound;
import com.pellanotes.pella.database.models.Note;
import com.pellanotes.pella.database.models.NoteBook;
import com.pellanotes.pella.database.models.User;
import com.pellanotes.pella.database.repositories.NoteBookRepo;
import com.pellanotes.pella.database.repositories.NoteRepo;
import com.pellanotes.pella.features.note.dtos.CreateNoteDto;
import com.pellanotes.pella.features.note.dtos.NoteBookDto;
import com.pellanotes.pella.features.note.dtos.NoteBookResponse;
import com.pellanotes.pella.features.note.dtos.NoteResponse;
import com.pellanotes.pella.features.note.dtos.RenameNoteBookDto;
import com.pellanotes.pella.features.note.dtos.RenameNoteDto;
import com.pellanotes.pella.features.note.dtos.UpdateNoteDto;

import jakarta.transaction.Transactional;


@Service
public class NoteService {
    
  
    private final NoteBookRepo noteBookRepo;
    private final NoteRepo noteRepo;


    public NoteService(NoteBookRepo noteBookRepo, NoteRepo noteRepo){
        this.noteBookRepo=noteBookRepo;
        this.noteRepo=noteRepo;
    }


    @Transactional
    private NoteBook checkNoteBook(Long userId,String title, boolean throwErrIfPresent){
        Optional<NoteBook> book= this.noteBookRepo.getNoteBook(userId, title);
        if(book.isPresent() && throwErrIfPresent)throw new ResourceConflict("NoteBook with this tite:"+title+" exist for this account");
        else if(book.isEmpty()) throw new ResourceNotFound("No NoteBook with this title exist");

        return book.get();
    }


    @Transactional
    private Object[] checkNoteInNoteBook(Long noteBookId,String noteTitle,boolean throwErrIfPresent){

        Optional<NoteBook> book=this.noteBookRepo.findById(noteBookId);
        if(book.isEmpty())throw new ResourceNotFound("No NoteBook with this id exist");
        Optional<Note> note= this.noteRepo.getNote(noteBookId, noteTitle);

        if(note.isPresent() && throwErrIfPresent)throw new ResourceConflict("Note with this tite:"+noteTitle+" exist for this notebook");
        else if(note.isEmpty()) throw new ResourceNotFound("No Note with this title exist");

        return new Object[]{book.get(),note.get()};
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
    public List<NoteBookResponse> getAllNoteBooks(User user){

        List<NoteBook> books= this.noteBookRepo.getAllNoteBooks(user.getId());
        List<NoteBookResponse> bookResponse= new ArrayList<>();
        
        for(NoteBook item :books){
            bookResponse.add(new NoteBookResponse(item));
        }
        
        return bookResponse;
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


    @Transactional
    public NoteResponse createNote(CreateNoteDto dto){
        Object [] noteAndNoteBook= this.checkNoteInNoteBook(dto.noteBookId, dto.title, true);

        Note note= new Note(dto.title,dto.content,(NoteBook) noteAndNoteBook[0]);
        this.noteRepo.save(note);

        return new NoteResponse(note);
    }

    @Transactional
    public NoteResponse renameNote(RenameNoteDto dto){
        Optional<Note> note= this.noteRepo.findById(dto.noteId);

        if(note.isEmpty())throw new ResourceNotFound("No Note with this id exist");

        this.noteRepo.renameNote(dto.noteId, dto.newTitle);

        (note.get()).setTitle(dto.newTitle);
        
        return new NoteResponse(note.get());
    }



    @Transactional
    public NoteResponse updateNote(UpdateNoteDto dto){
        Optional<Note> note= this.noteRepo.findById(dto.noteId);

        if(note.isEmpty())throw new ResourceNotFound("No Note with this id exist");

        this.noteRepo.updateNoteContent(dto.noteId, dto.updatedContent);

        (note.get()).setContent(dto.updatedContent);
        
        return new NoteResponse(note.get());
    }


}
