package com.northcoders.albummanagerapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AlbumManagerApiApplicationTests {

	@Test
	@DisplayName("Application context check")
	void contextLoads() {
	}

	@Test
	@DisplayName("Application check")
	void applicationStarts() {
		AlbumManagerApiApplication.main(new String[] {});
	}

}
