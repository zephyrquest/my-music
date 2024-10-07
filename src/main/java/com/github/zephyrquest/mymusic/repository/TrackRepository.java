package com.github.zephyrquest.mymusic.repository;

import com.github.zephyrquest.mymusic.model.Album;
import com.github.zephyrquest.mymusic.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
    Track getTrackByAlbumAndTitleIgnoreCase(Album album, String trackTitle);
}
