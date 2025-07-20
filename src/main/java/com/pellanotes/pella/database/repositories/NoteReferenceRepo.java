package com.pellanotes.pella.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pellanotes.pella.database.models.NoteReference;

public interface  NoteReferenceRepo extends JpaRepository<NoteReference, Long> {
    


}
