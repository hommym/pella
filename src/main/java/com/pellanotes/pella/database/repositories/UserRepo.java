package com.pellanotes.pella.database.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pellanotes.pella.database.models.User;

public interface  UserRepo extends JpaRepository<User, Long> {
    

    // Custom Queries 
    @Query(value ="SELECT email FROM user WHERE email=:eAddress", nativeQuery=true)
    Optional<String> checkEmail(@Param("eAddress") String email);

    @Query(value ="SELECT username FROM user  WHERE username=:uname",nativeQuery=true)
    Optional<String> checkUserName(@Param("uname") String username);

    @Query(value ="SELECT * FROM user WHERE email=:eAddress", nativeQuery=true)
    Optional<User> getUserByEmail(@Param("eAddress") String email);

    @Modifying
    @Query(value ="UPDATE user SET is_verfied=true WHERE id=:userId", nativeQuery=true)
    void verifyUser(@Param("userId") Long userId);

    @Modifying
    @Query(value ="UPDATE user SET password=:passwd WHERE id=:userId", nativeQuery=true)
    void updatePassword(@Param("userId") Long userId, @Param("passwd") String passwd);


}
