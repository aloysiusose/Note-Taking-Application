package com.aloysius.NoteTakingApplication.Services;

import com.aloysius.NoteTakingApplication.Models.Author;
import com.aloysius.NoteTakingApplication.Models.Note;
import com.aloysius.NoteTakingApplication.Repository.AuthorRepository;
import com.aloysius.NoteTakingApplication.Repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServicesTest {
    @InjectMocks
    private NoteServices underTest;

    @Mock
    private NoteRepository noteRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private NoteMapper noteMapper;

    @Test
    void shouldAddNotes() {
        //given
        Author author1 = new Author(1L, "Jerry", "Okhue", "jerry@gmail.com","12345");
        Note note1 = new Note(10L, "first note", "this is my first note", LocalDate.now(), author1);

        //when
        when(authorRepository.findByEmail(author1.getEmail())).thenReturn(Optional.of(author1));
        underTest.addNotes(note1, author1.getEmail());

        ArgumentCaptor<Note> notesCaptured = ArgumentCaptor.forClass(Note.class);

        Mockito.verify(noteRepository).save(notesCaptured.capture());

        Note capturedNotes = notesCaptured.getValue();
        assertThat(capturedNotes).isEqualTo(note1);

    }
    @Test
    void addNotesShouldThrowException() {
        //given
        Author author1 = new Author(1L, "Jerry", "Okhue", "jerry@gmail.com","12345");
        Note note1 = new Note(10L, "first note", "this is my first note", LocalDate.now(), author1);

        //when

        BDDMockito.given(authorRepository.findByEmail(author1.getEmail())).willReturn(Optional.empty());

        assertThatThrownBy(()->underTest.addNotes(note1, author1.getEmail()))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining(String.format("%s not Found", author1.getEmail()));

    }

    @Test
    void shouldGetAllMyNotes() throws NoteNotFoundException {
        //given
        Author author1 = new Author(1L, "Jerry", "Okhue", "jerry@gmail.com","12345");
        Note note1 = new Note(10L, "first note", "this is my first note", LocalDate.now(), author1);
        //when
        BDDMockito.given(noteRepository.findByAuthorEmail(author1.getEmail())).willReturn(Optional.<List<Note>>of(Collections.singletonList(note1)));

        underTest.allMyNotes(author1.getEmail());
        //then
        verify(noteRepository).findByAuthorEmail(author1.getEmail());

    }

    @Test
    void shouldFindNoteWhenGivenATitle() throws NoteNotFoundException {
        //given
        Author author1 = new Author(1L, "Jerry", "Okhue", "jerry@gmail.com","12345");
        Note note1 = new Note(10L, "first note", "this is my first note", LocalDate.now(), author1);

        //when
        when(noteRepository.findByAuthorEmailAndTitle(author1.getEmail(), note1.getTitle())).thenReturn(Optional.of(note1));

        underTest.findNoteByTitle(author1.getEmail(), "first note");
//Then
        verify(noteRepository).findByAuthorEmailAndTitle(author1.getEmail(), note1.getTitle());

    }
}