package es.uah.backUsuariosCriticas.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles", schema = "usuariosData")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRol", nullable = false)
    private Long idRol;

    @Column(name = "rol", nullable = false, length = 45)
    private String rol;
}
