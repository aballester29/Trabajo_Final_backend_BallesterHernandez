package es.uah.frontPeliculas.service;

import es.uah.frontPeliculas.model.Actor;
import es.uah.frontPeliculas.model.Critica;
import es.uah.frontPeliculas.model.Pelicula;
import es.uah.frontPeliculas.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CriticaServiceImpl implements CriticaService{
    @Autowired
    RestTemplate template;

    @Autowired
    ActorService actorService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PeliculaService peliculaService;

    String url = "http://localhost:8090/api/usuarios/criticas";

    @Override
    public Page<Critica> buscarTodas(Pageable pageable) {
        Critica[] criticas = template.getForObject(url, Critica[].class);
        List<Critica> criticasList = Arrays.asList(criticas);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Critica> list;

        if(criticasList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, criticasList.size());
            list = criticasList.subList(startItem, toIndex);
        }
        Page<Critica> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), criticasList.size());
        return page;
    }

    @Override
    public Page<Critica> buscarCriticasPorIdPelicula(Long idPelicula, Pageable pageable) {
        Critica[] criticas = template.getForObject(url+"/pelicula/"+idPelicula, Critica[].class);
        List<Critica> criticasList = Arrays.asList(criticas);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Critica>list;

        if(criticasList.size() <startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, criticasList.size());
            list = criticasList.subList(startItem, toIndex);
        }
        Page<Critica> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), criticasList.size());
        return page;
    }

    @Override
    public Critica buscarCriticaPorId(Long idCritica) {
        Critica critica= template.getForObject(url+"/"+idCritica, Critica.class);
        return critica;
    }

    @Override
    public String guardarCritica(Critica critica) {
        if (critica.getIdCritica() != null && critica.getIdCritica() > 0) {
            Critica criticaBd = buscarCriticaPorId(critica.getIdCritica());
            critica.setFecha(criticaBd.getFecha());
            template.put(url, critica);
        } else {
            critica.setFecha(new Date());
            critica.setIdCritica(0L);
            template.postForObject(url, critica, String.class);
        }
        return "La nueva critica ha sido guardada!";
    }

    @Override
    public void eliminarCritica(Long idCritica) {
        template.delete(url+ "/" +  idCritica);
    }
}
