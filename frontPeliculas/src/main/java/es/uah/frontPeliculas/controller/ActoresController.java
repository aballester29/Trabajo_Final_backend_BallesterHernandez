package es.uah.frontPeliculas.controller;

import es.uah.frontPeliculas.model.Actor;
import es.uah.frontPeliculas.model.Pelicula;
import es.uah.frontPeliculas.paginator.PageRender;

import es.uah.frontPeliculas.service.ActorService;
import es.uah.frontPeliculas.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/actores")
public class ActoresController {

    @Autowired
    ActorService actorService;
    @Autowired
    PeliculaService peliculaService;

    @GetMapping("/listar")
    public String findALl(Model model, @RequestParam(name="page",
            defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Actor> actores = actorService.findAll(pageable);

        PageRender<Actor> pageRender = new PageRender<Actor>("/actores/listar",
                actores);

        model.addAttribute("titulo", "Listado de actores");
        model.addAttribute("actores", actores);
        model.addAttribute("page", pageRender);
        return "Actores/listar";
    }


    @GetMapping("/alta")
    public String nuevo(Model model) {
        model.addAttribute("titulo", "Nuevo actor");
        Actor actor = new Actor();
        Pageable pageable = PageRequest.of(0, 5);
        model.addAttribute("actor", actor);
        return "Actores/alta";
    }

    @PostMapping("/guardar/")
    public String guardar(Model model, Actor actor, RedirectAttributes attributes) {
        if(actor != null) {
            System.out.println(actor.getNombre());
            System.out.println(actor.getNacionalidad());
            System.out.println(actor.getFechaNac());
        }
        actorService.save(actor);

        model.addAttribute("titulo", "Nuevo actor");
        attributes.addFlashAttribute("msg", "Los datos de la persona fueron guardados!");
        return "redirect:/actores/listar";
    }


    @GetMapping("/editar/{id}")
    public String editarActor(Model model, @PathVariable("id") Integer id) {
        Actor actor = actorService.findById(id);
        model.addAttribute("titulo", "Editar actor");
        model.addAttribute("actor", actor);
        return "Actores/alta";
    }

    @GetMapping(value = "/borrar")
    public String delete(@RequestParam(value = "id") Integer id, RedirectAttributes attributes) {
        actorService.delete(id);
        attributes.addFlashAttribute("msg", "Los datos del curso fueron borrados!");
        return "redirect:/actores/listar";
    }
}
