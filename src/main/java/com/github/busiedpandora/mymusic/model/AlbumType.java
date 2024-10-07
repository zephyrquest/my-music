package com.github.busiedpandora.mymusic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name = "Album_Type")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class AlbumType {
    public static final Long STUDIO_ID = 1L;
    public static final Long REMIX_ID = 2L;
    public static final Long LIVE_ID = 3L;
    public static final Long COLLECTION_ID = 4L;

    public enum Value {
        STUDIO, REMIX, LIVE, COLLECTION;
    }

    @Id
    @Column
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
}
