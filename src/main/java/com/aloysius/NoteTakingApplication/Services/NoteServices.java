package com.aloysius.NoteTakingApplication.Services;

import com.aloysius.NoteTakingApplication.Models.Author;
import com.aloysius.NoteTakingApplication.Models.Note;
import com.aloysius.NoteTakingApplication.Repository.AuthorRepository;
import com.aloysius.NoteTakingApplication.Repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoteServices {

    private final NoteMapper noteMapper;

    private final NoteRepository noteRepository;
    private final AuthorRepository authorRepository;

    public void addNotes(Note note, String email){


        Author author = authorRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("%s not Found", email)));
        note.setAuthor(author);
        noteRepository.save(note);

//// TODO: will have to redirect to registration page.
    }

    public List<NoteDTO> allMyNotes(String email) throws NoteNotFoundException {

        return noteRepository.findByAuthorEmail(email)
                .orElseThrow(()-> new NoteNotFoundException(String.format("Note with this %s not found", email)))
                .stream().map(noteMapper::process).collect(Collectors.toList());
        //will have to query 2 tables
    }

    public NoteDTO findNoteByTitle(String email,String title) throws NoteNotFoundException {

        var note = noteRepository.findByAuthorEmailAndTitle(email,title)
                .orElseThrow(() -> new NoteNotFoundException(String.format("Note with this %s not found", title)));
        NoteDTO notedto = noteMapper.process(note);

        return notedto;
    }
}
