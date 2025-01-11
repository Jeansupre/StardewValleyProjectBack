package com.jean.stardew_valley_api.repository;

import com.jean.stardew_valley_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findUsuarioByUsername(String username);
}