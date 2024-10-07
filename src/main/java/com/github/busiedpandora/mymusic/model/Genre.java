package com.github.busiedpandora.mymusic.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
public class Genre {
    public static final Long ROCK_ID = 1L;
    public static final Long POP_ID = 2L;
    public static final Long ALTERNATIVE_ID = 3L;
    public static final Long ELECTRONIC_ID = 4L;
    public static final Long INSTRUMENTAL_ID = 5L;

    public enum Value {
        ROCK, POP, ALTERNATIVE, ELECTRONIC, INSTRUMENTAL;
    }

    @Id
    @Column
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
}
