package com.itStep.yourMusic.domain;

import javax.persistence.*;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String artist;
    private String name;
    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="playlist_id")
    private Playlist playlist;

    public Song(String artist, String name) {
        this.artist = artist;
        this.name = name;
    }

    public Song(){}

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
