package es.uah.gestionPeliculas.controllers;

import es.uah.gestionPeliculas.model.entities.Actor;
import es.uah.gestionPeliculas.services.actores.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/actores")
@RequiredArgsConstructor
public class ActorController {
    @Autowired
    ActorService actorService;


    /* -----------------------------LISTADO DE ACTORES-------------------------------------------- */
    @GetMapping
    public List<Actor> findALl(Sort sort) {
        return actorService.findAll(sort);
    }

    /* -----------------------------BUSQUEDA DE ACTORES-------------------------------------------- */

    /* ---------- por nombre -----------*/
    @GetMapping("/nombre/{nombre}")
    public List<Actor> findByNombre(@PathVariable("nombre") String nombre){
        return actorService.findByNombre(nombre);
    }

    /* ---------- por id -----------*/
    @GetMapping("/{id}")
    public Optional<Actor> findById(@PathVariable("id") String id){
        Long idLong = Long.parseLong(id);
        return actorService.findById(idLong);
    }

    /* -----------------------------GUARDADO DE ACTORES-------------------------------------------- */

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@RequestBody Actor actor, BindingResult result) {

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
                Actor actordb = actorService.save(actor);

                if (actordb != null) {
                    responseAsMap.put("Modificado correctamente", actor);
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

    /* -----------------------------MODIFICACIÓN DE ACTORES-------------------------------------------- */

    @PutMapping
    public ResponseEntity<Map<String, Object>> modify(@RequestBody Actor actor, BindingResult result) {

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
                Actor actordb = actorService.save(actor);

                if (actordb != null) {
                    responseAsMap.put("Modificado correctamente", actor);
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

    /* -----------------------------BORRADO DE ACTORES-------------------------------------------- */
    @DeleteMapping
    public ResponseEntity<List<Actor>> delete(@RequestParam(required = false) Long id, Sort sort) {

        actorService.delete(id);
        ResponseEntity<List<Actor>> responseEntity = null;

        List<Actor> actores = null;

        actores = actorService.findAll(sort);

        if (actores.size() > 0) {
            responseEntity = new ResponseEntity<List<Actor>>(actores, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<List<Actor>>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }



}
