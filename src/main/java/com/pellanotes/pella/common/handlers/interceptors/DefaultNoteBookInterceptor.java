package com.pellanotes.pella.common.handlers.interceptors;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.pellanotes.pella.database.models.NoteBook;
import com.pellanotes.pella.database.models.User;
import com.pellanotes.pella.database.repositories.NoteBookRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Component
public class DefaultNoteBookInterceptor implements HandlerInterceptor {
    

    private final NoteBookRepo noteBookRepo;

    public DefaultNoteBookInterceptor(NoteBookRepo noteBookRepo){
        this.noteBookRepo=noteBookRepo;
    }


     @Override
    public void afterCompletion(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull Object handler,
                                @Nullable Exception ex) throws Exception {

        User user = (User) request.getAttribute("user");
       if(ex ==null && user!=null){
       
        if((this.noteBookRepo.getDefaultNoteBook(user.getId())).isEmpty()){
            // creating default note books
        System.out.println("Creating Default NoteBook...");
        NoteBook defaultBook= new NoteBook("default",user,true);
        this.noteBookRepo.save(defaultBook);
        System.out.println("Default NoteBook Created");

        }
       }else {
        System.out.println("Default NoteBook not created");
       }

    }
}
