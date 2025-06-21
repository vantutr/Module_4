package com.codegym.baitap2.service;

import com.codegym.baitap2.model.Feedback;
import java.util.List;

public interface IFeedbackService {
    List<Feedback> findAllToday();
    void save(Feedback feedback);
    void like(Long id);
    Feedback findById(Long id);
}