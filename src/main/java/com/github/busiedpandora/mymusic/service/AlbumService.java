package com.github.busiedpandora.mymusic.service;

import com.github.busiedpandora.mymusic.model.Track;
import com.github.busiedpandora.mymusic.repository.AlbumTypeRepository;
import com.github.busiedpandora.mymusic.model.Album;
import com.github.busiedpandora.mymusic.model.Genre;
import com.github.busiedpandora.mymusic.repository.AlbumRepository;
import com.github.busiedpandora.mymusic.repository.GenreRepository;
import com.github.busiedpandora.mymusic.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AlbumTypeRepository albumTypeRepository;
    @Autowired
    private TrackRepository trackRepository;
    @Value("${basePath.albums}")
    private String albumsBasePath;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public void readAndPersistAlbums() {
        Yaml yaml = new Yaml();

        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("data.yml");

        Map<String, Object> data = yaml.load(inputStream);

        List<Map<String, Object>> albums = (List<Map<String, Object>>) data.get("albums");

        for(Map<String, Object> a : albums) {
            String title = (String) a.get("title");

            if(albumRepository.getAlbumByTitle(title) == null) {
                List<String> genresString = (List<String>) a.get("genres");
                String releaseDate = (String) a.get("releaseDate");
                String type = (String) a.get("type");
                String coverImagePath = albumsBasePath + a.get("coverImagePath");
                List<Map<String, Object>> tracksList = (List<Map<String, Object>>) a.get("tracks");

                Album album = new Album();
                album.setTitle(title);

                List<Genre> genres = new ArrayList<>();
                for (String g : genresString) {
                    Genre genre = genreRepository.getGenreByName(g.toUpperCase());
                    if (genre != null) {
                        genres.add(genre);
                    }
                }
                album.setGenres(genres);

                album.setReleaseDate(LocalDate.parse(releaseDate, dateTimeFormatter));
                album.setType(albumTypeRepository.getAlbumTypeByName(type.toUpperCase()));
                album.setCoverImagePath(coverImagePath);

                //albumRepository.save(album);
                List<Track> tracks = new ArrayList<>();
                for (Map<String, Object> t : tracksList) {
                    String trackTitle = (String) t.get("title");
                    int trackNumber = (int) t.get("number");
                    int trackLength = (int) t.get("length");
                    String audioPath = albumsBasePath + t.get("audioPath");
                    String lyricsPath = albumsBasePath + t.get("lyricsPath");

                    Track track = new Track();
                    track.setTitle(trackTitle);
                    track.setNumber(trackNumber);
                    track.setLength(trackLength);
                    track.setAudioPath(audioPath);
                    track.setLyricsPath(lyricsPath);
                    track.setAlbum(album);

                    tracks.add(track);
                }

                album.setTracks(tracks);

                albumRepository.save(album);
            }
        }
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAllOrderByReleaseDateAsc();
    }

    public Album getAlbumByTitleWithDashesAndIgnoreCase(String title) {
        return albumRepository.getAlbumByTitleIgnoreCase(title.replace("-", " "));
    }

    public List<Track> getTracksByAlbumOrderByTrackNumber(Album album) {
        return album.getTracks()
                .stream()
                .sorted(Comparator.comparingInt(Track::getNumber))
                .toList();
    }

    public Track getTrackByAlbumAndTitleWithDashesAndIgnoreCase(Album album, String trackTitle) {
        return trackRepository.getTrackByAlbumAndTitleIgnoreCase(album,
                trackTitle.replace("-", " "));
    }
}
