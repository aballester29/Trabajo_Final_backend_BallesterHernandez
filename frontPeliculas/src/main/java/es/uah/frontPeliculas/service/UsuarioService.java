package es.uah.frontPeliculas.service;

import es.uah.frontPeliculas.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {
    Page<Usuario> buscarTodos(Pageable pageable);

    Usuario buscarUsuarioPorId(Long idUsuario);

    Usuario buscarUsuarioPorCorreo(String correo);

    Usuario login(String correo, String clave);

    void guardarUsuario(Usuario usuario);

    void eliminarUsuario(Long idUsuario);
}
