package com.itStep.yourMusic.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String artist;
    private String name;
    private String filename;



    @ManyToMany
    @JoinTable(name="playlist_song",
    joinColumns = @JoinColumn(name="song_id"),
    inverseJoinColumns = @JoinColumn(name="playlist_id"))
    private Set<Playlist> songPlaylists=new HashSet<>();

    public Song(String artist, String name) {
        this.artist = artist;
        this.name = name;
    }
    public Set<Playlist> getSongPlaylists() {
        return songPlaylists;
    }

    public void setSongPlaylists(Set<Playlist> songPlaylists) {
        this.songPlaylists = songPlaylists;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return id.equals(song.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
