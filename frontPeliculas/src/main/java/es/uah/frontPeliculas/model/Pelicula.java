package es.uah.frontPeliculas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pelicula {

    //PROPIEDADES
    private Long id;
    private String titulo;
    private Long anio;
    private Long duracion;
    private String pais;
    private String direccion;
    private String genero;
    private String sinopsis;
    private String portada;
    private List<Actor> actores;
    private List<Integer> actoresId;
}
