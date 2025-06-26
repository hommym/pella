package com.pellanotes.pella.database.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pellanotes.pella.database.models.Otp;

public interface OtpRepo extends JpaRepository<Otp, Long>{
    

@Query(value = "SELECT code FROM otp WHERE user_id=:userId",nativeQuery =true)    
Optional<Integer> getOtpCodeByUserId(@Param("userId") Long userId);    


}
