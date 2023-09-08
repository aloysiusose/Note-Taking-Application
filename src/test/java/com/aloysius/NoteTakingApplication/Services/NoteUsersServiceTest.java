package com.aloysius.NoteTakingApplication.Services;

import com.aloysius.NoteTakingApplication.Models.NoteUsers;
import com.aloysius.NoteTakingApplication.Repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteUsersServiceTest {
    @InjectMocks
    private AuthorService authorService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthorRepository authorRepository;

    @Test
    void registerOneAuthor() throws NoteNotFoundException {

        NoteUsers noteUsers1 = new NoteUsers(1L, "Jerry", "Okhue", "jerry@gmail.com","12345");

        authorService.register(noteUsers1);

        ArgumentCaptor<NoteUsers> authorCapture = ArgumentCaptor.forClass(NoteUsers.class);

        verify(authorRepository).save(authorCapture.capture());

        NoteUsers capturedValue = authorCapture.getValue();

        assertThat(capturedValue).isEqualTo(noteUsers1);

       verify(authorRepository, times(1)).findByEmail("jerry@gmail.com");
        verify(authorRepository, times(1)).save(noteUsers1);
        verify(passwordEncoder, times(1)).encode("12345");
        verifyNoMoreInteractions(authorRepository);
        verifyNoMoreInteractions(passwordEncoder);



    }
    @Test
    void registerOneAuthorWillThrowException() throws NoteNotFoundException {

        NoteUsers noteUsers1 = new NoteUsers(1L, "Jerry", "Okhue", "jerry@gmail.com","12345");
        NoteUsers noteUsers2 = new NoteUsers(2L, "John", "Doe", "jerry@gmail.com","45679");

        authorService.register(noteUsers1);

        BDDMockito.given(authorRepository.findByEmail(noteUsers1.getEmail())).willReturn(Optional.of(noteUsers1));

        assertThatThrownBy(()->authorService.register(noteUsers2))
                .isInstanceOf(NoteNotFoundException.class)
                .hasMessageContaining(String.format("%s already Exist", noteUsers2.getEmail()));

        Mockito.verify(authorRepository, never()).save(noteUsers2);





    }
}