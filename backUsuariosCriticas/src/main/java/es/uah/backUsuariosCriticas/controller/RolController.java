package es.uah.backUsuariosCriticas.controller;

import es.uah.backUsuariosCriticas.model.entities.Rol;
import es.uah.backUsuariosCriticas.services.roles.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class RolController {
    @Autowired
    RolService rolesService;

    @GetMapping("/roles")
    public List<Rol> buscarTodos() {
        return rolesService.buscarTodos();
    }

    @GetMapping("/roles/{id}")
    public Optional<Rol> buscarRolPorId(@PathVariable("id") Long id) {
        return rolesService.buscarRolPorId(id);
    }

    @PostMapping("/roles")
    public void guardarRol(@RequestBody Rol rol) {
        rolesService.guardarRol(rol);
    }

    @DeleteMapping("/roles/{id}")
    public void eliminarRol(@PathVariable("id") Long id) {
        rolesService.eliminarRol(id);
    }
}
