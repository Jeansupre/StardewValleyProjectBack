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
@Table(name = "direcciones")
public class Direccion implements Serializable {

    @Serial
    private static final long serialVersionUID = -7506884975212262549L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "direcciones_id_gen")
    @SequenceGenerator(name = "direcciones_id_gen", sequenceName = "direcciones_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_ubicacion", nullable = false)
    private Long idUbicacion;

    @Column(name = "direccion", length = 250)
    private String casa;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_ubicacion", insertable=false, updatable=false)
    private Ubicacion ubicacion;
}