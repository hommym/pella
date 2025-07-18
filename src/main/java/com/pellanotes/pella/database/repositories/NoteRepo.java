package com.pellanotes.pella.database.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pellanotes.pella.database.models.Note;


public interface NoteRepo extends JpaRepository<Note, Long> {
    

    @Query(value = "SELECT * FROM note WHERE note_book_id=:noteBookId AND title=:noteTitle",nativeQuery =true)    
    Optional<Note> getNote(@Param("noteBookId") Long noteBookId,@Param("noteTitle") String noteTitle); 


    @Modifying
    @Query(value = "UPDATE note_book SET title=:newTitle WHERE id=:noteId",nativeQuery =true)    
    void renameNote(@Param("noteId") Long noteId,@Param("newTitle") String newTitle); 


    @Modifying
    @Query(value = "UPDATE note SET content=:updatedContent WHERE id=:noteId",nativeQuery =true)    
    void updateNoteContent(@Param("noteId") Long noteId,@Param("updatedContent") String updatedContent); 

}
