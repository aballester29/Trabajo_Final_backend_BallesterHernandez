package es.uah.frontPeliculas.service;

import es.uah.frontPeliculas.model.Rol;

import java.util.List;

public interface RolService {
    List<Rol> buscarTodos();

    Rol buscarRolPorId(Long idRol);

    void guardarRol(Rol rol);

    void eliminarRol(Long idRol);

}
