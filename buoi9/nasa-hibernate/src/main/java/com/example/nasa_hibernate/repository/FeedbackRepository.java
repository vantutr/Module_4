package com.example.nasa_hibernate.repository;

import com.example.nasa_hibernate.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query("SELECT f FROM Feedback f WHERE f.date = :date")
    Page<Feedback> findByDate(@Param("date") Date date, Pageable pageable);
}
