package es.uah.frontPeliculas.service;

import es.uah.frontPeliculas.model.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PeliculaService {

    Page<Pelicula> findAll(Pageable pageable);

    Pelicula findById(Long id);

    Page<Pelicula> buscarPorTitulo(String titulo, Pageable pageable);
    Page<Pelicula> buscarPorGenero(String genero, Pageable pageable);
    Page<Pelicula> buscarPorActor(String nombre, Pageable pageable);
    void delete(Integer idPelicula);
    void save(Pelicula pelicula);

    /*List<Pelicula> findByTitulo(String titulo);
    List<Pelicula> findByGenero(String genero);*/
}
