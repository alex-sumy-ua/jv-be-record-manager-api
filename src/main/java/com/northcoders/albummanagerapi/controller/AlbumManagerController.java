package com.northcoders.albummanagerapi.controller;

import com.northcoders.albummanagerapi.data.Album;
import com.northcoders.albummanagerapi.service.AlbumManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
