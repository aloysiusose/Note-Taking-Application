package com.aloysius.NoteTakingApplication.Services;

import com.aloysius.NoteTakingApplication.Models.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {
    public NoteDTO process(Note note){
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId(note.getId());
        noteDTO.setTitle(note.getTitle());
        noteDTO.setNote(note.getNote());
        noteDTO.setLatestUpdate(note.getLatestUpdate());
        return  noteDTO;
    }
}
