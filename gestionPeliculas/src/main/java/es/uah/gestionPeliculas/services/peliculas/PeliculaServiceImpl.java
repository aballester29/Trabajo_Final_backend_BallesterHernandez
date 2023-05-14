package es.uah.gestionPeliculas.services.peliculas;

import es.uah.gestionPeliculas.dao.PeliculaDao;
import es.uah.gestionPeliculas.model.entities.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService{

    @Autowired
    private PeliculaDao peliculaDao;

    @Override
    public List<Pelicula> findAll(Sort sort) {
        return peliculaDao.findAll(sort);
    }

    @Override
    public Pelicula findById(Long id){
        Optional<Pelicula> pelicula =peliculaDao.findById(id);
        if( pelicula == null){
            return null;
        }else{
            Pelicula pelicula1 = pelicula.get();
            return pelicula1;
        }

    }

    @Override
    public void delete(Long id) {
        peliculaDao.deleteById(id);
    }

    @Override
    public Pelicula save(Pelicula pelicula) {
        return peliculaDao.save(pelicula);
    }

    @Override
    public List<Pelicula> findByTitulo(String titulo) { return peliculaDao.findByTitulo(titulo);}

    @Override
    public List<Pelicula> findByGenero(String genero){ return peliculaDao.findByGenero(genero);};
}
