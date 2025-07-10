package com.pellanotes.pella.common.exceptions;

public class ForbiddenRequest extends  RuntimeException {
    public  ForbiddenRequest(String message){
        super(message);
        }
}
