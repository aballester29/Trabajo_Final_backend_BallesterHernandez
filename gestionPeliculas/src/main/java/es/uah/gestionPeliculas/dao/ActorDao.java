package es.uah.gestionPeliculas.dao;

import es.uah.gestionPeliculas.model.entities.Actor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActorDao extends JpaRepository<Actor, Long> {
    // Recupera un listado de actores ordenado
    @Query(value = "select e from Actor e")
    public List<Actor> findAll(Sort sort);


    List<Actor> findByNombre(String nombre);
}
