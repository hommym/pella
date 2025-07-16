package com.pellanotes.pella.database.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pellanotes.pella.database.models.NoteBook;

public interface NoteBookRepo extends JpaRepository<NoteBook, Long>{
    



@Query(value = "SELECT * FROM note_book WHERE owner_id=:userId AND is_default=true",nativeQuery =true)    
Optional<NoteBook> getDefaultNoteBook(@Param("userId") Long userId);   


@Query(value = "SELECT * FROM note_book WHERE owner_id=:userId AND title=:title",nativeQuery =true)    
Optional<NoteBook> getNoteBook(@Param("userId") Long userId,@Param("title") String title);   

@Modifying
@Query(value = "UPDATE note_book SET title=:newTitle WHERE owner_id=:userId AND title=:oldTitle",nativeQuery =true)    
void renameNoteBook(@Param("userId") Long userId,@Param("oldTitle") String oldTitle,@Param("newTitle") String newTitle);  

@Modifying
@Query(value = "DELETE FROM note_book WHERE id=:bookId",nativeQuery =true)    
void removeNoteBook(@Param("bookId") Long notebookId); 

}
