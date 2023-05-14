package es.uah.gestionPeliculas.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actor implements Serializable {

    //PROPIEDADES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Date fechaNac;
    private String nacionalidad;

    //RELACIONES

    //MUCHOS ACTORES = MUCHAS PELICULAS
    @JsonIgnore
    @ManyToMany(mappedBy = "actores")
    private List<Pelicula> peliculas;


}
