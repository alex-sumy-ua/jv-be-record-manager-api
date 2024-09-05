package com.northcoders.albummanagerapi.repository;

import com.northcoders.albummanagerapi.data.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
}
