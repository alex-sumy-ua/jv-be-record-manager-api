package com.northcoders.albummanagerapi.repository;

import com.northcoders.albummanagerapi.data.Album;
import com.northcoders.albummanagerapi.data.Artist;
import com.northcoders.albummanagerapi.data.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Use the actual database or omit for in-memory DB
@Rollback(false) // To persist changes (omit for auto-rollback in each test)
class AlbumManagerRepositoryTests {

    @Autowired
    private AlbumManagerRepository albumManagerRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Test
    @DisplayName("Test adding a new artist")
    void testAddNewArtist() {
        Artist artist = new Artist(1L, "Pink Floyd", "Band");

        Artist savedArtist = artistRepository.save(artist);

        assertThat(savedArtist).isNotNull();
        assertThat(savedArtist.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test adding a new album")
    void testAddNewAlbum() {
        Artist artist = new Artist(1L, "Pink Floyd", "Band");
        Album album = new Album(1L, "The Wall", "The most famous album", 1982, artist, Genre.PROGROCK, 25, 50);

        Artist savedArtist = artistRepository.save(artist);
        Album savedAlbum = albumManagerRepository.save(album);

        assertThat(savedArtist).isNotNull();
        assertThat(savedArtist.getId()).isGreaterThan(0);
        assertThat(savedAlbum).isNotNull();
        assertThat(savedAlbum.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test finding an album by ID")
    void testFindAlbumById() {
        Artist newArtist = new Artist();
        newArtist.setName("Pink Floyd");
        newArtist.setRole("Band");
        Artist savedArtist = artistRepository.save(newArtist);

        Album newAlbum = new Album();
        newAlbum.setTitle("The Wall");
        newAlbum.setDescription("The most famous album");
        newAlbum.setArtist(savedArtist);
        newAlbum.setPrice(25);
        newAlbum.setReleased(1979);
        newAlbum.setInStock(100);
        newAlbum.setGenre(Genre.PROGROCK);
        Album savedAlbum = albumManagerRepository.save(newAlbum);

        Long albumId = savedAlbum.getId();
        Optional<Album> albumOpt = albumManagerRepository.findById(albumId);

        assertThat(albumOpt).isPresent();
        Album album = albumOpt.get();
        assertThat(album.getTitle()).isEqualTo("The Wall");
    }

    @Test
    @DisplayName("Test updating an album")
    void testUpdateAlbum() {
        // Setup: Add an album to the repository
        Artist newArtist = new Artist();
        newArtist.setName("Test Artist");
        newArtist.setRole("Drummer");

        Artist savedArtist = artistRepository.save(newArtist);

        Album newAlbum = new Album();
        newAlbum.setTitle("Test Album");
        newAlbum.setDescription("Test Description");
        newAlbum.setArtist(newArtist);
        newAlbum.setPrice(20);
        newAlbum.setReleased(1995);
        newAlbum.setInStock(50);
        newAlbum.setGenre(Genre.ROCK);

        Album savedAlbum = albumManagerRepository.save(newAlbum);

        // Test: Update the album's price
        Long albumId = savedAlbum.getId();
        Optional<Album> albumOpt = albumManagerRepository.findById(albumId);

        assertThat(albumOpt).isPresent();
        Album album = albumOpt.get();
        album.setPrice(30);

        Album updatedAlbum = albumManagerRepository.save(album);
        assertThat(updatedAlbum.getPrice()).isEqualTo(30);
    }

    @Test
    @DisplayName("Test deleting an album")
    void testDeleteAlbum() {
        Long albumId = 2L; // Assuming this album exists in the DB
        albumManagerRepository.deleteById(albumId);

        Optional<Album> albumOpt = albumManagerRepository.findById(albumId);
        assertThat(albumOpt).isEmpty();
    }
}
