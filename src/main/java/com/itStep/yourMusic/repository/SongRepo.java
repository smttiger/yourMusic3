package com.itStep.yourMusic.repository;

import com.itStep.yourMusic.domain.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepo extends CrudRepository<Song, Long> {
    List<Song> findByArtistContaining(String artist);
    List<Song> findByNameContaining(String name);
    Song findById(int id);


}
