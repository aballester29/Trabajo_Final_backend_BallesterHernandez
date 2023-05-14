package es.uah.gestionPeliculas.model.dto;

import es.uah.gestionPeliculas.model.entities.Actor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class NewPelicula {
    private String titulo;
    private Long anio;
    private Long duracion;
    private String pais;
    private String direccion;
    private String genero;
    private String sinopsis;
    private String portada;
    private MultipartFile portadaFile;
    private List<Actor> actores;
}
