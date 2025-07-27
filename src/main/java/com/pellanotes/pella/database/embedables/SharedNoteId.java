package com.pellanotes.pella.database.embedables;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

// this composite primary key for SharedNote table

@Embeddable
public class SharedNoteId implements Serializable{

    
    Long recipientId;

    Long noteId;

    public SharedNoteId() {} // needed for embeddables

    public SharedNoteId(Long recipientId,Long noteId){
            this.recipientId=recipientId;
            this.noteId=noteId;
    }

       @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SharedNoteId)) return false;
            SharedNoteId that = (SharedNoteId) o;
            return Objects.equals(recipientId, that.recipientId) &&
                Objects.equals(noteId, that.noteId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(recipientId, noteId);
        }

}
