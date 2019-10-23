package com.itStep.yourMusic.service;

import com.itStep.yourMusic.domain.Playlist;
import com.itStep.yourMusic.domain.Song;
import com.itStep.yourMusic.domain.User;
import com.itStep.yourMusic.repository.PlaylistRepo;
import com.itStep.yourMusic.repository.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepo playlistRepo;

    @Autowired
    private SongRepo songRepo;

    @Autowired
    SongService songService;

    public Set<Playlist> listOfPlaylists(User user,String playlistName,
             User currentUser) {
        Playlist playlist = new Playlist(playlistName, currentUser);
        Set<Playlist> userPlaylists = user.getPlaylists();
        userPlaylists.add(playlist);
        playlistRepo.save(playlist);
        user.setPlaylists(userPlaylists);
        return userPlaylists;
    }

    public void showSongs(int id, Model model) {
        Playlist playlist = playlistRepo.findById(id);
        model.addAttribute("playlistSongs", playlist.getPlaylistSongs());
        model.addAttribute("playlist", playlist);
    }

    public Set<Playlist> deletePlaylist(
             User user,
             int id
    ) {
        Playlist playlist = playlistRepo.findById(id);
        Set<Playlist> userPlaylists = user.getPlaylists();
        userPlaylists.remove(playlist);
        playlistRepo.delete(playlist);
        user.setPlaylists(userPlaylists);
        return userPlaylists;
    }
    public void deleteSongFromPlaylist(
            int id,
            int songId,
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
    }

    public void searchByArtist( String artist,
                          int id,
                         Model model) {
        model.addAttribute("songs", songService.searchByArtist(artist));
        showSongs(id, model);
    }

    public void searchByName( String name,
                               Model model,
                                int id) {
        model.addAttribute("songs", songService.searchByName(name));
        showSongs(id, model);
    }
    public void addSongToPlaylist(
             int id,
             int songId,
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
    }
}
