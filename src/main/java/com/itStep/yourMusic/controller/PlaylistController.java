package com.itStep.yourMusic.controller;

import com.itStep.yourMusic.domain.Playlist;
import com.itStep.yourMusic.domain.Song;
import com.itStep.yourMusic.domain.User;
import com.itStep.yourMusic.repository.PlaylistRepo;
import com.itStep.yourMusic.repository.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class PlaylistController {
    @Autowired
    private PlaylistRepo playlistRepo;

    @Autowired
    private SongRepo songRepo;

    @GetMapping("/playlists/{user}")
    public String userPlaylists(

            @PathVariable User user,
            Model model
    ) {
        Set<Playlist> playlists = user.getPlaylists();
        model.addAttribute("playlists", playlists);
        return "playlists";
    }

    @PostMapping("/playlists/{user}")
    public String createPlaylist(
            @PathVariable User user,
            @RequestParam String playlistName,
            Model model,
            @AuthenticationPrincipal User currentUser
    ) {

        Playlist playlist = new Playlist(playlistName, currentUser);
        Set<Playlist> userPlaylists = user.getPlaylists();
        userPlaylists.add(playlist);
        playlistRepo.save(playlist);
        user.setPlaylists(userPlaylists);
        model.addAttribute("playlists", userPlaylists);
        return "playlists";
    }

    @GetMapping("/playlists/{user}/{id}")
    public String playlistSongs(
            @PathVariable(name = "id") int id,
            Model model
    ) {
        Playlist playlist =  playlistRepo.findById(id);
        Set<Song> plSongs = playlist.getPlaylistSongs();
        model.addAttribute("playlistSongs", plSongs);
        model.addAttribute("playlistName",playlist.getName());
        return "plSongs";
    }

    @PostMapping("/playlists/{UserId}/{playlistId}/add")
    public String addSongToPlaylist(
            @PathVariable(name = "playlistId") int id,
            @RequestParam String name,
            Model model
    ) {
        Playlist playlist =  playlistRepo.findById(id);
        Set<Song> plSongs = playlist.getPlaylistSongs();
        List<Song> song = songRepo.findByName(name);
        plSongs.addAll(song);
        playlist.setPlaylistSongs(plSongs);
        playlistRepo.save(playlist);
        model.addAttribute("playlistSongs", plSongs);
        return "plsongs";
    }

}


