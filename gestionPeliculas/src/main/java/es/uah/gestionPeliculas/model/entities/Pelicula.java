package es.uah.gestionPeliculas.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pelicula implements Serializable {

    //PROPIEDADES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Long anio;
    private Long duracion;
    private String pais;
    private String direccion;
    private String genero;
    private String sinopsis;
    private String portada;

    //RELACIONES

    //MUCHAS PELICULAS = MUCHOS ACTORES
    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "pelicula_actor",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actores;


}
