package com.pellanotes.pella.database.models;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name="note_book",uniqueConstraints={@UniqueConstraint(columnNames={"owner_id","title"})})
public class NoteBook {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable=false)
    private String title;

    @Column(name="owner_id",nullable=false,insertable=false,updatable=false)
    private Long ownerId;


    private boolean isDefault=false;

    @Column(name="created_at",nullable=false)
    @CreationTimestamp
    private LocalDate createdAt;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private User owner;

    @OneToMany(mappedBy = "noteBook",cascade = CascadeType.ALL,orphanRemoval=true,fetch=FetchType.EAGER)
    private  List<Note> notes;

    //For JPA
    public NoteBook() {
    }

    public NoteBook(String title,User owner) {
        this.title=title;
        this.owner=owner;
        this.notes=new ArrayList<>();
    }

    public NoteBook(String title,User owner,boolean isDefault) {
        this.title=title;
        this.owner=owner;
        this.isDefault=isDefault;
    }

   public  Long getId(){
        return this.id;
    }
    
   public Long getOwnerId(){
        return this.ownerId;
    }

    public LocalDate getCreationDate(){
        return this.createdAt;
    }

    public String getTitle(){
        return this.title;
    }

    public void updateTitle(String title){
        this.title=title;
    }

    public List<Note> getNotes(){
        return this.notes;
    }
    

}
