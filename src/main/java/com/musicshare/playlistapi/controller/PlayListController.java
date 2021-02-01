package com.musicshare.playlistapi.controller;

import com.musicshare.playlistapi.entity.PlayList;
import com.musicshare.playlistapi.entity.Song;
import com.musicshare.playlistapi.exception.DuplicatePlayListException;
import com.musicshare.playlistapi.exception.IsNotFoundException;
import com.musicshare.playlistapi.exception.NameRequiredException;
import com.musicshare.playlistapi.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/playlist")
public class PlayListController {

    @Autowired
    private PlayListService playListService;

    /**
     *
     * @param name
     * @return
     * @throws NameRequiredException
     * @throws DuplicatePlayListException
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PlayList createPlayListWithName(@RequestParam String name) throws NameRequiredException, DuplicatePlayListException {
        if (null == name || name.trim().isEmpty()) {
            throw new NameRequiredException();
        }
        return playListService.createPlayListWithName(name);
    }

    /**
     *
     * @param playlistName
     * @param song
     * @return
     * @throws IsNotFoundException
     */
    @PostMapping("{playlistName}/song")
    public PlayList addSongsToPlayList(@PathVariable String playlistName, @RequestBody Song song) throws IsNotFoundException {
        return playListService.addSongsToPlayList(playlistName, song);
    }

    /**
     *
     * @param playlistName
     * @param song
     * @return
     */
    @DeleteMapping("{playlistName}/song")
    public PlayList deleteSongFromPlayList(@PathVariable String playlistName, @RequestBody Song song) {
        return playListService.deleteSongFromPlayList(playlistName, song);
    }

    /**
     *
     * @return
     * @throws IsNotFoundException
     */
    @GetMapping("/")
    public List<PlayList> getAllPlayList() throws IsNotFoundException {
        return playListService.getAllPlayList();
    }

    /**
     *
     * @param playlistName
     * @return
     * @throws IsNotFoundException
     */
    @GetMapping("{playlistName}")
    public PlayList getSongsInPlaylist(@PathVariable String playlistName) throws IsNotFoundException {
        return playListService.getSongsInPlaylist(playlistName);
    }

    /**
     *
     * @return
     * @throws IsNotFoundException
     */
    @GetMapping("/songlist")
    public List<Song> getAllSongs() throws IsNotFoundException {
        return playListService.getAllSongs();
    }
}
