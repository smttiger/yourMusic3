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
        Playlist playlist = playlistRepo.findById(id);
        Set<Song> plSongs = playlist.getPlaylistSongs();
        model.addAttribute("playlistSongs", plSongs);
        model.addAttribute("playlist", playlist);
        return "plSongs";
    }


    @PostMapping("/playlists/{user}/{playlistId}/delete")
    public String deletePlaylist(
            @PathVariable User user,
            @PathVariable(name = "playlistId") int id,
            Model model
    ) {
        Playlist playlist = playlistRepo.findById(id);
        Set<Playlist> userPlaylists = user.getPlaylists();
        userPlaylists.remove(playlist);
        playlistRepo.delete(playlist);
        user.setPlaylists(userPlaylists);
        model.addAttribute("playlists", userPlaylists);
        return "playlists";
    }

    @PostMapping("/playlists/{UserId}/{playlistId}/{songId}/delete")
    public String deleteSongFromPlaylist(
            @PathVariable(name = "playlistId") int id,
            @PathVariable(name = "songId") int songId,
            Model model
    ) {
        Playlist playlist = playlistRepo.findById(id);
        Set<Song> plSongs = playlist.getPlaylistSongs();
        Song song = songRepo.findById(songId);
        plSongs.remove(song);
        playlist.setPlaylistSongs(plSongs);
        playlistRepo.save(playlist);
        model.addAttribute("playlistSongs", plSongs);
        model.addAttribute("playlist", playlist);
        return "plsongs";
    }

    @GetMapping("/playlists/{user}/{playlistId}/search")
    public String search(@RequestParam String artist,
                         @PathVariable(name = "playlistId") int id,
                         Model model) {
        Iterable<Song> songs;
        Playlist playlist = playlistRepo.findById(id);
        if (artist != null && !artist.isEmpty()) {
            songs = songRepo.findByArtist(artist);
        } else songs = songRepo.findAll();
        model.addAttribute("songs", songs);
        model.addAttribute("playlist", playlist);
        Set<Song> plSongs = playlist.getPlaylistSongs();
        model.addAttribute("playlistSongs", plSongs);
        return "plSongs";
    }

    @GetMapping("/playlists/{user}/{playlistId}/searchByName")
    public String searchByName(@RequestParam String name,
                               Model model,
                               @PathVariable(name = "playlistId") int id) {
        Playlist playlist = playlistRepo.findById(id);
        Iterable<Song> songs;
        if (name != null && !name.isEmpty()) {
            songs = songRepo.findByName(name);
        } else songs = songRepo.findAll();
        model.addAttribute("songs", songs);
        model.addAttribute("playlist", playlist);
        Set<Song> plSongs = playlist.getPlaylistSongs();
        model.addAttribute("playlistSongs", plSongs);
        return "plSongs";

    }

    @GetMapping("/playlists/{user}/{playlistId}/Listen")
    public String listenToPlaylist(
            Model model,
            @PathVariable(name = "playlistId") int id) {
        Playlist playlist = playlistRepo.findById(id);
        Iterable<Song> songs;
        Set<Song> plSongs = playlist.getPlaylistSongs();
        model.addAttribute("songs", plSongs);
        return "player";
    }

    @PostMapping("/playlists/{UserId}/{playlistId}/{songId}/add")
    public String addSongToPlaylist(
            @PathVariable(name = "playlistId") int id,
            @PathVariable(name = "songId") int songId,
            Model model
    ) {
        Playlist playlist = playlistRepo.findById(id);
        Set<Song> plSongs = playlist.getPlaylistSongs();
        Song song = songRepo.findById(songId);
        plSongs.add(song);
        playlist.setPlaylistSongs(plSongs);
        playlistRepo.save(playlist);
        model.addAttribute("playlistSongs", plSongs);
        model.addAttribute("playlist", playlist);
        return "plSongs";
    }


}


