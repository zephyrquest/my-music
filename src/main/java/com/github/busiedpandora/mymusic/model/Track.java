package com.github.busiedpandora.mymusic.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Track {
    @Id
    @SequenceGenerator(
            name = "track_sequence",
            sequenceName = "track_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "track_sequence"
    )
    @Column
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private int number;
    @Column(nullable = false)
    private int length;
    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;
    @Column(name = "audio_path", nullable = false)
    private String audioPath;
    @Column(name = "lyrics_path", nullable = false)
    private String lyricsPath;
}
