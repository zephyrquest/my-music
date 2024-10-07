package com.github.zephyrquest.mymusic.repository;

import com.github.zephyrquest.mymusic.model.AlbumType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumTypeRepository extends JpaRepository<AlbumType, Long> {
    AlbumType getAlbumTypeByName(String name);
}
