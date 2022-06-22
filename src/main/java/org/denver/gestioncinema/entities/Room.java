package org.denver.gestioncinema.entities;

import com.fasterxml.jackson.annotation.*;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Chair> chairs;
    @OneToMany(mappedBy = "room")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<FilmProjection> filmProjections;
}
