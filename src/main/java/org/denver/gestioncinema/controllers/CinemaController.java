package org.denver.gestioncinema.controllers;

import org.denver.gestioncinema.dto.TicketDTO;
import org.denver.gestioncinema.entities.Film;
import org.denver.gestioncinema.entities.Ticket;
import org.denver.gestioncinema.repositories.FilmRepository;
import org.denver.gestioncinema.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/films")
    public List<Film> filmList(){
        return filmRepository.findAll();
    }
    @GetMapping(path = "/poster/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getFilmPoster(@PathVariable Long id) throws IOException {
       Film film = filmRepository.findById(id).get();
        File file = new File(System.getProperty("user.home")+"/Cinema/"+film.getPoster());
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }
    @PostMapping(value = "/buyTicket",consumes ={"application/json"})
    @Transactional
    public List<Ticket> buyTicket(@RequestBody TicketDTO ticketDTO){
        List<Ticket> tickets = new ArrayList<>();
        ticketDTO.getTickets().forEach(t->{
            System.out.println(t.getId());
            Ticket updatedTicket = ticketRepository.findById(t.getId()).get();
            updatedTicket.setReserved(true);
            updatedTicket.setNameClient(ticketDTO.getNameClient());
            updatedTicket.setCodePayment(ticketDTO.getCodePayment());
            ticketRepository.save(updatedTicket);
            tickets.add(updatedTicket);
        });
        return tickets;
    }
}
