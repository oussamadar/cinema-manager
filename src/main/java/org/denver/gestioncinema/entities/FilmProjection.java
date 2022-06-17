package org.denver.gestioncinema.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmProjection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date projectionDate;
    private double price;
    @ManyToOne
    private Room room;
    @ManyToOne
    private Film film;
    @OneToMany(mappedBy = "filmProjection")
    private Collection<Ticket> tickets;
    @ManyToOne
    private Seance seance;

}
