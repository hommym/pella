package com.pellanotes.pella.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pellanotes.pella.database.models.User;

public interface  UserRepo extends JpaRepository<User, Long> {
    

    // Custom Queries 
}
