package es.uah.frontPeliculas.service;

import es.uah.frontPeliculas.model.Actor;
import es.uah.frontPeliculas.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
    public class ActorServiceImpl implements ActorService {

    @Autowired
    RestTemplate template;
    String url = "http://localhost:8090/api/peliculas/actores";
    @Override
    public Page<Actor> findAll(Pageable pageable) {
        Actor[] actores = template.getForObject(url, Actor[].class);
        List<Actor> ActorList = Arrays.asList(actores);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Actor> list;
        if (ActorList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, ActorList.size());
            list = ActorList.subList(startItem, toIndex);
        }
        Page<Actor> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize),
                ActorList.size());
        return page;
    }


    @Override
    public Actor findById(Integer idActor) {
        Actor actor = template.getForObject(url + "/" + idActor, Actor.class);
        return actor;
    }
    @Override
    public void save(Actor actor) {
        if (actor.getId() != null && actor.getId() > 0) {
            template.put(url, actor);
        } else {
            actor.setId(0L);
            template.postForObject(url, actor, String.class);
        }
    }
    @Override
    public void delete(Integer idActor) {
        template.delete(url + "?id=" + idActor);
    }
}
