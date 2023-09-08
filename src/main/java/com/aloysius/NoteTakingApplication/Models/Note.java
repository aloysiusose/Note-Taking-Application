package com.aloysius.NoteTakingApplication.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Note {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String note;
    private LocalDate latestUpdate = LocalDate.now();

    @ManyToOne()
    @JoinColumn(name = "authorId")
    private NoteUsers noteUsers;

}
