package com.northcoders.albummanagerapi.repository;

import com.northcoders.albummanagerapi.data.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumManagerRepository extends CrudRepository<Album, Long> {
}
