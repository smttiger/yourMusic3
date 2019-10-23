package com.itStep.yourMusic.service;

import com.itStep.yourMusic.domain.Song;
import com.itStep.yourMusic.repository.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class SongService {
    @Autowired
    private SongRepo songRepo;

    @Value("${upload.path}")
    private String uploadPath;

    private Iterable<Song> songs;

    public void uploadSong(String artist, String name, Model model,
                           MultipartFile file) {

        Song song = new Song(artist, name);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/" + resultFilename));
                model.addAttribute("uploadReport", "File was successfully uploaded, thank you!");
                song.setFilename(resultFilename);
                songRepo.save(song);
                Iterable<Song> songs = songRepo.findAll();
                model.addAttribute("songs", songs);
            } catch (IOException e) {
                model.addAttribute("uploadReport", "File was not uploaded, sorry, something went wrong.");
            }
        }
    }

    public Iterable<Song> searchByArtist(String artist) {
        if (artist != null && !artist.isEmpty()) {
            songs = songRepo.findByArtist(artist);
        } else songs = songRepo.findAll();
        return songs;
    }

    public Iterable<Song> searchByName(String name) {
        if (name != null && !name.isEmpty()) {
            songs = songRepo.findByName(name);
        } else songs = songRepo.findAll();
        return songs;
    }
}