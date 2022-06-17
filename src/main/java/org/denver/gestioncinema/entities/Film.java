package org.denver.gestioncinema.entities;

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
    private Collection<FilmProjection> filmProjections;
    @ManyToOne
    private Category category;



}
