package es.uah.gestionPeliculas.services.actores;

import es.uah.gestionPeliculas.dao.ActorDao;
import es.uah.gestionPeliculas.model.entities.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService{
    @Autowired
    private ActorDao actorDao;

    @Override
    public List<Actor> findAll(Sort sort) {
        return actorDao.findAll(sort);
    }
    @Override
    public Optional<Actor> findById(Long id){
        return actorDao.findById(id);
    }

    @Override
    public void delete(long id) {
        actorDao.deleteById(id);
    }

    @Override
    public Actor save(Actor actor) {
        return actorDao.save(actor);
    }

    @Override
    public List<Actor> findByNombre(String nombre){ return actorDao.findByNombre(nombre);};
}
