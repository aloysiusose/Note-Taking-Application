package com.aloysius.NoteTakingApplication.Repository;

import com.aloysius.NoteTakingApplication.Models.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository underTest;

    @Test
    void findByEmail() {
        //given
        Author author1 = new Author(1l, "Elijah", "Maha", "meliha@gmail.com", "12345");
        underTest.save(author1);
        //when
        var byEmail = underTest.findByEmail("melijah@gmail.com");

        //then
        assertThat(byEmail).isEqualTo(author1);
    }
}