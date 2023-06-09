package com.aloysius.NoteTakingApplication.Services;

import com.aloysius.NoteTakingApplication.Models.Note;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class NoteDTO {

    private Long id;
    private String title;
    private String note;
    private LocalDate latestUpdate;

}
