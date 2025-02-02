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
@Table(name = "items")
public class Item implements Serializable {

    @Serial
    private static final long serialVersionUID = -2335143660833004484L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "items_id_gen")
    @SequenceGenerator(name = "items_id_gen", sequenceName = "items_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 250)
    private String nombre;

    @Column(name = "precio_base", nullable = false)
    private Long precioBase;

    @Column(name = "id_categoria", nullable = false)
    private Long idCategoria;

    @Column(name = "imagen", nullable = false)
    private byte[] imagen;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_categoria", insertable=false, updatable=false)
    private Categoria categoria;
}