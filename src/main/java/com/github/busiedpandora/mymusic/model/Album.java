package com.github.busiedpandora.mymusic.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Album {
    @Id
    @SequenceGenerator(
            name = "album_sequence",
            sequenceName = "album_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "album_sequence"
    )
    @Column
    private Long id;
    @Column(nullable = false, unique = true)
    private String title;
    @ManyToMany
    @JoinTable(name = "albums_genres",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;
    @ManyToOne
    private AlbumType type;
    @OneToMany(mappedBy = "album", cascade = CascadeType.PERSIST)
    private List<Track> tracks;
    @Column(name = "cover_image_path", nullable = false)
    private String coverImagePath;
}
