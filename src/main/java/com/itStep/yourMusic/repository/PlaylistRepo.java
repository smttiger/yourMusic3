package com.itStep.yourMusic.repository;

import com.itStep.yourMusic.domain.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepo extends CrudRepository<Playlist,Long> {

    Playlist findById(int id);

}
