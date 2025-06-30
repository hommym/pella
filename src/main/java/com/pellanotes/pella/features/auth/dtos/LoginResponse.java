package com.pellanotes.pella.features.auth.dtos;

public class LoginResponse {
    


    public String email;

    public String profile;

    public String username;

    public String authToken;

    public boolean twoFA;


    public LoginResponse(String email, String profile, String username, String authToken,boolean twoFA) {
        this.email = email;
        this.profile = profile;
        this.username = username;
        this.authToken = authToken;
        this.twoFA=twoFA;
    }
    

}
