package com.jean.stardewvalleyapi.repository;

import com.jean.stardewvalleyapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}