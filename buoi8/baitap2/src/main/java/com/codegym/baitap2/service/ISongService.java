package com.codegym.baitap2.service;

import com.codegym.baitap2.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();
    void save(Song song);
    Song findById(Long id);
}
