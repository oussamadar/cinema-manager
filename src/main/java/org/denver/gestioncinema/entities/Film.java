package org.denver.gestioncinema.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private double duration;
    private String filmMaker;
    private String description;
    private String poster;
    private Date releaseDate;
    @OneToMany(mappedBy = "film")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<FilmProjection> filmProjections;
    @ManyToOne
    private Category category;



}
