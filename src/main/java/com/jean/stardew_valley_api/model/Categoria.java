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
@Table(name = "categorias")
public class Categoria implements Serializable {

    @Serial
    private static final long serialVersionUID = -1440643899073437822L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorias_id_gen")
    @SequenceGenerator(name = "categorias_id_gen", sequenceName = "categorias_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 250)
    private String nombre;

}