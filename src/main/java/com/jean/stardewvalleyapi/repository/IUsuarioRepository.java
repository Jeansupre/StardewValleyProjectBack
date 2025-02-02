package com.jean.stardewvalleyapi.repository;

import com.jean.stardewvalleyapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findUsuarioByUsername(String username);
}