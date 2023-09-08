package com.aloysius.NoteTakingApplication.Security.Service;

import com.aloysius.NoteTakingApplication.Models.NoteUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@RequiredArgsConstructor
public class CustomUserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private final NoteUsers noteUsers;

    @Override
    public String getPassword() {
        return noteUsers.getPassword();
    }

    @Override
    public String getUsername() {
        return noteUsers.getEmail();
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
