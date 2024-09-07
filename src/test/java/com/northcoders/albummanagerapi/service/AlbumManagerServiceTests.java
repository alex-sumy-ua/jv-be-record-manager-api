package com.northcoders.albummanagerapi.service;

import com.northcoders.albummanagerapi.data.Album;
import com.northcoders.albummanagerapi.data.Artist;
import com.northcoders.albummanagerapi.data.Genre;
import com.northcoders.albummanagerapi.repository.AlbumManagerRepository;
import com.northcoders.albummanagerapi.repository.ArtistRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
class AlbumManagerServiceTests {

    @Mock
    private AlbumManagerRepository mockAlbumManagerRepository;

    @Mock
    private ArtistRepository mockArtistRepository;

    @InjectMocks
//    private AlbumManagerService albumManagerService = new AlbumManagerServiceImpl();
    private AlbumManagerServiceImpl albumManagerService;

    @Test
    @DisplayName("Returning the list of all albums")
    void getAllAlbumsTest() {

        // Arrange
        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist(1L, "George Michael", "Singer"));
        artists.add(new Artist(2L, "Pol Moria Orchestra", "Orchestra"));
        artists.add(new Artist(3L, "Pink Floyd", "Band"));

        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L, "Fight", "The first solo album", 1987, artists.getFirst(), Genre.PROGROCK, 20, 99));
        albums.add(new Album(2L, "The Best", "The best melodies", 2022, artists.get(1), Genre.CLASSICAL, 15, 88));
        albums.add(new Album(3L, "The Wall", "The most famous album", 1982, artists.getLast(), Genre.PROGROCK, 18, 60));

        when(mockAlbumManagerRepository.findAll()).thenReturn(albums);

        // Act
        List<Album> actualResult = albumManagerService.getAllAlbums();

        // Assert
        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(albums);
    }

    @Test
    @DisplayName("Returning the album by given id")
    void getAlbumByIdTest() {

        // Arrange
        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist(1L, "George Michael", "Singer"));
        artists.add(new Artist(2L, "Pol Moria Orchestra", "Orchestra"));
        artists.add(new Artist(3L, "Pink Floyd", "Band"));

        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L, "Fight", "The first solo album", 1987, artists.getFirst(), Genre.PROGROCK, 20, 99));
        albums.add(new Album(2L, "The Best", "The best melodies", 2022, artists.get(1), Genre.CLASSICAL, 15, 88));
        albums.add(new Album(3L, "The Wall", "The most famous album", 1982, artists.getLast(), Genre.PROGROCK, 18, 60));

        Album album = albums.get(1);

        when(mockAlbumManagerRepository.findById(2L)).thenReturn(Optional.ofNullable(album));

        // Act
        Album actualResult = albumManagerService.getAlbumById(2L);

        // Assert
        assertThat(actualResult).isEqualTo(album);
    }

    @Test
    @DisplayName("Adding new artist")
    void addArtistTest() {

        // Arrange
        Artist artist = new Artist(1L, "George Michael", "Singer");

        when(mockArtistRepository.save(artist)).thenReturn(artist);

        // Act
        Artist actualResult = albumManagerService.addArtist(artist);

        // Assert
        assertThat(actualResult).isEqualTo(artist);
    }

    @Test
    @DisplayName("Adding new album")
    void addAlbumTest() {

        // Arrange
        Artist artist = new Artist(1L, "George Michael", "Singer");
        Album album = new Album(1L, "Fight", "The first solo album", 1987, artist, Genre.PROGROCK, 20, 99);

        when(mockAlbumManagerRepository.save(album)).thenReturn(album);

        // Act
        Album actualResult = albumManagerService.addAlbum(album);

        // Assert
        assertThat(actualResult).isEqualTo(album);
    }

    @Test
    @DisplayName("Updating any fields of the given album except the id")
    void updateAlbumTest() {

        // Arrange
        Long albumId = 5L;

        Artist artist1 = new Artist(1L, "George Michael", "Singer");
        Artist artist2 = new Artist(2L, "Pol Moria Orchestra", "Orchestra");

        Album existingAlbum = new Album(albumId, "Fight", "The first solo album", 1987, artist1, Genre.PROGROCK, 20, 99);
        Album updatedAlbum = new Album(albumId, "The Best", "The best melodies", 2022, artist2, Genre.CLASSICAL, 15, 88);


        when(mockAlbumManagerRepository.findById(albumId)).thenReturn(Optional.of(existingAlbum));
        when(mockAlbumManagerRepository.save(existingAlbum)).thenReturn(updatedAlbum);

        // Act
        Album actualResult = albumManagerService.updateAlbum(existingAlbum, updatedAlbum);

        // Assert
        assertThat(actualResult).isEqualTo(updatedAlbum);

    }
}