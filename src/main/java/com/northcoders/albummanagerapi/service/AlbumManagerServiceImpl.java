package com.northcoders.albummanagerapi.service;

import com.northcoders.albummanagerapi.data.Album;
import com.northcoders.albummanagerapi.data.Artist;
import com.northcoders.albummanagerapi.data.Genre;
import com.northcoders.albummanagerapi.exception.ItemCanNotBeProceededException;
import com.northcoders.albummanagerapi.exception.ItemNotFoundException;
import com.northcoders.albummanagerapi.repository.AlbumManagerRepository;
import com.northcoders.albummanagerapi.repository.ArtistRepository;
import lombok.SneakyThrows;
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
//        List<Album> albums = new ArrayList<>();
//        albumManagerRepository.findAll().forEach(albums::add);
//        return albums;
        return new ArrayList<>(albumManagerRepository.findAll());
    }

    @Override
    public List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>();
        artistRepository.findAll().forEach(artists::add);
        return artists;
    }

    @Override
    public Album getAlbumById(Long id) {
        return albumManagerRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("The album with ID %s cannot be found.", id)));
    }

    @Override
    public Album addAlbum(Album album) {
        return albumManagerRepository.save(album);
    }

    @Override
    public Album updateAlbum(Album albumFound, Album albumUpdated) {
        albumFound.setTitle(albumUpdated.getTitle());
        albumFound.setDescription(albumUpdated.getDescription());
        albumFound.setReleased(albumUpdated.getReleased());
        albumFound.setArtist(albumUpdated.getArtist());
        albumFound.setGenre(albumUpdated.getGenre());
        albumFound.setPrice(albumUpdated.getPrice());
        albumFound.setInStock(albumUpdated.getInStock());
        return albumManagerRepository.save(albumFound);
    }

    @SneakyThrows
    @Override
    public String deleteAlbumById(Long id) {
        Album album = getAlbumById(id);
        albumManagerRepository.delete(album);
        return String.format("Album with ID %s has been deleted", id);
    }

    @Override
    public Artist addArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public Artist getArtistById(Long id) {
        return artistRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("The artist with ID %s cannot be found.", id)));
    }

    @Override
    public Artist updateArtist(Artist artistFound, Artist artistUpdated) {
        artistFound.setName(artistUpdated.getName());
        artistFound.setRole(artistUpdated.getRole());
        return artistRepository.save(artistFound);
    }

    @Override
    public String deleteArtistById(Long id) {
        try {
            Artist artist = getArtistById(id);
            artistRepository.delete(artist);
        } catch (RuntimeException e) {
            throw new ItemCanNotBeProceededException("The artist with ID " + id + " cannot be proceeded because is in use.");
        }
        return String.format("Artist with ID %s has been deleted", id);
    }

    @Override
    public List<Album> getAlbumByArtist(Artist artist) {
        return albumManagerRepository.findByArtist(artist).orElseThrow(() ->
                new ItemNotFoundException(String.format("The album with artist %s cannot be found.", artist.getName())));

    }

    @Override
    public List<Album> getAlbumByYear(int released) {
        return albumManagerRepository.findByReleased(released).orElseThrow(() ->
                new ItemNotFoundException(String.format("The album with release year %s cannot be found.", released)));

    }

    @Override
    public List<Album> getAlbumByGenre(Genre genre) {
        return albumManagerRepository.findByGenre(genre).orElseThrow(() ->
                new ItemNotFoundException(String.format("The album with genre %s cannot be found.", genre.descriptor)));

    }

    @Override
    public Album getAlbumByTitle(String title) {
        return albumManagerRepository.findByTitle(title).orElseThrow(() ->
                new ItemNotFoundException(String.format("The album with title %s cannot be found.", title)));
    }

}
