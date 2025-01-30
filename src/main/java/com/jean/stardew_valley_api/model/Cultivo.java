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
@Table(name = "cultivos")
public class Cultivo implements Serializable {

    @Serial
    private static final long serialVersionUID = -3519987154443632150L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cultivos_id_gen")
    @SequenceGenerator(name = "cultivos_id_gen", sequenceName = "cultivos_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_item", nullable = false)
    private Long idItem;

    @Column(name = "id_semilla", nullable = false)
    private Long idSemilla;

    @Column(name = "id_estacion", nullable = false)
    private Long idEstacion;

    @Column(name = "tiempo_crecer", nullable = false)
    private Long tiempoCrecer;

    @Column(name = "vuelve_crecer", nullable = false)
    private Boolean vuelveCrecer;

    @Column(name = "tiempo_volver_crecer")
    private Long tiempoVolverCrecer;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_item", insertable=false, updatable=false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_semilla", insertable=false, updatable=false)
    private Semilla semilla;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estacion", insertable=false, updatable=false)
    private Estacion estacion;
}