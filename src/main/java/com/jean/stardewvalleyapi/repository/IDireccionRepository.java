package com.jean.stardewvalleyapi.repository;

import com.jean.stardewvalleyapi.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDireccionRepository extends JpaRepository<Direccion, Long> {
}