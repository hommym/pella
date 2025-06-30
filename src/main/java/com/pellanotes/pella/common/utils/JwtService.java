package com.pellanotes.pella.common.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;










@Service
public class JwtService {
    
    @Value("${SECRET_KEY}")
    private String key;

    private final long expireTime=1000*15782016;  //


    private SecretKey getSigingKey(){
        return  Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.key));
    }


    public String getToken(String email){
           Map<String, Object> claim = new HashMap<>();
           claim.put("sub", email);
        return  Jwts.builder().claims(claim)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis()+expireTime))
        .signWith(this.getSigingKey())
        .compact();
    }

    public boolean isTokenValid(String token){
        Date expiration = Jwts.parser()
        .verifyWith(this.getSigingKey())
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getExpiration();
        return expiration.before(new Date());
    }

    public String extractEmail(String token){
        return Jwts.parser()
        .verifyWith(this.getSigingKey())
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
    }


    }






