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
@Table(name = "aldeanos")
public class Aldeano implements Serializable {

    @Serial
    private static final long serialVersionUID = 979940925717798357L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aldeanos_id_gen")
    @SequenceGenerator(name = "aldeanos_id_gen", sequenceName = "aldeanos_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 250)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estacion_cumple", nullable = false)
    private Estacion idEstacionCumple;

    @Column(name = "dia_cumple", nullable = false)
    private Long diaCumple;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_direccion", nullable = false)
    private Direccion idDireccion;

    @Column(name = "imagen", nullable = false)
    private byte[] imagen;

}