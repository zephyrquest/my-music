package com.github.busiedpandora.mymusic.commandlinerunner;

import com.github.busiedpandora.mymusic.model.Genre;
import com.github.busiedpandora.mymusic.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreInitRunner implements CommandLineRunner, Ordered {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public void run(String... args) throws Exception {
        Genre rock = new Genre(Genre.ROCK_ID, Genre.Value.ROCK.toString());
        Genre pop = new Genre(Genre.POP_ID, Genre.Value.POP.toString());
        Genre alternative = new Genre(Genre.ALTERNATIVE_ID, Genre.Value.ALTERNATIVE.toString());
        Genre electronic = new Genre(Genre.ELECTRONIC_ID, Genre.Value.ELECTRONIC.toString());
        Genre instrumental = new Genre(Genre.INSTRUMENTAL_ID, Genre.Value.INSTRUMENTAL.toString());

        genreRepository.saveAll(List.of(rock, pop, alternative, electronic, instrumental));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
