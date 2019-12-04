package com.itStep.yourMusic.service;

import com.itStep.yourMusic.domain.Song;
import com.itStep.yourMusic.repository.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class SongService {
    @Autowired
    private SongRepo songRepo;


    @Value("${upload.path}")
    private String uploadPath;

    private Iterable<Song> songs;


    public void uploadSong(
            Song song,
            BindingResult bindingResult,
            Model model,
            MultipartFile file) {


        if (file == null || file.getOriginalFilename().isEmpty()) {
            model.addAttribute("chosenError", "File is not chosen");
        } else if (!file.getOriginalFilename().endsWith("mp3")) {
            model.addAttribute("mp3Error", "File is not mp3");
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ErrorService.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("song", song);
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty() && file.getOriginalFilename().endsWith("mp3")) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + file.getOriginalFilename();
                try {
                    file.transferTo(new File(uploadPath + "/" + resultFilename));
                    model.addAttribute("uploadReportSuccess", "File was successfully uploaded, thank you!");
                    model.addAttribute("alert", "alert-success");
                    song.setFilename(resultFilename);
                    song.setArtist(song.getArtist().toLowerCase());
                    song.setName(song.getName().toLowerCase());
                    songRepo.save(song);
                    model.addAttribute("song", null);
                } catch (IOException e) {
                    model.addAttribute("uploadReportFail", "File was not uploaded, sorry, something went wrong," +
                            "probably file is too big");
                    model.addAttribute("alert", "alert-danger");
                }
            }
        }
    }

    public Iterable<Song> searchByArtist(String artist) {
        if (artist != null) {
            songs = songRepo.findByArtistContaining(artist.toLowerCase());
        } else songs = songRepo.findAll();
        return songs;
    }

    public Iterable<Song> searchByName(String name) {
        if (name != null) {
            songs = songRepo.findByNameContaining(name.toLowerCase());
        } else songs = songRepo.findAll();
        return songs;
    }
}