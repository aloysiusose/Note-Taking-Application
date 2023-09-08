package com.aloysius.NoteTakingApplication.Controllers;

import com.aloysius.NoteTakingApplication.Models.NoteUsers;
import com.aloysius.NoteTakingApplication.Services.AuthorService;
import com.aloysius.NoteTakingApplication.Services.NoteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    @PostMapping("register")
    public void register(@RequestBody NoteUsers noteUsers) throws NoteNotFoundException {

        authorService.register(noteUsers);
        // i will need email validation

    }
    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception){

        return  ResponseEntity.badRequest().body(exception.getMessage());
    }
}
