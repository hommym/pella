package com.pellanotes.pella.database.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pellanotes.pella.database.models.NoteBook;

public interface NoteBookRepo extends JpaRepository<NoteBook, Long>{
    



@Query(value = "SELECT * FROM note_book WHERE owner_id=:userId AND is_default=true",nativeQuery =true)    
Optional<NoteBook> getDefaultNoteBook(@Param("userId") Long userId);   


}
