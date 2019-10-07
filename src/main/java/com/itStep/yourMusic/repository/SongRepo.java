package com.itStep.yourMusic.repository;

import com.itStep.yourMusic.domain.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface SongRepo extends CrudRepository<Song,Long> {
    ArrayList<Song> findByArtist(String artist);
    List<Song> findByName(String name);
    Song findById(int id);
}
