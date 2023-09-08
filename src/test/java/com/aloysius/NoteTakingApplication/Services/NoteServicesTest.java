package com.aloysius.NoteTakingApplication.Services;

import com.aloysius.NoteTakingApplication.Models.NoteUsers;
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
        NoteUsers noteUsers1 = new NoteUsers(1L, "Jerry", "Okhue", "jerry@gmail.com","12345");
        Note note1 = new Note(10L, "first note", "this is my first note", LocalDate.now(), noteUsers1);

        //when
        when(authorRepository.findByEmail(noteUsers1.getEmail())).thenReturn(Optional.of(noteUsers1));
        underTest.addNotes(note1, noteUsers1.getEmail());

        ArgumentCaptor<Note> notesCaptured = ArgumentCaptor.forClass(Note.class);

        Mockito.verify(noteRepository).save(notesCaptured.capture());

        Note capturedNotes = notesCaptured.getValue();
        assertThat(capturedNotes).isEqualTo(note1);

    }
    @Test
    void addNotesShouldThrowException() {
        //given
        NoteUsers noteUsers1 = new NoteUsers(1L, "Jerry", "Okhue", "jerry@gmail.com","12345");
        Note note1 = new Note(10L, "first note", "this is my first note", LocalDate.now(), noteUsers1);

        //when

        BDDMockito.given(authorRepository.findByEmail(noteUsers1.getEmail())).willReturn(Optional.empty());

        assertThatThrownBy(()->underTest.addNotes(note1, noteUsers1.getEmail()))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining(String.format("%s not Found", noteUsers1.getEmail()));

    }

    @Test
    void shouldGetAllMyNotes() throws NoteNotFoundException {
        //given
        NoteUsers noteUsers1 = new NoteUsers(1L, "Jerry", "Okhue", "jerry@gmail.com","12345");
        Note note1 = new Note(10L, "first note", "this is my first note", LocalDate.now(), noteUsers1);
        //when
        BDDMockito.given(noteRepository.findByAuthorEmail(noteUsers1.getEmail())).willReturn(Optional.<List<Note>>of(Collections.singletonList(note1)));

        underTest.allMyNotes(noteUsers1.getEmail());
        //then
        verify(noteRepository).findByAuthorEmail(noteUsers1.getEmail());

    }

    @Test
    void shouldFindNoteWhenGivenATitle() throws NoteNotFoundException {
        //given
        NoteUsers noteUsers1 = new NoteUsers(1L, "Jerry", "Okhue", "jerry@gmail.com","12345");
        Note note1 = new Note(10L, "first note", "this is my first note", LocalDate.now(), noteUsers1);

        //when
        when(noteRepository.findByAuthorEmailAndTitle(noteUsers1.getEmail(), note1.getTitle())).thenReturn(Optional.of(note1));

        underTest.findNoteByTitle(noteUsers1.getEmail(), "first note");
//Then
        verify(noteRepository).findByAuthorEmailAndTitle(noteUsers1.getEmail(), note1.getTitle());

    }
}