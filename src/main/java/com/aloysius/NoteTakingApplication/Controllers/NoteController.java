package com.aloysius.NoteTakingApplication.Controllers;

import com.aloysius.NoteTakingApplication.Models.Note;
import com.aloysius.NoteTakingApplication.Models.NoteUsers;
import com.aloysius.NoteTakingApplication.Services.NoteDTO;
import com.aloysius.NoteTakingApplication.Services.NoteNotFoundException;
import com.aloysius.NoteTakingApplication.Services.NoteServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/home")
@RequiredArgsConstructor
@RestController
public class NoteController {

    private final NoteServices noteServices;
    @PostMapping("/add")
    public void saveNote(@AuthenticationPrincipal NoteUsers customUserDetails, @RequestBody Note notes){

        String email = customUserDetails.getUsername();

        noteServices.addNotes(notes, email);
        // i need to map the written note to a specific author which is the logged-in user
    }
    @GetMapping("/notes")
    public List<NoteDTO> myNotes(@AuthenticationPrincipal NoteUsers customUserDetails) throws NoteNotFoundException {

        String email = customUserDetails.getUsername();

        return noteServices.allMyNotes(email);
        // i need to return only my notes, i can not see notes by others
    }
    @GetMapping("/notes/{title}")
    public NoteDTO findNoteByTitle(@PathVariable String title, @AuthenticationPrincipal NoteUsers customUserDetails) throws NoteNotFoundException {
        String email = customUserDetails.getUsername();
        return noteServices.findNoteByTitle(email, title);

    }

//    public void shareNotes(@PathVariable String email, Note note){
//
//    }
    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception){

        return  ResponseEntity.badRequest().body(exception.getMessage());
    }


}
