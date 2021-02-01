## `Instructions : `

Music Share is a playlist service where users can create music lists and share them with others.

---

## `Acceptance Tests :`

1. Playlists are empty on creation.
2. Playlists have many songs.
3. Songs can be added to and removed from a playlist.
4. Playlists are named.
5. Playlists are unique.

---
### `Use case 1 :`

- [x] When a playlist is created with a name
- [x] Then a confirmation is returned that it was successful.
- [X] And the playlist is empty.

### `Use case 2 :`

- [x] When a playlist is created without a name
- [x] Then a message is returned that a name is required.

### `Use case 3 :`

- [x] Given an empty playlist
- [x] When an song is added
- [x] Then the playlist has 1 song

### `Use case 4 :`

- [x] Given a playlist with 2 songs
- [x] When a song is removed
- [x] Then the playlist has 1 song.

### `Use case 5 :`

- [x] Given a song doesn't exist
- [x] And a playlist with 1 song
- [x] When the song is added to the playlist
- [x] Then the playlist still has 1 song
- [x] And a message is returned that the song doesn't exist.

---

### `Build commands :`

- docker build . -t playlistap
- heroku git:remote -a playlistap
- Git remote -v
- heroku login
- heroku container:login
- heroku container:push web
- heroku container:release web

### `Heroku url (from Salesforce):`

URL :
https://playlistap.herokuapp.com/swagger-ui.html 

---