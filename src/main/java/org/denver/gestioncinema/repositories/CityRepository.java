package org.denver.gestioncinema.repositories;

import org.denver.gestioncinema.entities.Cinema;
import org.denver.gestioncinema.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CityRepository extends JpaRepository<City,Long> {
}
