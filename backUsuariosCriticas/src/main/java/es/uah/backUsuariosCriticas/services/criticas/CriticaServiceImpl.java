package es.uah.backUsuariosCriticas.services.criticas;

import es.uah.backUsuariosCriticas.dao.criticas.CriticaDao;
import es.uah.backUsuariosCriticas.model.entities.Critica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriticaServiceImpl implements CriticaService{
    @Autowired
    CriticaDao criticaDao;

    @Override
    public List<Critica> buscarTodas() {
        return criticaDao.findAll();
    }

    @Override
    public List<Critica> buscarCriticasPorIdPelicula(Long idPelicula) {
        return criticaDao.findByIdPelicula(idPelicula);
    }

    @Override
    public Optional<Critica> buscarCriticaPorId(Long idCritica) {
        return criticaDao.findById(idCritica);
    }

    @Override
    public void guardarCritica(Critica critica) {
        criticaDao.save(critica);
    }

    @Override
    public void eliminarCritica(Long idCritica) {
        criticaDao.deleteById(idCritica);
    }
}
