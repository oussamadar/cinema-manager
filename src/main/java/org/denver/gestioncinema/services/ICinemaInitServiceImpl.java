package org.denver.gestioncinema.services;

import org.denver.gestioncinema.entities.*;
import org.denver.gestioncinema.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class ICinemaInitServiceImpl implements ICinemaInitService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ChairRepository chairRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private FilmProjectionRepository filmProjectionRepository;
    @Override
    public void initCities() {
        Stream.of("Casablanca","Marrakech","Rabat","Tanger").forEach(c->{
            City city = new City();
            city.setName(c);
            cityRepository.save(city);
        });
    }

    @Override
    public void initCinemas() {
        cityRepository.findAll().forEach(c->{
            Stream.of("MegaRama","IMAX","FOUNOUN","CHAHRAZAD","DAOULIZ").forEach(nameC->{
                Cinema cinema = new Cinema();
                cinema.setName(nameC);
                cinema.setCity(c);
                cinema.setNbRoom(3+(int)(Math.random()*7));
                cinemaRepository.save(cinema);
            });
        });
    }
    @Override
    public void initRooms() {
        cinemaRepository.findAll().forEach(c->{
            for(int i=0; i<c.getNbRoom(); i++){
                Room room = new Room();
                room.setName("Room "+i);
                room.setCinema(c);
                room.setNbChair(20+(int)(Math.random()*15));
                roomRepository.save(room);
            }
        });
    }
    @Override
    public void initChairs() {
        roomRepository.findAll().forEach(c->{
            for(int i=0; i<c.getNbChair(); i++){
                Chair chair = new Chair();
                chair.setChairNumber(i);
                chair.setRoom(c);
                chairRepository.save(chair);
            }
        });

    }

    @Override
    public void initSeances() {
        SimpleDateFormat format= new SimpleDateFormat("HH:mm");
        Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(d->{
            Seance seance = new Seance();
            try {
                seance.setStartTime(format.parse(d));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            seanceRepository.save(seance);
        });
    }

    @Override
    public void initCategories() {
        Stream.of("Action","Comedy","Drama","Fantasy").forEach(
                c->{
                    Category  category= new Category();
                    category.setName(c);
                    categoryRepository.save(category);
                }
        );
    }

    @Override
    public void initFilms() {
        List<Category> categories = categoryRepository.findAll();
        List<Double> durations = Arrays.asList(1D,1.5D,2D,2.5D);
        Stream.of("Game Of Thrones","Vikings","Spider Man","Green Book","Me before you","The fault in our starts").forEach(
                f->{
                    Film film = new Film();
                    film.setTitle(f);
                    film.setCategory(categories.get(0+(int)(Math.random()*3)));
                    film.setDuration(durations.get(0+(int)(Math.random()*3)));
                    film.setPoster(f.toLowerCase().replace(" ","")+".PNG");
                    filmRepository.save(film);
                }
        );

    }

    @Override
    public void initFilmProjections() {

        roomRepository.findAll().forEach(room -> {
            seanceRepository.findAll().forEach(seance -> {

            FilmProjection projection = new FilmProjection();
            projection.setRoom(room);
            projection.setFilm(filmRepository.findAll().get(0+(int) (Math.random()*5)));
            projection.setSeance(seance);
            projection.setProjectionDate(new Date());
            projection.setPrice(50+(int) (Math.random()*100));
            filmProjectionRepository.save(projection);
         });
        });
    }

    @Override
    public void initTickets() {
        filmProjectionRepository.findAll().forEach(filmProjection -> {
            filmProjection.getRoom().getChairs().forEach(chair -> {
                Ticket ticket = new Ticket();
                ticket.setChair(chair);
                ticket.setPrice(filmProjection.getPrice());
                ticket.setFilmProjection(filmProjection);
                ticket.setReserved(false);
                ticketRepository.save(ticket);
            });
        });
    }
}
