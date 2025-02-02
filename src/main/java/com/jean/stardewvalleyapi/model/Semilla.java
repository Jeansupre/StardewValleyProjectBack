package com.jean.stardewvalleyapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "semillas")
public class Semilla implements Serializable {

    @Serial
    private static final long serialVersionUID = -7899365994876829389L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "semillas_id_gen")
    @SequenceGenerator(name = "semillas_id_gen", sequenceName = "semillas_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 250)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 250)
    private String nombre;

    @Column(name = "precio_compra")
    private Long precioCompra;

    @Size(max = 250)
    @NotNull
    @Column(name = "codigo", nullable = false, length = 250)
    private String codigo;

    @Column(name = "imagen", nullable = false)
    private byte[] imagen;

}