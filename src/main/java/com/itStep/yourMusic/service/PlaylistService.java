package com.itStep.yourMusic.service;

import com.itStep.yourMusic.domain.Playlist;
import com.itStep.yourMusic.domain.Song;
import com.itStep.yourMusic.domain.User;
import com.itStep.yourMusic.repository.PlaylistRepo;
import com.itStep.yourMusic.repository.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.util.Set;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepo playlistRepo;

    @Autowired
    private SongRepo songRepo;

    @Autowired
    SongService songService;

    public void createPlaylist(User user, String playlistName,
                               User currentUser, Model model) {
        Playlist playlist = new Playlist(playlistName, currentUser);
        if (StringUtils.isEmpty(playlistName)) {
            model.addAttribute("nameError", "Please, enter name of playlist");
        } else {
            playlistRepo.save(playlist);
        }
        model.addAttribute("playlists", user.getPlaylists());
    }


        public void showSongs(int id, Model model) {
        Playlist playlist = playlistRepo.findById(id);
        model.addAttribute("playlistSongs", playlist.getPlaylistSongs());
        model.addAttribute("playlist", playlist);
    }


    public Set<Playlist> deletePlaylist(User user, int id) {
        Playlist playlist = playlistRepo.findById(id);
        playlistRepo.delete(playlist);
        return user.getPlaylists();
    }

    public void deleteSongFromPlaylist(int id, int songId, Model model) {
        Playlist playlist = playlistRepo.findById(id);
        Set<Song> plSongs = playlist.getPlaylistSongs();
        Song song = songRepo.findById(songId);
        plSongs.remove(song);
        playlist.setPlaylistSongs(plSongs);
        playlistRepo.save(playlist);
        model.addAttribute("playlistSongs", plSongs);
        model.addAttribute("playlist", playlist);
    }

    public void searchByArtist(String artist, int id, Model model) {
        model.addAttribute("songs", songService.searchByArtist(artist));
        model.addAttribute("artist", artist);
        showSongs(id, model);
    }



    public void addSongToPlaylist(int id, int songId, Model model) {
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
