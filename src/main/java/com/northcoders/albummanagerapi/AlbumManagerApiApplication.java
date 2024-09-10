package com.northcoders.albummanagerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AlbumManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumManagerApiApplication.class, args);
	}

}
