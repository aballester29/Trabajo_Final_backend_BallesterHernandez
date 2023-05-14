package es.uah.gestionPeliculas.dao;

import es.uah.gestionPeliculas.model.entities.Pelicula;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeliculaDao extends JpaRepository<Pelicula, Long> {
    // Recupera un listado de peliculas ordenado
    @Query(value = "select e from Pelicula e")
    public List<Pelicula> findAll(Sort sort);

    List<Pelicula> findByTitulo(String titulo);

    List<Pelicula> findByGenero(String genero);
}
