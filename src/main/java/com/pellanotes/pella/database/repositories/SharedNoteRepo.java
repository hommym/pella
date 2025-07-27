package com.pellanotes.pella.database.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pellanotes.pella.database.embedables.SharedNoteId;
import com.pellanotes.pella.database.enums.NoteAccessType;
import com.pellanotes.pella.database.models.SharedNote;

public interface SharedNoteRepo extends JpaRepository <SharedNote, SharedNoteId> {

    
    
@Query(value = "SELECT * FROM shared_note WHERE owner_id=:ownerId",nativeQuery =true)    
List<SharedNote> getSharedNotes(@Param("ownerId") Long ownerId);   


@Query(value = "SELECT * FROM shared_note WHERE recipient_id=:recipientId",nativeQuery =true)    
List<SharedNote> getRecievedSharedNotes(@Param("recipientId") Long recipientId); 


@Modifying
@Query(value = "UPDATE shared_note SET access_type=:accessType WHERE recipient_id=:recipientId AND note_id=:noteId",nativeQuery =true)    
void updateAccessType(@Param("recipientId") Long recipientId,@Param("noteId") Long noteId,@Param("accessType") NoteAccessType accessType);

}
