package es.uah.frontPeliculas.controller;

import es.uah.frontPeliculas.model.Rol;
import es.uah.frontPeliculas.model.Usuario;
import es.uah.frontPeliculas.paginator.PageRender;
import es.uah.frontPeliculas.service.RolService;
import es.uah.frontPeliculas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/cusuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolesService;

    @GetMapping(value = "/ver/{id}")
    public String ver(Model model, @PathVariable("id") Long id, RedirectAttributes attributes) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Detalle del usuario: " + usuario.getNombre());
        return "Usuarios/verUsuario";
    }

    @GetMapping("/listado")
    public String listadoUsuarios(Model model, @RequestParam(name="page", defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Usuario> listado = usuarioService.buscarTodos(pageable);
        PageRender<Usuario> pageRender = new PageRender<Usuario>("/cusuarios/listado", listado);
        model.addAttribute("titulo", "Listado de todos los usuarios");
        model.addAttribute("listadoUsuarios", listado);
        model.addAttribute("page", pageRender);
        return "Usuarios/listUsuario";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        List<Rol> roles = rolesService.buscarTodos();
        model.addAttribute("titulo", "Nuevo usuario");
        model.addAttribute("allRoles", roles);
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "Usuarios/formUsuario";
    }

    @PostMapping("/guardar/")
    public String guardarUsuario(Model model, Usuario usuario, RedirectAttributes attributes) {
        //si existe un usuario con el mismo correo no lo guardamos
        if (usuarioService.buscarUsuarioPorCorreo(usuario.getCorreo())!=null) {
            attributes.addFlashAttribute("msga", "Error al guardar, ya existe el correo!");
            return "redirect:/cusuarios/listado";
        }
        List<Rol> roles = rolesService.buscarTodos();
        model.addAttribute("allRoles", roles);
        usuarioService.guardarUsuario(usuario);
        model.addAttribute("titulo", "Nuevo usuario");
        attributes.addFlashAttribute("msg", "Los datos del usuario fueron guardados!");
        return "redirect:/cusuarios/listado";
    }

    @PostMapping("/registrar")
    public String registro(Model model, Usuario usuario, RedirectAttributes attributes) {
        //si existe un usuario con el mismo correo no lo guardamos
        if (usuarioService.buscarUsuarioPorCorreo(usuario.getCorreo())!=null) {
            attributes.addFlashAttribute("msga", "Error al guardar, ya existe el correo!");
            return "redirect:/login";
        }
        usuario.setEnable(true);
        Rol rol = rolesService.buscarRolPorId(2L);
        usuario.setRoles(Arrays.asList(rol));
        usuarioService.guardarUsuario(usuario);
        attributes.addFlashAttribute("msg", "Los datos del registro fueron guardados!");
        return "redirect:/login";
    }

    @GetMapping("/registrar")
    public String nuevoRegistro(Model model) {
        model.addAttribute("titulo", "Nuevo registro");
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "/registro";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(Model model, @PathVariable("id") Long id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        model.addAttribute("titulo", "Editar usuario");
        model.addAttribute("usuario", usuario);
        List<Rol> roles = rolesService.buscarTodos();
        model.addAttribute("allRoles", roles);
        return "Usuarios/formUsuario";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarUsuario(Model model, @PathVariable("id") Long id, RedirectAttributes attributes) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario != null) {
            usuarioService.eliminarUsuario(id);
            attributes.addFlashAttribute("msg", "Los datos del usuario fueron borrados!");
        } else {
            attributes.addFlashAttribute("msg", "Usuario no encontrado!");
        }

        return "redirect:/cusuarios/listado";
    }

}

