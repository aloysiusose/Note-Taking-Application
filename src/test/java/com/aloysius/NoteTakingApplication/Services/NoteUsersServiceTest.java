package com.aloysius.NoteTakingApplication.Services;

import com.aloysius.NoteTakingApplication.Models.NoteUsers;
import com.aloysius.NoteTakingApplication.Repository.NoteUsersRepository;
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
    private NoteUsersRepository noteUsersRepository;

    @Test
    void registerOneAuthor() throws NoteNotFoundException {

        NoteUsers noteUsers1 = new NoteUsers();
        noteUsers1.setId(1L); noteUsers1.setFirstName("Jerry"); noteUsers1.setLastName("Okhue");
        noteUsers1.setUsername("jerry@gmail.com"); noteUsers1.setPassword("12345");

        authorService.register(noteUsers1);

        ArgumentCaptor<NoteUsers> authorCapture = ArgumentCaptor.forClass(NoteUsers.class);

        verify(noteUsersRepository).save(authorCapture.capture());

        NoteUsers capturedValue = authorCapture.getValue();

        assertThat(capturedValue).isEqualTo(noteUsers1);

       verify(noteUsersRepository, times(1)).findByUsername("jerry@gmail.com");
        verify(noteUsersRepository, times(1)).save(noteUsers1);
        verify(passwordEncoder, times(1)).encode("12345");
        verifyNoMoreInteractions(noteUsersRepository);
        verifyNoMoreInteractions(passwordEncoder);



    }
    @Test
    void registerOneAuthorWillThrowException() throws NoteNotFoundException {

        NoteUsers noteUsers1 = new NoteUsers();
        noteUsers1.setId(1L); noteUsers1.setFirstName("Jerry"); noteUsers1.setLastName("Okhue");
        noteUsers1.setUsername("jerry@gmail.com"); noteUsers1.setPassword("12345");
        NoteUsers noteUsers2 = new NoteUsers();
        noteUsers2.setId(2L); noteUsers2.setFirstName("John"); noteUsers2.setLastName("Doe");
        noteUsers1.setUsername("jerry@gmail.com"); noteUsers2.setPassword("45678");

        authorService.register(noteUsers1);

        BDDMockito.given(noteUsersRepository.findByUsername(noteUsers1.getUsername())).willReturn(Optional.of(noteUsers1));

        assertThatThrownBy(()->authorService.register(noteUsers2))
                .isInstanceOf(NoteNotFoundException.class)
                .hasMessageContaining(String.format("%s already Exist", noteUsers2.getUsername()));

        Mockito.verify(noteUsersRepository, never()).save(noteUsers2);





    }
}