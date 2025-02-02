package com.jean.stardewvalleyapi.repository;

import com.jean.stardewvalleyapi.model.Semilla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISemillaRepository extends JpaRepository<Semilla, Long> {
}