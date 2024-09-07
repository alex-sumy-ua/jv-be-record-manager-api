package com.northcoders.albummanagerapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.albummanagerapi.data.Album;
import com.northcoders.albummanagerapi.data.Artist;
import com.northcoders.albummanagerapi.data.Genre;
import com.northcoders.albummanagerapi.service.AlbumManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
class AlbumManagerControllerTests {

    @Mock
    private AlbumManagerService mockAlbumManagerService;

    @InjectMocks
    private AlbumManagerController albumManagerController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(albumManagerController).build();
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Returns all albums from the DB")
    void getAllAlbumsReturnsAlbums() throws Exception {

        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist(1L, "George Michael", "Singer"));
        artists.add(new Artist(2L, "Pol Moria Orchestra", "Orchestra"));
        artists.add(new Artist(3L, "Pink Floyd", "Band"));

        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L, "Fight", "The first solo album", 1987, artists.getFirst(), Genre.PROGROCK, 20, 99));
        albums.add(new Album(2L, "The Best", "The best melodies", 2022, artists.get(1), Genre.CLASSICAL, 15, 88));
        albums.add(new Album(3L, "The Wall", "The most famous album", 1982, artists.getLast(), Genre.PROGROCK, 18, 60));

        when(mockAlbumManagerService.getAllAlbums()).thenReturn(albums);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/albums"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Fight"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("The first solo album"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].released").value(1987))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist").value(artists.getFirst()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genre").value(Genre.PROGROCK.descriptor))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].inStock").value(99))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("The Best"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value("The best melodies"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].released").value(2022))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].artist").value(artists.get(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].genre").value(Genre.CLASSICAL.descriptor))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(15))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].inStock").value(88))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].title").value("The Wall"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].description").value("The most famous album"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].released").value(1982))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].artist").value(artists.getLast()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].genre").value(Genre.PROGROCK.descriptor))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].price").value(18))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].inStock").value(60));
    }

    @Test
    @DisplayName("Returns an album by given id from the DB")
    void getAlbumByIdReturnsAlbum() throws Exception {

        Artist artist = new Artist(2L, "George Michael", "Singer");

        Album album = new Album(5L, "Fight", "The first solo album", 1987, artist, Genre.PROGROCK, 20, 99);

        when(mockAlbumManagerService.getAlbumById(5L)).thenReturn(album);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/album/5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Fight"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("The first solo album"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.released").value(1987))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value(artist))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value(Genre.PROGROCK.descriptor))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.inStock").value(99));
    }

    @Test
    @DisplayName("Adding an album check")
    void testPostMappingAddAlbum() throws Exception {

        Artist artist = new Artist(2L, "George Michael", "Singer");

        Album album = new Album(5L, "Fight", "The first solo album", 1987, artist, Genre.PROGROCK, 20, 99);

        when(mockAlbumManagerService.addArtist(artist)).thenReturn(artist);
        when(mockAlbumManagerService.addAlbum(album)).thenReturn(album);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.post("/api/v1/album")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(album)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Fight"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("The first solo album"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.released").value(1987))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value(artist))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value(Genre.PROGROCK.descriptor))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.inStock").value(99));

        verify(mockAlbumManagerService, times(1)).addAlbum(any(Album.class));
    }

    @Test
    @DisplayName("Updating any field of the album expect id")
    void testUpdateAlbumById() throws Exception {
        Long albumId = 5L;

        Artist artist1 = new Artist(1L, "George Michael", "Singer");
        Artist artist2 = new Artist(2L, "Pol Moria Orchestra", "Orchestra");

        Album existingAlbum = new Album(albumId, "Fight", "The first solo album", 1987, artist1, Genre.PROGROCK, 20, 99);
        Album updatedAlbum = new Album(albumId, "The Best", "The best melodies", 2022, artist2, Genre.CLASSICAL, 15, 88);

        when(mockAlbumManagerService.getAlbumById(albumId)).thenReturn(existingAlbum);
        when(mockAlbumManagerService.updateAlbum(existingAlbum, updatedAlbum)).thenReturn(updatedAlbum);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.put("/api/v1/albums/5")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(updatedAlbum)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(albumId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("The Best"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("The best melodies"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.released").value(2022))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value(artist2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value(Genre.CLASSICAL.descriptor))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(15))
                .andExpect(MockMvcResultMatchers.jsonPath("$.inStock").value(88));

        verify(mockAlbumManagerService, times(1)).getAlbumById(albumId);
        verify(mockAlbumManagerService, times(1)).updateAlbum(existingAlbum, updatedAlbum);
    }

    @Test
    @DisplayName("Adding an artist check")
    void testPostMappingAddArtist() throws Exception {

        Artist artist = new Artist(2L, "George Michael", "Singer");

        when(mockAlbumManagerService.addArtist(artist)).thenReturn(artist);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/albums/artist")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(artist)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("George Michael"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("Singer"));

        verify(mockAlbumManagerService, times(1)).addArtist(any(Artist.class));
    }

    @Test
    @DisplayName("Deleting an album by its id check")
    public void testDeleteBookById() throws Exception {

        Long albumId = 2L;

        Artist artist = new Artist(1L, "George Michael", "Singer");

        Album album = new Album(2L, "Fight", "The first solo album", 1987, artist, Genre.PROGROCK, 20, 99);

        when(mockAlbumManagerService.addArtist(artist)).thenReturn(artist);
        when(mockAlbumManagerService.addAlbum(album)).thenReturn(album);
        when(mockAlbumManagerService.deleteAlbumById(2L)).thenReturn("Album with ID " + albumId + " has been deleted");

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.delete("/api/v1/albums/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Album with ID " + albumId + " has been deleted"));

        verify(mockAlbumManagerService, times(1)).deleteAlbumById(2L);
    }


}