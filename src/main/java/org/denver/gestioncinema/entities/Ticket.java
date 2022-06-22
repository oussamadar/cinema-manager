package org.denver.gestioncinema.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameClient;
    private double price;
    private int codePayment;
    private boolean isReserved;
    @OneToOne
    private Chair chair;
    @ManyToOne
    private FilmProjection filmProjection;

}
