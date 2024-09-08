package com.northcoders.albummanagerapi.service;

import com.northcoders.albummanagerapi.data.Album;
import com.northcoders.albummanagerapi.data.Artist;
import com.northcoders.albummanagerapi.data.Genre;

import java.util.List;

public interface AlbumManagerService {

    Album addAlbum(Album Album);
    List<Album> getAllAlbums();
    Album getAlbumById(Long id);
    Album updateAlbum(Album albumFound, Album albumUpdated);
    String deleteAlbumById(Long id);

    List<Album> getAlbumByYear(int released);
    List<Album> getAlbumByGenre(Genre genre);
    Album getAlbumByTitle(String title);
    List<Album> getAlbumByArtist(Artist artist);

    // Not in scope but useful
    Artist addArtist(Artist artist);
    List<Artist> getAllArtists();
    Artist getArtistById(Long id);
    Artist updateArtist(Artist artistFound, Artist artistUpdated);
    String deleteArtistById(Long id);

}