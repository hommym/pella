package com.pellanotes.pella.features.note;

import org.springframework.stereotype.Service;

import com.pellanotes.pella.database.repositories.UserRepo;


@Service
public class NoteService {
    
    private final UserRepo userRepo;


    public NoteService(UserRepo userRepo){
        this.userRepo=userRepo;
    }





}
