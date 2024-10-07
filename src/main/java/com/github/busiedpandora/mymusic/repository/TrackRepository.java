package com.github.busiedpandora.mymusic.repository;

import com.github.busiedpandora.mymusic.model.Album;
import com.github.busiedpandora.mymusic.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
    Track getTrackByAlbumAndTitleIgnoreCase(Album album, String trackTitle);
}
