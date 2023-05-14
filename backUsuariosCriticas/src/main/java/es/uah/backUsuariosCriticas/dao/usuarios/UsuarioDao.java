package es.uah.backUsuariosCriticas.dao.usuarios;

import es.uah.backUsuariosCriticas.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);

    Usuario findByCorreoAndClave(String correo, String clave);
}
