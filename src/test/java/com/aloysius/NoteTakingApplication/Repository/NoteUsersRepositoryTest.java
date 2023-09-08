package com.aloysius.NoteTakingApplication.Repository;

import com.aloysius.NoteTakingApplication.Models.NoteUsers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class NoteUsersRepositoryTest {
    @Autowired
    private NoteUsersRepository underTest;

    @Test
    void findByEmail() {
        //given
        NoteUsers noteUsers1 = new NoteUsers();
        noteUsers1.setId(1L); noteUsers1.setFirstName("Jerry"); noteUsers1.setLastName("Okhue");
        noteUsers1.setUsername("jerry@gmail.com"); noteUsers1.setPassword("12345");
        underTest.save(noteUsers1);
        //when
        var byEmail = underTest.findByUsername("jerry@gmail.com");

        //then
        assertThat(byEmail).isEqualTo(Optional.of(noteUsers1));
    }
}