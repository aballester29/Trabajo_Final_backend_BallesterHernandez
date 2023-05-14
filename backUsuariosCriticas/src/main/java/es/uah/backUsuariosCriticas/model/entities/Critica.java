package es.uah.backUsuariosCriticas.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "criticas", schema = "usuariosData")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Critica implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idCritica;

    @Column(name = "idPelicula", nullable = false)
    private Long idPelicula;

    @Column(name = "puntuacion", nullable = false)
    private Double puntuacion;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "Usuarios_idUsuario", referencedColumnName = "idUsuario", nullable = false)
    @JsonIgnoreProperties("criticas")
    private Usuario usuario;
}
