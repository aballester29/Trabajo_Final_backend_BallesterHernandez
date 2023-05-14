package es.uah.frontPeliculas.controller;

import es.uah.frontPeliculas.model.Critica;
import es.uah.frontPeliculas.model.Usuario;
import es.uah.frontPeliculas.paginator.PageRender;
import es.uah.frontPeliculas.service.CriticaService;
import es.uah.frontPeliculas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/ccriticas")
public class CriticaController {
    @Autowired
    CriticaService criticaService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/listado")
    public String listadoCriticas(Model model, @RequestParam(name="page", defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Critica> listado = criticaService.buscarTodas(pageable);
        PageRender<Critica> pageRender = new PageRender<Critica>("/ccriticas/listado", listado);
        model.addAttribute("titulo", "Listado de todas las criticas");
        model.addAttribute("listadoCriticas", listado);
        model.addAttribute("page", pageRender);
        return "Criticas/listCriticas";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Critica critica = new Critica();
        model.addAttribute("titulo", "Nueva critica");
        model.addAttribute("critica", critica);
        return "Criticas/formCritica";
    }

    @PostMapping("/guardar/")
    public String guardarCritica(Model model, Critica critica, RedirectAttributes attributes) {
        String resultado = criticaService.guardarCritica(critica);
        model.addAttribute("titulo", "Nueva critica");
        attributes.addFlashAttribute("msg", resultado);
        return "redirect:/ccriticas/listado";
    }

    /*@GetMapping("/nuevaCritica/{idCurso}")
    public String añadirCritica(@PathVariable("idCurso") Long idPelicula, RedirectAttributes attributes, Principal principal) {
        Usuario usuario = usuarioService.buscarUsuarioPorCorreo(principal.getName());
        Critica critica = new Critica(idPelicula, usuario);
        String resultado = criticaService.guardarCritica(critica);
        attributes.addFlashAttribute("msg", resultado);
        return "redirect:/ccursos/listado";
    }*/

    @GetMapping("/editar/{id}")
    public String editarCritica(Model model, @PathVariable("id") Long id) {
        Critica critica = criticaService.buscarCriticaPorId(id);
        model.addAttribute("titulo", "Editar critica");
        model.addAttribute("critica", critica);
        return "Criticas/formCritica";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarCritica(Model model, @PathVariable("id") Long id, RedirectAttributes attributes) {
        Critica critica = criticaService.buscarCriticaPorId(id);
        if (critica != null) {
            criticaService.eliminarCritica(id);
            attributes.addFlashAttribute("msg", "Los datos de la critica fueron borrados!");
        } else {
            attributes.addFlashAttribute("msg", "Critica no encontrada!");
        }

        return "redirect:/ccriticas/listado";
    }

}
