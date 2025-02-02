package com.jean.stardewvalleyapi.repository;

import com.jean.stardewvalleyapi.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstacionRepository extends JpaRepository<Estacion, Long> {
}