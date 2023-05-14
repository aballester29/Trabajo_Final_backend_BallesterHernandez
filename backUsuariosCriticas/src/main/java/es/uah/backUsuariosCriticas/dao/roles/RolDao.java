package es.uah.backUsuariosCriticas.dao.roles;

import es.uah.backUsuariosCriticas.model.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDao extends JpaRepository<Rol, Long> {
}
