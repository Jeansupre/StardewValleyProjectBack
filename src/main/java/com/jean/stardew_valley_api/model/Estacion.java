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
@Table(name = "estaciones")
public class Estacion implements Serializable {

    @Serial
    private static final long serialVersionUID = -2775512717122152634L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estaciones_id_gen")
    @SequenceGenerator(name = "estaciones_id_gen", sequenceName = "estaciones_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 250)
    private String nombre;

}