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

    @GetMapping("/albums")
    public List<Album> getAllAlbums() {
        return albumManagerService.getAllAlbums();
    }

    @GetMapping("/album/{id}")
    public Album getAlbumById(@PathVariable Long id) {
        return albumManagerService.getAlbumById(id);
    }

    @PostMapping("/album")
    public ResponseEntity<Album> addAlbum(@RequestBody Album album) {
        Album newAlbum = albumManagerService.addAlbum(album);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("album", "/api/v1/album" + newAlbum.getId().toString());
        return new ResponseEntity<>(newAlbum, httpHeaders, HttpStatus.CREATED);
    }
}
