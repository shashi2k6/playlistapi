package com.musicshare.playlistapi.service;

import com.musicshare.playlistapi.entity.PlayList;
import com.musicshare.playlistapi.entity.Song;
import com.musicshare.playlistapi.exception.DuplicatePlayListException;
import com.musicshare.playlistapi.exception.IsNotFoundException;
import com.musicshare.playlistapi.repository.PlayListRepository;
import com.musicshare.playlistapi.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayListService {

    @Autowired
    PlayListRepository playListRepository;

    @Autowired
    SongRepository songRepository;

    public PlayList createPlayListWithName(String name) throws DuplicatePlayListException {
        PlayList playList = PlayList.builder()
                .name(name)
                .songs(new ArrayList<Song>())
                .build();
        PlayList playListFromDB = playListRepository.findByName(name);
        if (playListFromDB != null) {
            throw new DuplicatePlayListException();
        }
        return playListRepository.save(playList);
    }

    public PlayList addSongsToPlayList(String playListName, Song song) throws IsNotFoundException {
        PlayList playList = playListRepository.findByName(playListName);
        Song songFromDb = songRepository.findBySongName(song.getSongName());
        if (songFromDb == null) {
            throw new IsNotFoundException();
        } else {
            if (playList.getSongs().size() < 1) {
                List<Song> songs = new ArrayList<>();
                songs.add(songFromDb);
                playList.setSongs(songs);
            } else {
                playList.getSongs().add(songFromDb);
            }
        }
        return playListRepository.save(playList);
    }

    public PlayList deleteSongFromPlayList(String playListName, Song song) {
        PlayList playList = playListRepository.findByName(playListName);
        List<Song> songInPlayList = playList.getSongs();
        System.out.println("songList = " + songInPlayList.size());
        songInPlayList.removeIf(songDB -> songDB.getSongName().equals(song.getSongName()));
        return playListRepository.save(playList);
    }

    public List<PlayList> getAllPlayList() {
        return playListRepository.findAll();
    }

    public PlayList getSongsInPlaylist(String playListName) {
        return playListRepository.findByName(playListName);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }
}
