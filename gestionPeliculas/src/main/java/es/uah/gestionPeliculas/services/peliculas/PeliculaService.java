package es.uah.gestionPeliculas.services.peliculas;

import es.uah.gestionPeliculas.model.entities.Pelicula;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface PeliculaService {

    public List<Pelicula> findAll(Sort sort);
    public void delete(Long id);
    public Pelicula save(Pelicula pelicula);

    Pelicula findById(Long id);
    List<Pelicula> findByTitulo(String titulo);
    List<Pelicula> findByGenero(String genero);
}
