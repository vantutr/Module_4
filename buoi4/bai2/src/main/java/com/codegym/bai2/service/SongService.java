package com.codegym.bai2.service;

import com.codegym.bai2.model.Song;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SongService implements ISongService {
    private final List<Song> songs = new ArrayList<>();

    @Override
    public List<Song> findAll() {
        return songs;
    }

    @Override
    public void save(Song song) {
        songs.add(song);
    }
}