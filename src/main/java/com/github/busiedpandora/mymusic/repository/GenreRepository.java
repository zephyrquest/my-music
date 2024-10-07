package com.github.busiedpandora.mymusic.repository;

import com.github.busiedpandora.mymusic.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre getGenreByName(String name);
}
