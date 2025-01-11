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
@Table(name = "preferencias")
public class Preferencia implements Serializable {

    @Serial
    private static final long serialVersionUID = -3834405571134163120L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preferencias_id_gen")
    @SequenceGenerator(name = "preferencias_id_gen", sequenceName = "preferencias_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 250)
    private String nombre;

    @Column(name = "valor_amistad", nullable = false)
    private Long valorAmistad;

}