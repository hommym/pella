package com.pellanotes.pella.database.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="note",uniqueConstraints={@UniqueConstraint(columnNames={"note_book_id","title"})})
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content; 
    
    @Column(name="note_book_id",insertable=false,updatable=false)
    private Long noteBookId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="note_book_id")
    @OnDelete(action=OnDeleteAction.CASCADE)
    NoteBook noteBook;

    @Column(name="created_at")
    @CreationTimestamp
    private LocalDate createdAt;

    @OneToMany(mappedBy="note",fetch=FetchType.EAGER,orphanRemoval=true)
    List<NoteReference> noteReferences;

    public Note(){ // for JPA

    }

    public Note(String title,String content,NoteBook noteBook){ 

        this.title=title;
        this.content=content;
        this.noteBook=noteBook;
        this.noteBookId=noteBook.getId();
    }



    public   Long getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getContent(){
        return this.content;
    }

    public Long getNoteBookId(){
        return this.noteBookId;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setContent(String updatedContent){
        this.content=updatedContent;
    }

    public List<NoteReference> getReferences(){
        if(this.noteReferences==null) return new ArrayList<>();
        return this.noteReferences;
    }

    public NoteBook getBook(){
        return this.noteBook;
    }

}
