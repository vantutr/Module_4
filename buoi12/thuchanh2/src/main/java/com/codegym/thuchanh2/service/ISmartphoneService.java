package com.codegym.thuchanh2.service;

import com.codegym.thuchanh2.model.Smartphone;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface ISmartphoneService {
    Iterable<Smartphone> findAll();

    Optional<Smartphone> findById(Long id);

    Smartphone save(Smartphone smartphone);

    void remove(Long id);
}
