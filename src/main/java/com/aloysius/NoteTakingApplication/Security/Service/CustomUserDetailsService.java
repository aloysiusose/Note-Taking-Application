package com.aloysius.NoteTakingApplication.Security.Service;

import com.aloysius.NoteTakingApplication.Models.NoteUsers;
import com.aloysius.NoteTakingApplication.Repository.NoteUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final String USER_NOT_FOUND = "user with username : %s not found";

    private final NoteUsersRepository noteUsersRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return noteUsersRepository.findByUsername(email).map(NoteUserDetails::new)
               .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public static class NoteUserDetails extends NoteUsers implements UserDetails{
        public NoteUserDetails(NoteUsers users) {
            super(users);
        }
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }


}
