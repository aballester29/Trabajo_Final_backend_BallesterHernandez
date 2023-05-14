package es.uah.gestionPeliculas.controllers;

import es.uah.gestionPeliculas.model.dto.NewPelicula;
import es.uah.gestionPeliculas.model.entities.Actor;
import es.uah.gestionPeliculas.model.entities.Pelicula;
import es.uah.gestionPeliculas.services.actores.ActorService;
import es.uah.gestionPeliculas.services.peliculas.PeliculaService;
import es.uah.gestionPeliculas.services.uploads.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/peliculas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class PeliculaController {

    @Autowired
    PeliculaService peliculaService;

    @Autowired
    ActorService actorService;

    /* -----------------------------LISTADO DE PELICULAS-------------------------------------------- */
   @GetMapping
    public List<Pelicula> findALl(Sort sort) {
        return peliculaService.findAll(sort);
    }



    /* -----------------------------BUSQUEDA DE PELICULAS-------------------------------------------- */

    /* ---------- por id -----------*/
    @GetMapping("/{id}")
    public Pelicula findById(@PathVariable("id") String id){
        Long idLong = Long.parseLong(id);
        return peliculaService.findById(idLong);
    }
    /* ---------- por titulo -----------*/
    @GetMapping("/titulo/{titulo}")
    public List<Pelicula> findByTitulo(@PathVariable("titulo") String titulo){
        return peliculaService.findByTitulo(titulo);
    }

    /* ---------- por genero -----------*/
    @GetMapping("/genero/{genero}")
    public List<Pelicula> findByGenero(@PathVariable("genero") String genero){
        return peliculaService.findByGenero(genero);
    }

    /* ---------- por actor -----------*/

    @GetMapping("/actor/{actor}")
    public List<Pelicula> findByActor(@PathVariable("actor") String actor){
        Actor actorDb = actorService.findByNombre(actor).get(0);
        return actorDb.getPeliculas();
    }
    /* -----------------------------GUARDADO DE PELICULAS-------------------------------------------- */

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@RequestBody NewPelicula pelicula, BindingResult result) {

        ResponseEntity<Map<String, Object>> responseEntity = null;

        Map<String, Object> responseAsMap = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errores = new ArrayList<>();

            for (ObjectError error : result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }

            responseAsMap.put("Errores", errores);
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);

        } else {
            try {
                Pelicula newPelicula = new Pelicula();
                newPelicula.setAnio(pelicula.getAnio());
                newPelicula.setDireccion(pelicula.getDireccion());
                newPelicula.setGenero(pelicula.getGenero());
                newPelicula.setPais(pelicula.getPais());
                newPelicula.setDuracion(pelicula.getDuracion());
                newPelicula.setTitulo(pelicula.getTitulo());
                newPelicula.setPortada(pelicula.getPortada());
                newPelicula.setSinopsis(pelicula.getSinopsis());
                newPelicula.setActores(pelicula.getActores());
                Pelicula peliculaDb = peliculaService.save(newPelicula);

                if (peliculaDb != null) {
                    responseAsMap.put("Modificada correctamente", pelicula);
                    responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CREATED);
                } else {
                    responseAsMap.put("Errores", "No se ha guardado la película");
                    responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
                }

            } catch (DataAccessException e) {
                responseAsMap.put("Errores", "No existe la presentacion");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
        }
        return responseEntity;
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> modify(@RequestBody Pelicula newPelicula, BindingResult result) {

        ResponseEntity<Map<String, Object>> responseEntity = null;

        Map<String, Object> responseAsMap = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errores = new ArrayList<>();

            for (ObjectError error : result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }

            responseAsMap.put("Errores", errores);
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);

        } else {
            try {
                Pelicula peliculaDb = peliculaService.findById(newPelicula.getId());
                newPelicula.setActores(peliculaDb.getActores());
                Pelicula pelicula = peliculaService.save(newPelicula);

                if (pelicula != null) {
                    responseAsMap.put("Modificado correctamente", pelicula);
                    responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CREATED);
                } else {
                    responseAsMap.put("Errores", "No se ha guardado el actor");
                    responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
                }

            } catch (DataAccessException e) {
                responseAsMap.put("Errores", "No existe la presentacion");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
        }
        return responseEntity;

    }

    /* -----------------------------BORRADO DE PELICULAS-------------------------------------------- */

    @DeleteMapping
    public ResponseEntity<List<Pelicula>> delete(@RequestParam(required = false) Long id) {


        peliculaService.delete(id);
        Sort sortByName = Sort.by("id");
        ResponseEntity<List<Pelicula>> responseEntity = null;

        List<Pelicula> peliculas = null;

        peliculas = peliculaService.findAll(sortByName);

        if (peliculas.size() > 0) {
            responseEntity = new ResponseEntity<List<Pelicula>>(peliculas, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<List<Pelicula>>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

}
