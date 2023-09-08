package com.aloysius.NoteTakingApplication.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class NoteUsers {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public NoteUsers(NoteUsers users) {
        this.id = users.id;
        this.firstName = users.firstName;
        this.lastName = users.lastName;
        this.username = users.username;
        this.password = users.password;
    }
}
