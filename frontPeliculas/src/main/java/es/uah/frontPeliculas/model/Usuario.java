package es.uah.frontPeliculas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    private Long idUsuario;
    private String nombre;
    private String clave;
    private String correo;
    private boolean enable;
    private List<Rol> roles;
    private List<Critica> criticas;
}
