package com.northcoders.albummanagerapi.service;

import com.northcoders.albummanagerapi.data.Album;
import com.northcoders.albummanagerapi.data.Artist;
import com.northcoders.albummanagerapi.data.Genre;
import com.northcoders.albummanagerapi.repository.AlbumManagerRepository;
import com.northcoders.albummanagerapi.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumManagerServiceImpl implements AlbumManagerService {

    @Autowired
    private AlbumManagerRepository albumManagerRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumManagerRepository.findAll().forEach(albums::add);
        return albums;
    }

    @Override
    public Album getAlbumById(Long id) {
        return null;
    }

    @Override
    public List<Album> getAlbumByArtist(Artist artist) {
        return List.of();
    }

    @Override
    public List<Album> getAlbumByYear(int released) {
        return List.of();
    }

    @Override
    public List<Album> getAlbumByGenre(Genre genre) {
        return List.of();
    }

    @Override
    public Album getAlbumByTitle(String title) {
        return null;
    }

    @Override
    public Album addAlbum(Album Album) {
        return null;
    }

    @Override
    public Album updateAlbum(Album albumFound, Album albumUpdated) {
        return null;
    }

    @Override
    public String deleteAlbumById(Long id) {
        return "";
    }

    @Override
    public String deleteAlbum(Album album) {
        return "";
    }

    @Override
    public Artist insertArtist(Artist artist) {
        return null;
    }

    @Override
    public Artist getArtistById(Long id) {
        return null;
    }

    @Override
    public String deleteArtistById(Long id) {
        return "";
    }
}
