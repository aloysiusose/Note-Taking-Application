package com.aloysius.NoteTakingApplication.Repository;

import com.aloysius.NoteTakingApplication.Models.NoteUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<NoteUsers, Long> {
    @Query("SELECT u FROM Author u WHERE u.email = :email")
    Optional<NoteUsers> findByEmail(String email);
}
