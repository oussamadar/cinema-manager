package org.denver.gestioncinema;

import org.denver.gestioncinema.services.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionCinemaApplication implements CommandLineRunner {

	@Autowired
	private ICinemaInitService cinemaInitService;
	public static void main(String[] args) {
		SpringApplication.run(GestionCinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cinemaInitService.initCities();
		cinemaInitService.initCinemas();
		cinemaInitService.initCategories();
		cinemaInitService.initRooms();
		cinemaInitService.initChairs();
		cinemaInitService.initSeances();
		cinemaInitService.initFilms();
		cinemaInitService.initFilmProjections();
		cinemaInitService.initTickets();

	}
}
