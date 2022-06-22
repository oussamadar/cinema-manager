package org.denver.gestioncinema.dto;

import lombok.Data;
import org.denver.gestioncinema.entities.Ticket;

import java.util.List;

@Data
public class TicketDTO {
    private int codePayment;
    private String nameClient;
    private List<Ticket> tickets;
}
