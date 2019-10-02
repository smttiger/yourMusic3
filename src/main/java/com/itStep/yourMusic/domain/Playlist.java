package com.itStep.yourMusic.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Playlist {
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Integer id;
        private String name;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="user_id")
        private User author;

    @OneToMany(mappedBy = "playlist", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Song> playlistSongs;

    public Set<Song> getPlaylistSongs() {
        return playlistSongs;
    }

    public void setPlaylistSongs(Set<Song> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }



    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }



    public Playlist(String name, User user) {
        this.name = name;
        this.author=user;
    }
    public Playlist() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
