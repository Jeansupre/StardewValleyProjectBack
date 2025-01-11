package com.jean.stardew_valley_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario_roles")
public class UsuarioRol implements Serializable {

    @Serial
    private static final long serialVersionUID = 4152778040604903201L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_roles_id_gen")
    @SequenceGenerator(name = "usuario_roles_id_gen", sequenceName = "usuarios_roles_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "id_rol", nullable = false)
    private Long idRol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false, nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", insertable = false, updatable = false, nullable = false)
    private Rol rol;
}