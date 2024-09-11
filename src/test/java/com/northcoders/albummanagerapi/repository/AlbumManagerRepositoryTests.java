package com.northcoders.albummanagerapi.repository;

import com.northcoders.albummanagerapi.data.Album;
import com.northcoders.albummanagerapi.data.Artist;
import com.northcoders.albummanagerapi.data.Genre;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Use the actual database or omit for in-memory DB
@Rollback(false) // To persist changes (omit for auto-rollback in each test)
class AlbumManagerRepositoryTests {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AlbumManagerRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Test
    @DisplayName("Test getting all artists")
    void testGetAllArtists() {

        Artist artist1 = new Artist(1L, "George Michael", "Singer");
        Artist artist2 = new Artist(2L, "Pol Moria Orchestra", "Orchestra");
        Artist artist3 = new Artist(3L, "Pink Floyd", "Band");
        artistRepository.save(artist1);
        artistRepository.save(artist2);
        artistRepository.save(artist3);

        Iterable<Artist> artists = artistRepository.findAll();

        assertThat(artists).isNotNull();
        assertThat(artists).hasSize(3);
    }

    @Test
    @DisplayName("Test adding a new artist")
    void testAddNewArtist() {
        Artist artist = new Artist(1L, "Pink Floyd", "Band");

        Artist savedArtist = artistRepository.save(artist);

        assertThat(savedArtist).isNotNull();
        assertThat(savedArtist.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test finding an artist by ID")
    void testFindArtistById() {

        Artist newArtist1 = new Artist();
        newArtist1.setName("George Michael");
        newArtist1.setRole("Singer");
        Artist savedArtist1 = artistRepository.save(newArtist1);

        Artist newArtist2 = new Artist();
        newArtist2.setName("Pink Floyd");
        newArtist2.setRole("Band");
        Artist savedArtist2 = artistRepository.save(newArtist2);

        Long artistId1 = savedArtist1.getId();
        Optional<Artist> artistOpt1 = artistRepository.findById(artistId1);
        assertThat(artistOpt1).isPresent();
        Artist artist1 = artistOpt1.get();
        assertThat(artist1.getName()).isEqualTo("George Michael");
        assertThat(artist1.getRole()).isEqualTo("Singer");

        Long artistId2 = savedArtist2.getId();
        Optional<Artist> artistOpt2 = artistRepository.findById(artistId2);
        assertThat(artistOpt2).isPresent();
        Artist artist2 = artistOpt2.get();
        assertThat(artist2.getName()).isEqualTo("Pink Floyd");
        assertThat(artist2.getRole()).isEqualTo("Band");
    }

    @Test
    @DisplayName("Test updating an artist")
    void testUpdateArtist() {

        Artist newArtist = new Artist();
        newArtist.setName("Test Artist");
        newArtist.setRole("Drummer");

        Artist savedArtist = artistRepository.save(newArtist);

        Long artistId = savedArtist.getId();
        Optional<Artist> artistOpt = artistRepository.findById(artistId);

        assertThat(artistOpt).isPresent();
        Artist artist = artistOpt.get();
        artist.setName("Test Artist 2");

        Artist updatedArtist = artistRepository.save(artist);
        assertThat(updatedArtist.getName()).isEqualTo("Test Artist 2");
    }

    @Test
    @DisplayName("Test deleting an artist")
    void testDeleteArtist() {
        Long artistId = 2L; // Assuming this album exists in the DB
        artistRepository.deleteById(artistId);

        Optional<Artist> artistOpt = artistRepository.findById(artistId);
        assertThat(artistOpt).isEmpty();
    }

    @Test
    @DisplayName("Test getting all albums")
    void testGetAllAlbums() {
        // Prepare test data
        Artist artist1 = new Artist();
        artist1.setName("George Michael");
        artist1.setRole("Singer");

        Artist artist2 = new Artist();
        artist2.setName("Pol Moria Orchestra");
        artist2.setRole("Orchestra");

        Artist artist3 = new Artist();
        artist3.setName("Pink Floyd");
        artist3.setRole("Band");

        artistRepository.saveAll(List.of(artist1, artist2, artist3));

        Album album1 = new Album();
        album1.setTitle("Fight");
        album1.setDescription("The first solo album");
        album1.setReleased(1987);
        album1.setArtist(artist1);
        album1.setGenre(Genre.PROGROCK);
        album1.setPrice(20);
        album1.setInStock(99);

        Album album2 = new Album();
        album2.setTitle("The Best");
        album2.setDescription("The best melodies");
        album2.setReleased(2022);
        album2.setArtist(artist2);
        album2.setGenre(Genre.CLASSICAL);
        album2.setPrice(15);
        album2.setInStock(88);

        Album album3 = new Album();
        album3.setTitle("The Wall");
        album3.setDescription("The most famous album");
        album3.setReleased(1982);
        album3.setArtist(artist3);
        album3.setGenre(Genre.PROGROCK);
        album3.setPrice(18);
        album3.setInStock(60);

        albumRepository.saveAll(List.of(album1, album2, album3));

        // Fetch all albums
        List<Album> albums = albumRepository.findAll();

        // Assertions
        assertThat(albums).isNotNull();
        assertThat(albums).hasSize(3);
        assertThat(albums).extracting("title").containsExactlyInAnyOrder("Fight", "The Best", "The Wall");
    }

    @Test
    @DisplayName("Test adding a new album")
    void testAddNewAlbum() {
        // Prepare test data
        Artist artist = new Artist();
        artist.setName("Pink Floyd");
        artist.setRole("Band");

        // Save artist
        Artist savedArtist = artistRepository.save(artist);

        Album album = new Album();
        album.setTitle("The Wall");
        album.setDescription("The most famous album");
        album.setReleased(1982);
        album.setArtist(savedArtist);
        album.setGenre(Genre.PROGROCK);
        album.setPrice(25);
        album.setInStock(50);

        // Save album
        Album savedAlbum = albumRepository.save(album);

        // Assertions
        assertThat(savedArtist).isNotNull();
        assertThat(savedArtist.getId()).isNotNull();  // Check if ID is generated
        assertThat(savedAlbum).isNotNull();
        assertThat(savedAlbum.getId()).isNotNull();  // Check if ID is generated
        assertThat(savedAlbum.getTitle()).isEqualTo("The Wall");
        assertThat(savedAlbum.getArtist()).isEqualTo(savedArtist);
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
        Album savedAlbum = albumRepository.save(newAlbum);

        Long albumId = savedAlbum.getId();
        Optional<Album> albumOpt = albumRepository.findById(albumId);

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

        Album savedAlbum = albumRepository.save(newAlbum);

        Long albumId = savedAlbum.getId();
        Optional<Album> albumOpt = albumRepository.findById(albumId);

        assertThat(albumOpt).isPresent();
        Album album = albumOpt.get();
        album.setPrice(30);

        Album updatedAlbum = albumRepository.save(album);
        assertThat(updatedAlbum.getPrice()).isEqualTo(30);
    }

    @Test
    @DisplayName("Test deleting an album")
    void testDeleteAlbum() {
        Long albumId = 2L; // Assuming this album exists in the DB
        albumRepository.deleteById(albumId);

        Optional<Album> albumOpt = albumRepository.findById(albumId);
        assertThat(albumOpt).isEmpty();
    }
}
