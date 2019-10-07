package com.itStep.yourMusic.controller;

import com.itStep.yourMusic.domain.Song;
import com.itStep.yourMusic.repository.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class SongController {
    @Autowired
    private SongRepo songRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("upload")
    public String add(

            @RequestParam String artist,
            @RequestParam String name,
            Model model,
            @RequestParam("file") MultipartFile file)  {

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
        return "upload";
    }

    @GetMapping("search")
    public String search(@RequestParam String artist, Model model) {
      Iterable<Song> songs;
        if (artist != null && !artist.isEmpty()) {
            songs = songRepo.findByArtist(artist);
        } else songs =songRepo.findAll();
        model.addAttribute("songs", songs);
        return "player";
    }

    @GetMapping("searchByName")
    public String searchByName(@RequestParam String name, Model model) {
        Iterable<Song> songs;
        if (name != null && !name.isEmpty()) {
            songs = songRepo.findByName(name);
        } else songs = songRepo.findAll();
        model.addAttribute("songs", songs);
        return "player";
    }

}
