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
@Table(name = "ubicaciones")
public class Ubicacion implements Serializable {

    @Serial
    private static final long serialVersionUID = 543415715332861100L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ubicaciones_id_gen")
    @SequenceGenerator(name = "ubicaciones_id_gen", sequenceName = "ubicaciones_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 250)
    private String nombre;

    @Column(name = "codigo", nullable = false)
    private String codigo;

}