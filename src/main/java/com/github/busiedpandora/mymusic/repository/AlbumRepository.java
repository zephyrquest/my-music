package com.github.busiedpandora.mymusic.repository;

import com.github.busiedpandora.mymusic.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album getAlbumByTitle(String title);
    Album getAlbumByTitleIgnoreCase(String title);

    @Query("SELECT a FROM Album a ORDER BY a.releaseDate ASC")
    List<Album> findAllOrderByReleaseDateAsc();

    @Query("SELECT a FROM Album a ORDER BY a.releaseDate DESC")
    List<Album> findAllOrderByReleaseDateDesc();
}
