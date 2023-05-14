package es.uah.frontPeliculas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actor {
    private Long id;
    private String nombre;
    private Date fechaNac;
    private String nacionalidad;

    private List<Pelicula> peliculas;
}
