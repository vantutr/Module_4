package com.codegym.baitap2.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Song {

    private Long id;

    @NotEmpty(message = "Tên bài hát không được bỏ trống!")
    @Size(max = 800, message = "Tên bài hát không được vượt quá 800 ký tự!")
    @Pattern(regexp = "^[^@;,.=+\\-]+$", message = "Tên bài hát không được chứa ký tự đặc biệt.")
    private String name;

    @NotEmpty(message = "Tên nghệ sĩ không được bỏ trống!")
    @Size(max = 300, message = "Tên nghệ sĩ không được vượt quá 300 ký tự!")
    @Pattern(regexp = "^[^@;,.=+\\-]+$", message = "Tên nghệ sĩ không được chứa ký tự đặc biệt.")
    private String artist;

    @NotEmpty(message = "Thể loại nhạc không được bỏ trống!")
    @Size(max = 1000, message = "Thể loại không được vượt quá 1000 ký tự!")
    @Pattern(regexp = "^[^@;.=+\\-]+$", message = "Thể loại không được chứa ký tự đặc biệt ngoại trừ dấu phẩy (,).")
    private String genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
