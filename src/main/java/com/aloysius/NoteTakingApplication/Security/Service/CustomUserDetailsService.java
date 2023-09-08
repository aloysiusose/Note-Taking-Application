package com.aloysius.NoteTakingApplication.Security.Service;

import com.aloysius.NoteTakingApplication.Repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final String USER_NOT_FOUND = "user with username : %s not found";

    private final AuthorRepository authorRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return authorRepository.findByEmail(email).map(CustomUserDetails::new)
               .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }


}
