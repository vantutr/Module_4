package com.codegym.baitap1.service;

import com.codegym.baitap1.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();
    Song findById(Long id);
    void save(Song song);
    void remove(Long id);
}
