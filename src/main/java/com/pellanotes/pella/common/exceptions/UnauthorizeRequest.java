package com.pellanotes.pella.common.exceptions;

public class UnauthorizeRequest  extends RuntimeException{
 
    public UnauthorizeRequest(String message){
        super(message);
    }

}
