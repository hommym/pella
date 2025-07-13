package com.pellanotes.pella.common.handlers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pellanotes.pella.common.dtos.ErrorResponse;
import com.pellanotes.pella.common.exceptions.ForbiddenRequest;
import com.pellanotes.pella.common.exceptions.ResourceConflict;
import com.pellanotes.pella.common.exceptions.ResourceNotFound;
import com.pellanotes.pella.common.exceptions.UnauthorizeRequest;



@RestControllerAdvice
public class GlobalExceptionHandler {
    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> badRequestHandler(MethodArgumentNotValidException err){
        String errMessage=(err.getBindingResult().getFieldErrors()).get(0).getDefaultMessage();
        return new ResponseEntity<>(new ErrorResponse(errMessage, 400),HttpStatus.BAD_REQUEST);
    }




    @ExceptionHandler(ForbiddenRequest.class)
    public ResponseEntity<ErrorResponse> forbiddenRequestHandler(ResourceNotFound err){
        return new ResponseEntity<>(new ErrorResponse(err.getMessage(), 403),HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundHandler(ResourceNotFound err){
        return new ResponseEntity<>(new ErrorResponse(err.getMessage(), 404),HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ResourceConflict.class)
    public ResponseEntity<ErrorResponse> resourceConflictHandler(ResourceConflict err){
        return new ResponseEntity<>(new ErrorResponse(err.getMessage(), 409),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizeRequest.class)
    public ResponseEntity<ErrorResponse> unauthorizeRequestHandler(UnauthorizeRequest err){
        return new ResponseEntity<>(new ErrorResponse(err.getMessage(), 401),HttpStatus.UNAUTHORIZED);
    }
   


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> serverErrorHandler(RuntimeException err){
        System.out.println(err);
        return new ResponseEntity<>(new ErrorResponse("Server Error", 500),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
