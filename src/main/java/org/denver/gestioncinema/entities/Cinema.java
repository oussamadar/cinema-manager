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

public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double longitude;
    private double latitude;
    private double altitude;
    private int nbRoom;
    @OneToMany(mappedBy = "cinema")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Room> rooms;
    @ManyToOne
    @JsonBackReference
    private City city;

}
