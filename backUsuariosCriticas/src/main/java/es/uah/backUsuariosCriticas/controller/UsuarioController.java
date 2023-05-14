package es.uah.backUsuariosCriticas.controller;

import es.uah.backUsuariosCriticas.model.entities.Usuario;
import es.uah.backUsuariosCriticas.services.usuarios.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class UsuarioController {
    @Autowired
    UsuarioService usuariosService;

    @GetMapping("/usuarios")
    public List<Usuario> buscarTodos() {
        return usuariosService.buscarTodos();
    }

    @GetMapping("/usuarios/{id}")
    public Optional<Usuario> buscarUsuarioPorId(@PathVariable("id") Long id) {
        return usuariosService.buscarUsuarioPorId(id);
    }

    @GetMapping("/usuarios/correo/{correo}")
    public Usuario buscarUsuarioPorCorreo(@PathVariable("correo") String correo) {
        return usuariosService.buscarUsuarioPorCorreo(correo);
    }

    @GetMapping("/usuarios/login/{correo}/{clave}")
    public Usuario buscarUsuarioPorCorreoConClave(@PathVariable("correo") String correo, @PathVariable("clave") String clave) {
        return usuariosService.buscarUsuarioPorCorreoClave(correo, clave);
    }

    @PostMapping("/usuarios")
    public void guardarUsuario(@RequestBody Usuario usuario) {
        usuariosService.guardarUsuario(usuario);
    }

    @PutMapping("/usuarios")
    public void actualizarUsuario(@RequestBody Usuario usuario) {
        usuariosService.actualizarUsuario(usuario);
    }

    @DeleteMapping("/usuarios/{id}")
    public void eliminarUsuario(@PathVariable("id") Long id) {
        usuariosService.eliminarUsuario(id);
    }
}
