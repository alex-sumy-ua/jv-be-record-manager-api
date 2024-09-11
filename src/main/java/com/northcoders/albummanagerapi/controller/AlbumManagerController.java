package com.northcoders.albummanagerapi.controller;

import com.northcoders.albummanagerapi.data.Album;
import com.northcoders.albummanagerapi.data.Artist;
import com.northcoders.albummanagerapi.data.Genre;
import com.northcoders.albummanagerapi.service.AlbumManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AlbumManagerController {

    @Autowired
    AlbumManagerService albumManagerService;

    @GetMapping("/albums/artists")  // usage: http://localhost:8082/api/v1/albums/artists
    @Cacheable("albumCache2")
    public List<Artist> getAllArtists() {
        return albumManagerService.getAllArtists();
    }

    @GetMapping("/albums/artist/{id}")  // usage: http://localhost:8082/api/v1/albums/artist/2
    @Cacheable("albumCache2")
    public Artist getArtistById(@PathVariable Long id) {
        return albumManagerService.getArtistById(id);
    }

    @PostMapping("/albums/artist")  // usage: http://localhost:8082/api/v1/albums/artist
    @CacheEvict(cacheNames = "albumCache1", allEntries = true)
    public ResponseEntity<Artist> addArtist(@RequestBody Artist artist) {
        Artist newArtist = albumManagerService.addArtist(artist);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("artist", "/api/v1/albums/artist" + newArtist.getId().toString());
        return new ResponseEntity<>(newArtist, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/albums/artists/{id}")    // usage: http://localhost:8082/api/v1/albums/artists/2
    @CacheEvict(cacheNames = "albumCache1", allEntries = true)
    public ResponseEntity<Artist> updateArtistById(@PathVariable Long id, @RequestBody Artist artistUpdated) {
        Artist artistFound = albumManagerService.getArtistById(id);
        if (artistFound != null) {
            Artist updated = albumManagerService.updateArtist(artistFound, artistUpdated);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/albums/artists/{id}")    // usage: http://localhost:8082/api/v1/albums/artists/2
    @CacheEvict(cacheNames = "albumCache1", allEntries = true)
    public String deleteArtistById(@PathVariable ("id") Long id) {
        return albumManagerService.deleteArtistById(id);
    }

    @GetMapping("/albums")  // usage: http://localhost:8082/api/v1/albums
    @Cacheable("albumCache2")
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = albumManagerService.getAllAlbums();
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/album/{id}")  // usage: http://localhost:8082/api/v1/album/2
    @Cacheable("albumCache2")
    public ResponseEntity<Album> getAlbumById(@PathVariable("id") Long id) {
        Album album = albumManagerService.getAlbumById(id);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    @PostMapping("/album")  // usage: http://localhost:8082/api/v1/album
    @CacheEvict(cacheNames = "albumCache1", allEntries = true)
    public ResponseEntity<Album> addAlbum(@RequestBody Album album) {
        if (album.getArtist() == null || album.getArtist().getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Artist is required
        }

        // Fetch the artist from the database and set it in the album
        Artist artist = albumManagerService.getArtistById(album.getArtist().getId());
        if (artist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        album.setArtist(artist);
        Album newAlbum = albumManagerService.addAlbum(album);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("album", "/api/v1/album" + newAlbum.getId().toString());
        return new ResponseEntity<>(newAlbum, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/albums/{id}")    // usage: http://localhost:8082/api/v1/albums/2
    @CacheEvict(cacheNames = "albumCache1", allEntries = true)
    public ResponseEntity<Album> updateAlbumById(@PathVariable Long id, @RequestBody Album albumUpdated) {
        Album albumFound = albumManagerService.getAlbumById(id);
        if (albumFound != null) {
            Album updated = albumManagerService.updateAlbum(albumFound, albumUpdated);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/albums/{id}")    // usage: http://localhost:8082/api/v1/albums/2
    @CacheEvict(cacheNames = "albumCache1", allEntries = true)
    public String deleteAlbumById(@PathVariable ("id") Long id) {
        return albumManagerService.deleteAlbumById(id);
    }

    @GetMapping("/albums/artist")  // usage: http://localhost:8082/api/v1/albums/artist?artistId=2
    @Cacheable("albumCache2")
    public ResponseEntity<List<Album>> getAlbumByArtist(@RequestParam Long artistId) {
        Artist artist = albumManagerService.getArtistById(artistId);
        List<Album> albums = albumManagerService.getAlbumByArtist(artist);
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/albums/year")  // usage: http://localhost:8082/api/v1/albums/year?released=1982
    @Cacheable("albumCache2")
    public ResponseEntity<List<Album>> getAlbumByYear(@RequestParam int released) {
        List<Album> albums = albumManagerService.getAlbumByYear(released);
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/albums/genre")  // usage: http://localhost:8082/api/v1/albums/genre?genre=ROCK
    @Cacheable("albumCache2")
    public ResponseEntity<List<Album>> getAlbumByGenre(@RequestParam Genre genre) {
        List<Album> albums = albumManagerService.getAlbumByGenre(genre);
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/albums/title")  // usage: http://localhost:8082/api/v1/albums/title?title=The Wall
    @Cacheable("albumCache2")
    public ResponseEntity<Album> getAlbumByTitle(@RequestParam String title) {
        Album album = albumManagerService.getAlbumByTitle(title);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

}
