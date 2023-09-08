package com.aloysius.NoteTakingApplication.Repository;

import com.aloysius.NoteTakingApplication.Models.NoteUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteUsersRepository extends JpaRepository<NoteUsers, Long> {
    Optional<NoteUsers> findByUsername(String username);
}
