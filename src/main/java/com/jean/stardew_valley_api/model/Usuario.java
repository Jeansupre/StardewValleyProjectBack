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
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = -5687983870445731250L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_id_gen")
    @SequenceGenerator(name = "usuarios_id_gen", sequenceName = "usuarios_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 250)
    private String username;

    @Column(name = "password", nullable = false, length = 250)
    private String password;
}