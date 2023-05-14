package es.uah.backUsuariosCriticas.services.usuarios;

import es.uah.backUsuariosCriticas.dao.usuarios.UsuarioDao;
import es.uah.backUsuariosCriticas.model.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    UsuarioDao usuarioDao;

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioDao.findAll();
    }


    @Override
    public Optional<Usuario> buscarUsuarioPorId(Long idUsuario) {
        return usuarioDao.findById(idUsuario);
    }

    @Override
    public Usuario buscarUsuarioPorCorreo(String correo) {
        return usuarioDao.findByCorreo(correo);
    }

    @Override
    public Usuario buscarUsuarioPorCorreoClave(String correo, String clave) {
        return usuarioDao.findByCorreoAndClave(correo, clave);
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long idUsuario) {
        usuarioDao.deleteById(idUsuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioDao.save(usuario);
    }
}
