package com.pellanotes.pella.common.exceptions;

public class ValidatorExceptions extends RuntimeException{
    
    String message;
    int status=400; // Bad Request
    public ValidatorExceptions(String message){
        super(message);
        this.message=message;
    }

}
