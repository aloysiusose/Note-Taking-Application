package com.aloysius.NoteTakingApplication.Repository;

import com.aloysius.NoteTakingApplication.Models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

//    @Query("SELECT p FROM note p JOIN FETCH p.author c WHERE c.email = :authorEmail")
    Optional<List<Note>> findByAuthorEmail(@Param("authorEmail") String authorEmail);

    Optional<Note> findByAuthorEmailAndTitle(String authorEmail, String title);
}
