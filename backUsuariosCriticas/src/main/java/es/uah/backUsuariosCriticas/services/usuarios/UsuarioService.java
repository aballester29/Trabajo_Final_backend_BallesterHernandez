package es.uah.backUsuariosCriticas.services.usuarios;

import es.uah.backUsuariosCriticas.model.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> buscarTodos();

    Optional<Usuario> buscarUsuarioPorId(Long idUsuario);

    Usuario buscarUsuarioPorCorreo(String correo);

    Usuario buscarUsuarioPorCorreoClave(String correo, String clave);

    void guardarUsuario(Usuario usuario);

    void eliminarUsuario(Long idUsuario);

    void actualizarUsuario(Usuario usuario);

}
