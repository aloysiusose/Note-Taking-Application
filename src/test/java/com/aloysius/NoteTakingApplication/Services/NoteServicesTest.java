package com.aloysius.NoteTakingApplication.Services;

import com.aloysius.NoteTakingApplication.Models.NoteUsers;
import com.aloysius.NoteTakingApplication.Models.Note;
import com.aloysius.NoteTakingApplication.Repository.NoteUsersRepository;
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
    private NoteUsersRepository noteUsersRepository;
    @Mock
    private NoteMapper noteMapper;

    @Test
    void shouldAddNotes() {
        //given
        NoteUsers noteUsers1 = new NoteUsers();
        noteUsers1.setId(1L); noteUsers1.setFirstName("Jerry"); noteUsers1.setLastName("Okhue");
        noteUsers1.setUsername("jerry@gmail.com"); noteUsers1.setPassword("12345");
        Note note1 = new Note(10L, "first note", "this is my first note", LocalDate.now(), noteUsers1);

        //when
        when(noteUsersRepository.findByUsername(noteUsers1.getUsername())).thenReturn(Optional.of(noteUsers1));
        underTest.addNotes(note1, noteUsers1.getUsername());

        ArgumentCaptor<Note> notesCaptured = ArgumentCaptor.forClass(Note.class);

        verify(noteRepository).save(notesCaptured.capture());

        Note capturedNotes = notesCaptured.getValue();
        assertThat(capturedNotes).isEqualTo(note1);

    }
    @Test
    void addNotesShouldThrowException() {
        //given
        NoteUsers noteUsers1 = new NoteUsers();
        noteUsers1.setId(1L); noteUsers1.setFirstName("Jerry"); noteUsers1.setLastName("Okhue");
        noteUsers1.setUsername("jerry@gmail.com"); noteUsers1.setPassword("12345");
        Note note1 = new Note(10L, "first note", "this is my first note", LocalDate.now(), noteUsers1);

        //when

        BDDMockito.given(noteUsersRepository.findByUsername(noteUsers1.getUsername())).willReturn(Optional.empty());

        assertThatThrownBy(()->underTest.addNotes(note1, noteUsers1.getUsername()))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining(String.format("%s not Found", noteUsers1.getUsername()));

    }

    @Test
    void shouldGetAllMyNotes() throws NoteNotFoundException {
        //given
        NoteUsers noteUsers1 = new NoteUsers();
        noteUsers1.setId(1L); noteUsers1.setFirstName("Jerry"); noteUsers1.setLastName("Okhue");
        noteUsers1.setUsername("jerry@gmail.com"); noteUsers1.setPassword("12345");
        Note note1 = new Note(10L, "first note", "this is my first note", LocalDate.now(), noteUsers1);
        //when
        BDDMockito.given(noteRepository.findByNoteUsersUsername(noteUsers1.getUsername())).willReturn(Optional.<List<Note>>of(Collections.singletonList(note1)));

        underTest.allMyNotes(noteUsers1.getUsername());
        //then
        verify(noteRepository).findByNoteUsersUsername(noteUsers1.getUsername());

    }

    @Test
    void shouldFindNoteWhenGivenATitle() throws NoteNotFoundException {
        //given
        NoteUsers noteUsers1 = new NoteUsers();
        noteUsers1.setId(1L); noteUsers1.setFirstName("Jerry"); noteUsers1.setLastName("Okhue");
        noteUsers1.setUsername("jerry@gmail.com"); noteUsers1.setPassword("12345");
        Note note1 = new Note(10L, "first note", "this is my first note", LocalDate.now(), noteUsers1);

        //when
        when(noteRepository.findByNoteUsersUsernameAndTitle(noteUsers1.getUsername(), note1.getTitle())).thenReturn(Optional.of(note1));

        underTest.findNoteByTitle(noteUsers1.getUsername(), "first note");
//Then
        verify(noteRepository).findByNoteUsersUsernameAndTitle(noteUsers1.getUsername(), note1.getTitle());

    }
}