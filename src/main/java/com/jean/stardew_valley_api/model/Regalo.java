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
@Table(name = "regalos")
public class Regalo implements Serializable {

    @Serial
    private static final long serialVersionUID = 4831022222265966568L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "regalos_id_gen")
    @SequenceGenerator(name = "regalos_id_gen", sequenceName = "regalos_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_item", nullable = false)
    private Item idItem;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_aldeano", nullable = false)
    private Aldeano idAldeano;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_preferencia", nullable = false)
    private Preferencia idPreferencia;

}