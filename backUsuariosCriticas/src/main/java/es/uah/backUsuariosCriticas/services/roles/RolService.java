package es.uah.backUsuariosCriticas.services.roles;

import es.uah.backUsuariosCriticas.model.entities.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {
    List<Rol> buscarTodos();

    Optional<Rol> buscarRolPorId(Long idRol);

    void guardarRol(Rol rol);

    void eliminarRol(Long idRol);
}
