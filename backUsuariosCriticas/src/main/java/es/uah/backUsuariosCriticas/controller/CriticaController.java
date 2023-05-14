package es.uah.backUsuariosCriticas.controller;

import es.uah.backUsuariosCriticas.model.entities.Critica;
import es.uah.backUsuariosCriticas.services.criticas.CriticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class CriticaController {
    @Autowired
    CriticaService criticaService;

    @GetMapping("/criticas")
    public List<Critica> buscarTodas() {
        return criticaService.buscarTodas();
    }

    @GetMapping("/criticas/pelicula/{idPelicula}")
    public List<Critica> buscarCriticasPorIdPelicula(@PathVariable("idPelicula") Long idPelicula) {
        return criticaService.buscarCriticasPorIdPelicula(idPelicula);
    }

    @GetMapping("/criticas/{id}")
    public Optional<Critica> buscarCriticaPorId(@PathVariable("id") Long id) {
        return criticaService.buscarCriticaPorId(id);
    }

    @PostMapping("/criticas")
    public void guardarCritica(@RequestBody Critica critica) {
        criticaService.guardarCritica(critica);
    }

    @PutMapping("/criticas")
    public void editarCritica(@RequestBody Critica critica) {
        criticaService.guardarCritica(critica);
    }

    @DeleteMapping("/criticas/{id}")
    public void eliminarCritica(@PathVariable("id") Long id) {
        criticaService.eliminarCritica(id);
    }
}
