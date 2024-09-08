package com.northcoders.albummanagerapi.controller;

import com.northcoders.albummanagerapi.data.Album;
import com.northcoders.albummanagerapi.data.Artist;
import com.northcoders.albummanagerapi.service.AlbumManagerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Artist> getAllArtists() {
        return albumManagerService.getAllArtists();
    }

    @GetMapping("/albums/artist/{id}")  // usage: http://localhost:8082/api/v1/albums/artist/2
    public Artist getArtistById(@PathVariable Long id) {
        return albumManagerService.getArtistById(id);
    }

    @PostMapping("/albums/artist")  // usage: http://localhost:8082/api/v1/albums/artist
    public ResponseEntity<Artist> addArtist(@RequestBody Artist artist) {
        Artist newArtist = albumManagerService.addArtist(artist);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("artist", "/api/v1/albums/artist" + newArtist.getId().toString());
        return new ResponseEntity<>(newArtist, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/albums/artists/{id}")    // usage: http://localhost:8082/api/v1/albums/artists/2
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
    public String deleteArtistById(@PathVariable ("id") Long id) {
        return albumManagerService.deleteArtistById(id);
    }

    @GetMapping("/albums")  // usage: http://localhost:8082/api/v1/albums
    public List<Album> getAllAlbums() {
        return albumManagerService.getAllAlbums();
    }

    @GetMapping("/album/{id}")  // usage: http://localhost:8082/api/v1/album/2
    public Album getAlbumById(@PathVariable Long id) {
        return albumManagerService.getAlbumById(id);
    }

    @PostMapping("/album")  // usage: http://localhost:8082/api/v1/album
    public ResponseEntity<Album> addAlbum(@RequestBody Album album) {
        Album newAlbum = albumManagerService.addAlbum(album);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("album", "/api/v1/album" + newAlbum.getId().toString());
        return new ResponseEntity<>(newAlbum, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/albums/{id}")    // usage: http://localhost:8082/api/v1/albums/2
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
    public String deleteAlbumById(@PathVariable ("id") Long id) {
        return albumManagerService.deleteAlbumById(id);
    }

}
