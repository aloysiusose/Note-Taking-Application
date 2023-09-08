package com.aloysius.NoteTakingApplication.Repository;

import com.aloysius.NoteTakingApplication.Models.NoteUsers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class NoteUsersRepositoryTest {
    @Autowired
    private AuthorRepository underTest;

    @Test
    void findByEmail() {
        //given
        NoteUsers noteUsers1 = new NoteUsers(1l, "Elijah", "Maha", "meliha@gmail.com", "12345");
        underTest.save(noteUsers1);
        //when
        var byEmail = underTest.findByEmail("melijah@gmail.com");

        //then
        assertThat(byEmail).isEqualTo(noteUsers1);
    }
}