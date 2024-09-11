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
import org.mockito.MockitoAnnotations;
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
    private AlbumManagerServiceImpl albumManagerService;

    @Test
    @DisplayName("Returning the list of all albums")
    void getAllAlbumsTest() {

        // Arrange
        List<Artist> artists = List.of(
                new Artist(1L, "George Michael", "Singer"),
                new Artist(2L, "Pol Moria Orchestra", "Orchestra"),
                new Artist(3L, "Pink Floyd", "Band")
        );

        List<Album> albums = List.of(
                new Album(1L, "Fight", "The first solo album", 1987, artists.get(0), Genre.PROGROCK, 20, 99),
                new Album(2L, "The Best", "The best melodies", 2022, artists.get(1), Genre.CLASSICAL, 15, 88),
                new Album(3L, "The Wall", "The most famous album", 1982, artists.get(2), Genre.PROGROCK, 18, 60)
        );

        when(mockAlbumManagerRepository.findAll()).thenReturn(albums);

        // Act
        List<Album> actualResult = albumManagerService.getAllAlbums();

        // Assert
        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(albums);
    }

    @Test
    @DisplayName("Returning an album by given id")
    void getAlbumByIdTest() {

        // Arrange
        Artist artist = new Artist(1L, "George Michael", "Singer");

        Album album = new Album(1L, "Fight", "The first solo album", 1987, artist, Genre.PROGROCK, 20, 99);

        when(mockAlbumManagerRepository.findById(1L)).thenReturn(Optional.of(album));

        // Act
        Album actualResult = albumManagerService.getAlbumById(1L);

        // Assert
        assertThat(actualResult).isEqualTo(album);
    }

    @Test
    @DisplayName("Adding a new album")
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
        when(mockAlbumManagerRepository.save(updatedAlbum)).thenReturn(updatedAlbum);

        // Act
        Album actualResult = albumManagerService.updateAlbum(existingAlbum, updatedAlbum);

        // Assert
        assertThat(actualResult).isEqualTo(updatedAlbum);
    }

    @Test
    @DisplayName("Deleting the album by given id")
    void deleteAlbumByIdTest() {

        // Arrange
        Long albumId = 5L;

        when(mockAlbumManagerRepository.findById(albumId)).thenReturn(Optional.of(new Album(albumId, "Dummy", "Dummy", 2000, null, Genre.ROCK, 0, 0)));
        doNothing().when(mockAlbumManagerRepository).deleteById(albumId);

        // Act
        String actualResult = albumManagerService.deleteAlbumById(albumId);

        // Assert
        assertThat(actualResult).isEqualTo("Album with ID " + albumId + " has been deleted");
    }

    @Test
    @DisplayName("Adding a new artist")
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
    @DisplayName("Returning the list of all artists")
    void getAllArtistsTest() {

        // Arrange
        List<Artist> artists = List.of(
                new Artist(1L, "George Michael", "Singer"),
                new Artist(2L, "Pol Moria Orchestra", "Orchestra"),
                new Artist(3L, "Pink Floyd", "Band")
        );

        when(mockArtistRepository.findAll()).thenReturn(artists);

        // Act
        List<Artist> actualResult = albumManagerService.getAllArtists();

        // Assert
        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(artists);
    }

    @Test
    @DisplayName("Returning an artist by given id")
    void getArtistByIdTest() {

        // Arrange
        Artist artist = new Artist(2L, "Pol Moria Orchestra", "Orchestra");

        when(mockArtistRepository.findById(2L)).thenReturn(Optional.of(artist));

        // Act
        Artist actualResult = albumManagerService.getArtistById(2L);

        // Assert
        assertThat(actualResult).isEqualTo(artist);
    }

    @Test
    @DisplayName("Updating any fields of the given artist except the id")
    void updateArtistTest() {

        // Arrange
        Long artistId = 5L;

        Artist existingArtist = new Artist(artistId, "George Michael", "Singer");
        Artist updatedArtist = new Artist(artistId, "Pol Moria Orchestra", "Orchestra");

        when(mockArtistRepository.findById(artistId)).thenReturn(Optional.of(existingArtist));
        when(mockArtistRepository.save(updatedArtist)).thenReturn(updatedArtist);

        // Act
        Artist actualResult = albumManagerService.updateArtist(existingArtist, updatedArtist);

        // Assert
        assertThat(actualResult).isEqualTo(updatedArtist);
    }

    @Test
    @DisplayName("Deleting the artist by given id")
    void deleteArtistByIdTest() {

        // Arrange
        Long artistId = 5L;

        when(mockArtistRepository.findById(artistId)).thenReturn(Optional.of(new Artist(artistId, "Dummy", "Dummy")));
        doNothing().when(mockArtistRepository).deleteById(artistId);

        // Act
        String actualResult = albumManagerService.deleteArtistById(artistId);

        // Assert
        assertThat(actualResult).isEqualTo("Artist with ID " + artistId + " has been deleted");
    }
}