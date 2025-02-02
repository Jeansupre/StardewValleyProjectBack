package com.jean.stardewvalleyapi.model;

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
@Table(name = "roles")
public class Rol implements Serializable {

    @Serial
    private static final long serialVersionUID = -6866450720016248610L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_gen")
    @SequenceGenerator(name = "roles_id_gen", sequenceName = "roles_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 250)
    private String nombre;

    @Column(name = "codigo", nullable = false, length = 250)
    private String codigo;

}