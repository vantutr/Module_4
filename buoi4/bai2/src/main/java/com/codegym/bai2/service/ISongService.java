package com.codegym.bai2.service;

import com.codegym.bai2.model.Song;
import java.util.List;

public interface ISongService {
    List<Song> findAll();
    void save(Song song);
}