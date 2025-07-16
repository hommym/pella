package com.pellanotes.pella.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pellanotes.pella.database.models.Note;


public interface NoteRepo extends JpaRepository<Note, Long> {
    

    

}
