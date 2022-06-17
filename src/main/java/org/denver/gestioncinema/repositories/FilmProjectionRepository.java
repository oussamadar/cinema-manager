package org.denver.gestioncinema.repositories;

import org.denver.gestioncinema.entities.Cinema;
import org.denver.gestioncinema.entities.FilmProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface FilmProjectionRepository extends JpaRepository<FilmProjection,Long> {
}
