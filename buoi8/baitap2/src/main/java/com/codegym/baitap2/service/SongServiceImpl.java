package com.codegym.baitap2.service;

import com.codegym.baitap2.model.Song;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SongServiceImpl implements ISongService {
    private static final Map<Long, Song> songMap = new HashMap<>();
    private static long nextId = 1;
    static {
        Song song1 = new Song();
        song1.setId(nextId++);
        song1.setName("Chúng ta của tương lai");
        song1.setArtist("Sơn Tùng M-TP");
        song1.setGenre("Pop, R&B");
        songMap.put(song1.getId(), song1);
    }
    @Override
    public List<Song> findAll() {
        return new ArrayList<>(songMap.values());
    }

    @Override
    public void save(Song song) {
        if (song.getId() == null) {
            // Thêm mới
            song.setId(nextId++);
            songMap.put(song.getId(), song);
        } else {
            // Cập nhật
            songMap.put(song.getId(), song);
        }
    }

    @Override
    public Song findById(Long id) {
        return songMap.get(id);
    }
}
