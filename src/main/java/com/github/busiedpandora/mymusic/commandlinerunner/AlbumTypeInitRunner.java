package com.github.busiedpandora.mymusic.commandlinerunner;

import com.github.busiedpandora.mymusic.repository.AlbumTypeRepository;
import com.github.busiedpandora.mymusic.model.AlbumType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlbumTypeInitRunner implements CommandLineRunner, Ordered {
    @Autowired
    private AlbumTypeRepository albumTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        AlbumType studio = new AlbumType(AlbumType.STUDIO_ID, AlbumType.Value.STUDIO.toString());
        AlbumType remix = new AlbumType(AlbumType.REMIX_ID, AlbumType.Value.REMIX.toString());
        AlbumType live = new AlbumType(AlbumType.LIVE_ID, AlbumType.Value.LIVE.toString());
        AlbumType collection = new AlbumType(AlbumType.COLLECTION_ID, AlbumType.Value.COLLECTION.toString());

        albumTypeRepository.saveAll(List.of(studio, remix, live, collection));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
