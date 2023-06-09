package com.aloysius.NoteTakingApplication.Repository;

import com.aloysius.NoteTakingApplication.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT u FROM Author u WHERE u.email = :email")
    Optional<Author> findByEmail(String email);
}
