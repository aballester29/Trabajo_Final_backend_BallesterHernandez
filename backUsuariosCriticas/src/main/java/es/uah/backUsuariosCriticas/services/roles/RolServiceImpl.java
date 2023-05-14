package es.uah.backUsuariosCriticas.services.roles;

import es.uah.backUsuariosCriticas.dao.roles.RolDao;
import es.uah.backUsuariosCriticas.model.entities.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements  RolService{
    @Autowired
    RolDao rolDAO;

    @Override
    public List<Rol> buscarTodos() {
        return rolDAO.findAll();
    }

    @Override
    public Optional<Rol> buscarRolPorId(Long idRol) {
        return rolDAO.findById(idRol);
    }

    @Override
    public void guardarRol(Rol rol) {
        rolDAO.save(rol);
    }

    @Override
    public void eliminarRol(Long idRol) {
        rolDAO.deleteById(idRol);
    }
}
