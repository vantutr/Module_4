package com.example.nasa_hibernate.repository;

import com.example.nasa_hibernate.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByDate(Date date);
}
