package com.github.zephyrquest.mymusic.commandlinerunner;

import com.github.zephyrquest.mymusic.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class ReadAndPersistAlbumsAndTrackRunner implements CommandLineRunner, Ordered {
    @Autowired
    private AlbumService albumService;

    @Override
    public void run(String... args) throws Exception {
        albumService.readAndPersistAlbums();
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
