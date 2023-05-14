package es.uah.frontPeliculas.service;

import es.uah.frontPeliculas.model.Critica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CriticaService {
    Page<Critica> buscarTodas(Pageable pageable);

    Page<Critica> buscarCriticasPorIdPelicula(Long idPelicula, Pageable pageable);

    Critica buscarCriticaPorId(Long idCritica);

    String guardarCritica(Critica critica);

    void eliminarCritica(Long idCritica);
}
