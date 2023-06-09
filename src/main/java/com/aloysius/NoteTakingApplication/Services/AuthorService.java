package com.aloysius.NoteTakingApplication.Services;

import com.aloysius.NoteTakingApplication.Models.Author;
import com.aloysius.NoteTakingApplication.Repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(Author author) throws NoteNotFoundException {

        boolean emailExist = authorRepository.findByEmail(author.getEmail())
                        .isPresent();

        if (emailExist){
            throw new NoteNotFoundException(String.format("%s already Exist", author.getEmail()));
        }
        author.setPassword(passwordEncoder.encode(author.getPassword()));

        authorRepository.save(author);


    }
}
