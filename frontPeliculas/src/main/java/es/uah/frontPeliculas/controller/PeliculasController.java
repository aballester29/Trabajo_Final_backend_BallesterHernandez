package es.uah.frontPeliculas.controller;

import es.uah.frontPeliculas.model.Actor;
import es.uah.frontPeliculas.model.Critica;
import es.uah.frontPeliculas.model.Pelicula;
import es.uah.frontPeliculas.paginator.PageRender;
import es.uah.frontPeliculas.service.ActorService;
import es.uah.frontPeliculas.service.CriticaService;
import es.uah.frontPeliculas.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/ppeliculas")
public class PeliculasController {

    @Autowired
    PeliculaService peliculaService;

    @Autowired
    ActorService actorService;

    @Autowired
    CriticaService criticaService;

    @GetMapping("/listar")
    public String findALl(Model model, @RequestParam(name="page",
            defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> peliculas = peliculaService.findAll(pageable);

        PageRender<Pelicula> pageRender = new PageRender<Pelicula>("/peliculas/listar",
                peliculas);
        model.addAttribute("titulo", "Listado de peliculas");
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("page", pageRender);
        return "Peliculas/listar";
    }

    @GetMapping("/busqueda")
    public String buscar(Model model) {
        return "Peliculas/busqueda";
    }

    @GetMapping("/titulo")
    public String buscarPorTitulo(Model model, @RequestParam(name="page", defaultValue="0") int page, @RequestParam("titulo") String titulo) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado;
        if (titulo.equals("")) {
            listado = peliculaService.findAll(pageable);
        } else {
            listado = peliculaService.buscarPorTitulo(titulo, pageable);
        }
        PageRender<Pelicula> pageRender = new PageRender<Pelicula>("/listado", listado);
        model.addAttribute("titulo", "Listado de peliculas por titulo");
        model.addAttribute("peliculas", listado);
        model.addAttribute("page", pageRender);
        return "Peliculas/listar";
    }

    @GetMapping("/genero")
    public String buscarPorGenero(Model model, @RequestParam(name="page", defaultValue="0") int page, @RequestParam("genero") String genero) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado = peliculaService.buscarPorGenero(genero, pageable);
        PageRender<Pelicula> pageRender = new PageRender<Pelicula>("/listado", listado);
        model.addAttribute("titulo", "Listado de peliculas por genero");
        model.addAttribute("peliculas", listado);
        model.addAttribute("page", pageRender);
        return "Peliculas/listar";
    }

    @GetMapping("/actores")
    public String buscarPorActor(Model model, @RequestParam(name="page", defaultValue="0") int page, @RequestParam("actor") String actor) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado;
        if (actor.equals("")) {
            listado = peliculaService.findAll(pageable);
        } else {
            listado = peliculaService.buscarPorActor(actor, pageable);
        }
        PageRender<Pelicula> pageRender = new PageRender<Pelicula>("/listado", listado);
        model.addAttribute("titulo", "Listado de peliculas por actor");
        model.addAttribute("peliculas", listado);
        model.addAttribute("page", pageRender);
        return "cursos/listCurso";
    }

    @GetMapping("/actores/{id}")
    public String buscarActoresporPelicula(Model model, @RequestParam(name="page", defaultValue="0") int page, @PathVariable("id") Long id) {
        Pelicula pelicula = peliculaService.findById(id);
        List<Actor> listadoA = pelicula.getActores();
        Page<Actor> listado = new PageImpl<>(listadoA);
        PageRender<Actor> pageRender = new PageRender<>("/listado", listado);
        model.addAttribute("titulo", "Listado de actores de"+ pelicula.getTitulo());
        model.addAttribute("listadoActores", listado);
        model.addAttribute("page", pageRender);
        return "Peliculas/listActores";
    }

    @GetMapping("/criticas/{id}")
    public String buscarCriticasPorPelicula(Model model, @RequestParam(name="page", defaultValue="0") int page, @PathVariable("id") Long id) {
        Pelicula pelicula = peliculaService.findById(id);
        Pageable pageable = PageRequest.of(page, 5);
        Page<Critica> listado = criticaService.buscarCriticasPorIdPelicula(id, pageable);
        PageRender<Critica> pageRender = new PageRender<>("/listado", listado);
        model.addAttribute("titulo", "Listado de criticas de"+ pelicula.getTitulo());
        model.addAttribute("listadoCriticas", listado);
        model.addAttribute("page", pageRender);
        return "Criticas/listCriticas";
    }
    @GetMapping("/alta")
    public String nuevo(Model model) {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Actor> actores = actorService.findAll(pageable);
        model.addAttribute("titulo", "Nueva pelicula");
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("actores", actores);
        return "Peliculas/alta";
    }
    @GetMapping("/editar/{id}")
    public String editarPelicula(Model model, @PathVariable("id") Long id) {
        Pelicula pelicula = peliculaService.findById(id);
        model.addAttribute("titulo", "Editar pelicula");
        model.addAttribute("pelicula", pelicula);
        return "Peliculas/alta";
    }

    @PostMapping("/guardar")
    public String guardarPelicula(Model model, Pelicula pelicula,
                               RedirectAttributes attributes) {
        peliculaService.save(pelicula);
        model.addAttribute("titulo", "Nueva pelicula");
        attributes.addFlashAttribute("msg", "Los datos del curso fueron guardados!");
        return "redirect:/ppeliculas/listar";
    }

    @GetMapping(value = "/borrar")
    public String delete(@RequestParam(value = "id") Integer id, RedirectAttributes attributes) {
        peliculaService.delete(id);
        attributes.addFlashAttribute("msg", "Los datos del curso fueron borrados!");
        return "redirect:/ppeliculas/listar";
    }
}
