package com.itStep.yourMusic.controller;

import com.itStep.yourMusic.domain.Song;
import com.itStep.yourMusic.repository.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;

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
//    @GetMapping("/main")
//    public String main(Map<String, Object> model) {
//        Iterable<Song> songs = songRepo.findAll();
//        model.put("songs", songs);
//        return "main";
//    }

//    @PostMapping("/main")
//    public String add(
//            //@AuthenticationPrincipal User user,
//            @RequestParam String artist,
//            @RequestParam String name, Map<String, Object> model)
//                      @RequestParam("file") MultipartFile file) throws IOException
//    {
//        Song song = new Song(artist, name);

//        if (file != null && !file.getOriginalFilename().isEmpty()) {
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//            String uuidFile= UUID.randomUUID().toString();
//            String resultFilename = uuidFile  + file.getOriginalFilename();
//            file.transferTo(new File(uploadPath+"/"+resultFilename));
//            song.setFilename(resultFilename);
//        }
//        songRepo.save(song);
//        Iterable<Song> songs = songRepo.findAll();
//        model.put("songs", songs);
//        return "main";
//    }

    @PostMapping("search")
    public String search(@RequestParam String search, Map<String, Object> model) {
        Iterable<Song> songs;
        if (search != null && !search.isEmpty()) {
            songs = songRepo.findByArtist(search);
        } else songs = songRepo.findAll();
        model.put("songs", songs);
//        return "main";
        return "search results";
    }

    @PostMapping("searchByName")
    public String searchByName(@RequestParam String searchByName, Map<String, Object> model) {
        Iterable<Song> songs;
        if (searchByName != null && !searchByName.isEmpty()) {
            songs = songRepo.findByName(searchByName);
        } else songs = songRepo.findAll();
        model.put("songs", songs);
        //        return "main";
        return "search results";
    }
}
