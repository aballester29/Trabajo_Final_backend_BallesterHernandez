package es.uah.frontPeliculas.service;

import es.uah.frontPeliculas.model.Actor;
import es.uah.frontPeliculas.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class PeliculaServiceImpl implements PeliculaService{
    @Autowired
    RestTemplate template;
    String url = "http://localhost:8090/api/peliculas/peliculas";

    @Override
    public Page<Pelicula> findAll(Pageable pageable) {
        Pelicula[] peliculas = template.getForObject(url, Pelicula[].class);
        List<Pelicula> peliculaList = Arrays.asList(peliculas);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Pelicula> list;
        if (peliculaList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, peliculaList.size());
            list = peliculaList.subList(startItem, toIndex);
        }
        Page<Pelicula> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize),
                peliculaList.size());
        return page;
    }

    @Override
    public Pelicula findById(Long idPelicula) {
        Pelicula pelicula = template.getForObject(url + "/" + idPelicula, Pelicula.class);
        return pelicula;
    }

    @Override
    public Page<Pelicula> buscarPorTitulo(String titulo, Pageable pageable) {
        Pelicula[] peliculas = template.getForObject(url + "/titulo/" + titulo, Pelicula[].class);
        List<Pelicula> lista = Arrays.asList(peliculas);
        Page<Pelicula> page = new PageImpl<>(lista, pageable, lista.size());
        return page;
    }

    @Override
    public Page<Pelicula> buscarPorGenero(String genero, Pageable pageable) {
        Pelicula[] peliculas = template.getForObject(url + "/genero/" + genero, Pelicula[].class);
        List<Pelicula> lista = Arrays.asList(peliculas);
        Page<Pelicula> page = new PageImpl<>(lista, pageable, lista.size());
        return page;
    }

    @Override
    public Page<Pelicula> buscarPorActor(String actor, Pageable pageable) {
        Pelicula[] peliculas = template.getForObject(url + "/actor/" + actor, Pelicula[].class);
        List<Pelicula> lista = Arrays.asList(peliculas);
        Page<Pelicula> page = new PageImpl<>(lista, pageable, lista.size());
        return page;
    }
    @Override
    public void delete(Integer idPelicula) {
        template.delete(url + "?id=" + idPelicula);
    }

    @Override
    public void save(Pelicula pelicula) {
        String urlActor = "http://localhost:8001/actores";
        List<Actor> actores = new ArrayList<>();
        List<Integer> actoresChecked = pelicula.getActoresId();
        for (Integer id:actoresChecked) {
            if(id != null){
                Actor actor = template.getForObject(urlActor + "/" +id, Actor.class);
                actores.add(actor);
            }
        }
        pelicula.setActores(actores);
        if (pelicula.getId() != null && pelicula.getId() > 0) {
            template.put(url, pelicula);
        } else {
            pelicula.setId(0L);
            template.postForObject(url, pelicula, String.class);
        }
    }
   /* @Override
    public void delete(long id) {
        peliculaDao.deleteById(id);
    }

    @Override
    public Pelicula save(Pelicula pelicula) {
        return peliculaDao.save(pelicula);
    }

    @Override
    public List<Pelicula> findByTitulo(String titulo) { return peliculaDao.findByTitulo(titulo);}

    @Override
    public List<Pelicula> findByGenero(String genero){ return peliculaDao.findByGenero(genero);};*/
}
