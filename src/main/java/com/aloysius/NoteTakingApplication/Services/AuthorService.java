package com.aloysius.NoteTakingApplication.Services;

import com.aloysius.NoteTakingApplication.Models.NoteUsers;
import com.aloysius.NoteTakingApplication.Repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(NoteUsers noteUsers) throws NoteNotFoundException {

        boolean emailExist = authorRepository.findByEmail(noteUsers.getEmail())
                        .isPresent();

        if (emailExist){
            throw new NoteNotFoundException(String.format("%s already Exist", noteUsers.getEmail()));
        }
        noteUsers.setPassword(passwordEncoder.encode(noteUsers.getPassword()));

        authorRepository.save(noteUsers);


    }
}
