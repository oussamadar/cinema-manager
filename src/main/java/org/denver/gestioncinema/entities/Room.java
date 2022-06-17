package org.denver.gestioncinema.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int nbChair;
    @ManyToOne
    private Cinema cinema;
    @OneToMany(mappedBy = "room")
    private Collection<Chair> chairs;
    @OneToMany(mappedBy = "room")
    private Collection<FilmProjection> filmProjections;
}
