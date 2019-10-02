package com.itStep.yourMusic.repository;

import com.itStep.yourMusic.domain.Playlist;
import com.itStep.yourMusic.domain.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaylistRepo extends CrudRepository<Playlist,Long> {

    List<Playlist> findByName(String name);
    Playlist findById(Integer id);
}
