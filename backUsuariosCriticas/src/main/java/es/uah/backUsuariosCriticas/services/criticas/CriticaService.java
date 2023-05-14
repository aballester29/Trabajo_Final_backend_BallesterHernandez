package es.uah.backUsuariosCriticas.services.criticas;

import es.uah.backUsuariosCriticas.model.entities.Critica;

import java.util.List;
import java.util.Optional;

public interface CriticaService {
    List<Critica> buscarTodas();

    List<Critica> buscarCriticasPorIdPelicula(Long idPelicula);

    Optional<Critica> buscarCriticaPorId(Long idCritica);

    void guardarCritica(Critica matricula);

    void eliminarCritica(Long idMatricula);
}
