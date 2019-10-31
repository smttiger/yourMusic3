package com.itStep.yourMusic.controller;

import com.itStep.yourMusic.domain.User;
import com.itStep.yourMusic.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlaylistController {


    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/playlists/{user}")
    public String userPlaylists(

            @PathVariable User user,
            Model model
    ) {

        model.addAttribute("playlists", user.getPlaylists());
        return "playlists";
    }

    @PostMapping("/playlists/{user}")
    public String createPlaylist(
            @PathVariable User user,
            @RequestParam String playlistName,
            Model model,
            @AuthenticationPrincipal User currentUser
    ) {
         playlistService.createPlaylist(user,playlistName,currentUser,model);
        return "playlists";
    }

    @GetMapping("/playlists/{user}/{id}")
    public String playlistSongs(
            @PathVariable(name = "id") int id,
            Model model
    ) {

        playlistService.showSongs(id, model);
        return "plSongs";
    }


    @PostMapping("/playlists/{user}/{playlistId}/delete")
    public String deletePlaylist(
            @PathVariable User user,
            @PathVariable(name = "playlistId") int id,
            Model model
    ) {

        model.addAttribute("playlists", playlistService.deletePlaylist(user,id));
        return "playlists";
    }

    @PostMapping("/playlists/{UserId}/{playlistId}/{songId}/delete")
    public String deleteSongFromPlaylist(
            @PathVariable(name = "playlistId") int id,
            @PathVariable(name = "songId") int songId,
            Model model
    ) {

        playlistService.deleteSongFromPlaylist(id,songId,model);
        return "plsongs";
    }

    @GetMapping("/playlists/{user}/{playlistId}/search")
    public String search(@RequestParam String artist,
                         @PathVariable(name = "playlistId") int id,
                         Model model,
                         @PageableDefault(sort={"id"},direction = Sort.Direction.DESC) Pageable pageable
    ) {

       playlistService.searchByArtist(artist,id,model);
        //playlistService.searchByArtist(artist,id,model,pageable);
        return "plSongs";
    }


    @GetMapping("/playlists/{user}/{playlistId}/Listen")
    public String listenToPlaylist(
            Model model,
            @PathVariable(name = "playlistId") int id) {

        playlistService.showSongs(id, model);
        return "player";
    }

    @PostMapping("/playlists/{UserId}/{playlistId}/{songId}/add")
    public String addSongToPlaylist(
            @RequestParam String artist,
            @PathVariable(name = "playlistId") int id,
            @PathVariable(name = "songId") int songId,
            Model model
    ) {

        playlistService.addSongToPlaylist(id,songId,model);
            playlistService.searchByArtist(artist,id,model);
        return "plSongs";
    }


}


