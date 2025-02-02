package com.jean.stardewvalleyapi.repository;

import com.jean.stardewvalleyapi.model.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {

    UsuarioRol findUsuarioRolByIdUsuario(Long idUsuario);

    @Query("SELECT ur FROM UsuarioRol ur WHERE ur.usuario.username = :username")
    UsuarioRol findUsuarioByUsername(String username);
}