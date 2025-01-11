package com.jean.stardew_valley_api.repository;

import com.jean.stardew_valley_api.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}