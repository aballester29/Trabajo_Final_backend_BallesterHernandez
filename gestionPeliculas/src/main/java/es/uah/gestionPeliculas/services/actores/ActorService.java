package es.uah.gestionPeliculas.services.actores;

import es.uah.gestionPeliculas.model.entities.Actor;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    public List<Actor> findAll(Sort sort);

    Optional<Actor> findById(Long id);
    public void delete(long id);
    public Actor save(Actor actor);
    List<Actor> findByNombre(String nombre);

}
