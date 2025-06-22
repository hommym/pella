package com.pellanotes.pella.common.dtos;

public class ErrorResponse {
    
    public String error;
    public int   statusCode;


    public ErrorResponse(String message ,int status){
        this.error=message;
        this.statusCode=status;

    }

}
