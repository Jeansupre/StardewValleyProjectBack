package com.jean.stardewvalleyapi.repository;

import com.jean.stardewvalleyapi.model.Cultivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICultivoRepository extends JpaRepository<Cultivo, Long> {
}