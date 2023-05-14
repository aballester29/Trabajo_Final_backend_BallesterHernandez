package es.uah.frontPeliculas.service;

import es.uah.frontPeliculas.model.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActorService {

    Page<Actor> findAll(Pageable pageable);
    Actor findById(Integer idActor);
    void delete(Integer idActor);

    void save(Actor actor);
}
