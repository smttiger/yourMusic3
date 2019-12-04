package com.itStep.yourMusic.controller;

import com.itStep.yourMusic.domain.User;
import com.itStep.yourMusic.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
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
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model
    ) {
        if (user.equals(currentUser)) {
            model.addAttribute("playlists", user.getPlaylists());
            return "playlists";
        } else return "greeting";
    }

    @PostMapping("/playlists/{user}")
    public String createPlaylist(
            @PathVariable User user,
            @RequestParam String playlistName,
            Model model
    ) {
        playlistService.createPlaylist(user, playlistName, model);
        return "playlists";
    }

    @GetMapping("/playlists/{user}/{id}")
    public String playlistSongs(
            @AuthenticationPrincipal User currentUser,
            @PathVariable int id,
            @PathVariable User user,
            Model model

    ) {
        if (user.equals(currentUser)) {
            playlistService.showSongs(id, model);
            return "plSongs";
        } else return "greeting";
    }


    @PostMapping("/playlists/{user}/{playlistId}/delete")
    public String deletePlaylist(
            @PathVariable User user,
            @PathVariable(name = "playlistId") int id,
            Model model
    ) {

        model.addAttribute("playlists", playlistService.deletePlaylist(user, id));
        return "playlists";
    }

    @PostMapping("/playlists/{UserId}/{playlistId}/{songId}/delete")
    public String deleteSongFromPlaylist(
            @RequestParam(required = false) String songName,
            @RequestParam(required = false) String artist,
            @PathVariable(name = "playlistId") int id,
            @PathVariable int songId,
            Model model
    ) {
        playlistService.deleteSongFromPlaylist(id, songId, model);
        if (artist != null) {
            playlistService.searchByArtist(artist, id, model);
        }
        if (songName != null) {
            playlistService.searchByName(songName, id, model);
        }
        return "plsongs";
    }

    @GetMapping("/playlists/{user}/{playlistId}/{songId}/delete")
    public String deleteSongFromPlaylistGet(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @PathVariable(name = "playlistId") int id,
            Model model
    ) {

        if (user.equals(currentUser)) {
            playlistService.showSongs(id, model);
            return "plsongs";
        } else return "greeting";
    }

    @GetMapping("/playlists/{user}/{playlistId}/search")
    public String search(@RequestParam String artist,
                         @PathVariable(name = "playlistId") int id,
                         Model model

    ) {

        playlistService.searchByArtist(artist, id, model);
        return "plSongs";
    }

    @GetMapping("/playlists/{user}/{playlistId}/searchByName")
    public String searchByName(@RequestParam String songName,
                               @PathVariable(name = "playlistId") int id,
                               Model model
    ) {

        playlistService.searchByName(songName, id, model);
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
            @RequestParam(required = false) String artist,
            @RequestParam(required = false) String songName,
            @PathVariable(name = "playlistId") int id,
            @PathVariable int songId,
            Model model
    ) {

        playlistService.addSongToPlaylist(id, songId, model);
        if (artist != null) {
            playlistService.searchByArtist(artist, id, model);
        }
        if (songName != null) {
            playlistService.searchByName(songName, id, model);
        }
        return "plSongs";
    }

    @GetMapping("/playlists/{user}/{playlistId}/{songId}/add")
    public String addSongToPlaylistGet(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @PathVariable(name = "playlistId") int id,
            Model model
    ) {

        if (user.equals(currentUser)) {
            playlistService.showSongs(id, model);
            return "plsongs";
        } else return "greeting";
    }

}


