package com.musicshare.playlistapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicshare.playlistapi.entity.Song;
import com.musicshare.playlistapi.service.PlayListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SpringBootTest
@Transactional
public class PlayListControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PlayListService playListService;

    /**
     * When a playlist is created with a name
     * Then a confirmation is returned that it was successful.
     * And the playlist is empty.
     *
     * @throws Exception
     */

    @Test
    public void test_createPlayListWithName() throws Exception {

        mockMvc.perform(post("/api/v1/playlist")
                .param("name", "playlist1"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("playlist1"))
                .andExpect(jsonPath("$.songs", hasSize(0)));

    }

    /**
     * When a playlist is created without a name
     * Then a message is returned that a name is required.
     *
     * @throws Exception
     */

    @Test
    public void test_createPlayListWithoutName() throws Exception {

        mockMvc.perform(post("/api/v1/playlist")
                .param("name", ""))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("playlist name is mandatory"));
    }

    /**
     * Add the song to the existing playlist.
     *
     * @throws Exception
     */
    @Test
    public void test_addSongsToPlaylist() throws Exception {
        mockMvc.perform(post("/api/v1/playlist")
                .param("name", "playlist1"))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/playlist/song")
                .param("name", "playlist1")
                .content(objectMapper.writeValueAsString(new Song(0, "Song1"))).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("playlist1"))
                .andExpect(jsonPath("$.songs", hasSize(1)));

    }

    /**
     * Given a playlist with 2 songs
     * When a song is removed
     * Then the playlist has 1 song.
     */
    @Test
    public void test_deleteSongFromPlaylist() throws Exception {

        Song song1 = Song.builder().songName("Song1").build();
        Song song2 = Song.builder().songName("Song2").build();
        createPlayListWithTwoSongs("playlist1", song1, song2);


        mockMvc.perform(delete("/api/v1/playlist/song")
                .param("name", "playlist1")
                .content(objectMapper.writeValueAsString(song1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("playlist1"))
                .andExpect(jsonPath("$.songs", hasSize(1)));


    }

    private void createPlayListWithTwoSongs(String playlistName, Song song1, Song song2) {
        playListService.createPlayListWithName(playlistName);
        playListService.addSongsToPlayList("playlist1", song1);
        playListService.addSongsToPlayList("playlist1", song2);
    }
}
