package es.uah.frontPeliculas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Critica {
    private Long idCritica;
    private Long idPelicula;
    private Double puntuacion;
    private Date fecha;
    private Usuario usuario;
}
