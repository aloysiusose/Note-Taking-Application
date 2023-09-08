package com.aloysius.NoteTakingApplication.Services;

import com.aloysius.NoteTakingApplication.Models.NoteUsers;
import com.aloysius.NoteTakingApplication.Repository.NoteUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final NoteUsersRepository noteUsersRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(NoteUsers noteUsers) throws NoteNotFoundException {

        boolean emailExist = noteUsersRepository.findByUsername(noteUsers.getUsername())
                        .isPresent();

        if (emailExist){
            throw new NoteNotFoundException(String.format("%s already Exist", noteUsers.getUsername()));
        }
        noteUsers.setPassword(passwordEncoder.encode(noteUsers.getPassword()));

        noteUsersRepository.save(noteUsers);


    }
}
