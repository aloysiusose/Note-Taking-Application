package com.aloysius.NoteTakingApplication.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NoteUsers {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String LastName;
    private String email;
    private String password;

//
//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Note> note = new ArrayList<>();
}
