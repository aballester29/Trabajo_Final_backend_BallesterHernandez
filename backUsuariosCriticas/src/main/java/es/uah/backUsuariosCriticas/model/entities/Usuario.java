package es.uah.backUsuariosCriticas.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuarios", schema = "usuariosData")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario", nullable = false)
    private Long idUsuario;

    @Column(name = "username", nullable = false, length = 45)
    private String nombre;

    @Column(name = "password", nullable = false, length = 60)
    private String clave;

    @Column(name = "correo", nullable = false, length = 45)
    private String correo;

    @Column(name = "enable", nullable = false)
    private Boolean enable = false;

    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL}) // No poner: orphanRemoval = true
    private List<Critica> criticas;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles", joinColumns = {
            @JoinColumn(name = "Usuarios_idUsuario", referencedColumnName = "idUsuario")}, inverseJoinColumns = {
            @JoinColumn(name = "Roles_idRol", referencedColumnName = "idRol")})
    private List<Rol> roles;
}
