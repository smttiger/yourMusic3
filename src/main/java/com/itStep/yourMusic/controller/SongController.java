package com.itStep.yourMusic.controller;

import com.itStep.yourMusic.domain.Song;
import com.itStep.yourMusic.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
public class SongController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private SongService songService;

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
            @Valid Song song,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file) {
        songService.uploadSong(song, bindingResult,model, file);
        return "upload";
    }

    @GetMapping("search")
    public String search(@RequestParam String artist, Model model) {

        model.addAttribute("playlistSongs", songService.searchByArtist(artist));
        return "player";
    }

    @GetMapping("searchByName")
    public String searchByName(@RequestParam String name, Model model) {

        model.addAttribute("playlistSongs", songService.searchByName(name));
        return "player";
    }

}
