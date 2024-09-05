package com.northcoders.albummanagerapi.service;

import com.northcoders.albummanagerapi.data.Album;
import com.northcoders.albummanagerapi.data.Artist;
import com.northcoders.albummanagerapi.data.Genre;

import java.util.List;

public interface AlbumManagerService {

    List<Album> getAllAlbums();
    Album getAlbumById(Long id);
    List<Album> getAlbumByArtist(Artist artist);
    List<Album> getAlbumByYear(int released);
    List<Album> getAlbumByGenre(Genre genre);

    Album getAlbumByTitle(String title);
    Album addAlbum(Album Album);
    Album updateAlbum(Album albumFound, Album albumUpdated);
    String deleteAlbumById(Long id);

    // Not in scope but useful
    String deleteAlbum(Album album);

    Artist insertArtist(Artist artist);
    Artist getArtistById(Long id);
    String deleteArtistById(Long id);

}