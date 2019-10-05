package com.itStep.yourMusic.repository;

import com.itStep.yourMusic.domain.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepo extends CrudRepository<Song,Long> {
    List<Song> findByArtist(String artist);
    List<Song> findByName(String name);
    Song findById(int id);
}
