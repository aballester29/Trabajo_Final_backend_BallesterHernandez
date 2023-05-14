package es.uah.backUsuariosCriticas.dao.criticas;

import es.uah.backUsuariosCriticas.model.entities.Critica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CriticaDao extends JpaRepository<Critica, Long> {
    List<Critica> findByIdPelicula(Long idPelicula);
}
