package com.northcoders.albummanagerapi.repository;

//import org.springframework.data.repository.CrudRepository;
import com.northcoders.albummanagerapi.data.Album;
import org.springframework.stereotype.Repository;
import com.northcoders.albummanagerapi.data.Artist;
import com.northcoders.albummanagerapi.data.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
//public interface AlbumManagerRepository extends CrudRepository<Album, Long> {
public interface AlbumManagerRepository extends JpaRepository<Album, Long> {
    Optional<List<Album>> findByArtist(Artist artist);
    Optional<List<Album>> findByReleased(int released);
    Optional<List<Album>> findByGenre(Genre genre);
    Optional<Album> findByTitle(String title);
}