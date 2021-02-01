## `Instructions : `

Music Share is a playlist service where users can create music lists and share them with others.

## `Acceptance Tests :`

1. Playlists are empty on creation.
2. Playlists have many songs.
3. Songs can be added to and removed from a playlist.
4. Playlists are named.
5. Playlists are unique.

### `Use case 1 :`

- [ ] When a playlist is created with a name
- [ ] Then a confirmation is returned that it was successful.
- [ ] And the playlist is empty.

### `Use case 2 :`

- [ ] When a playlist is created without a name
- [ ] Then a message is returned that a name is required.

### `Use case 3 :`

- [ ] Given an empty playlist
- [ ] When an song is added
- [ ] Then the playlist has 1 song

### `Use case 4 :`

- [ ] Given a playlist with 2 songs
- [ ] When a song is removed
- [ ] Then the playlist has 1 song.

### `Use case 5 :`

- [ ] Given a song doesn't exist
- [ ] And a playlist with 1 song
- [ ] When the song is added to the playlist
- [ ] Then the playlist still has 1 song
- [ ] And a message is returned that the song doesn't exist.

### `Heroku url :`

URL : https://playlistap.herokuapp.com/swagger-ui.html
