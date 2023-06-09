package com.aloysius.NoteTakingApplication.Services;

import com.aloysius.NoteTakingApplication.Models.Author;
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
class AuthorServiceTest {
    @InjectMocks
    private AuthorService authorService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthorRepository authorRepository;

    @Test
    void registerOneAuthor() throws NoteNotFoundException {

        Author author1 = new Author(1L, "Jerry", "Okhue", "jerry@gmail.com","12345");

        authorService.register(author1);

        ArgumentCaptor<Author> authorCapture = ArgumentCaptor.forClass(Author.class);

        verify(authorRepository).save(authorCapture.capture());

        Author capturedValue = authorCapture.getValue();

        assertThat(capturedValue).isEqualTo(author1);

       verify(authorRepository, times(1)).findByEmail("jerry@gmail.com");
        verify(authorRepository, times(1)).save(author1);
        verify(passwordEncoder, times(1)).encode("12345");
        verifyNoMoreInteractions(authorRepository);
        verifyNoMoreInteractions(passwordEncoder);



    }
    @Test
    void registerOneAuthorWillThrowException() throws NoteNotFoundException {

        Author author1 = new Author(1L, "Jerry", "Okhue", "jerry@gmail.com","12345");
        Author author2 = new Author(2L, "John", "Doe", "jerry@gmail.com","45679");

        authorService.register(author1);

        BDDMockito.given(authorRepository.findByEmail(author1.getEmail())).willReturn(Optional.of(author1));

        assertThatThrownBy(()->authorService.register(author2))
                .isInstanceOf(NoteNotFoundException.class)
                .hasMessageContaining(String.format("%s already Exist", author2.getEmail()));

        Mockito.verify(authorRepository, never()).save(author2);





    }
}