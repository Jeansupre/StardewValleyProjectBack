package com.jean.stardewvalleyapi.repository;

import com.jean.stardewvalleyapi.model.Preferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPreferenciaRepository extends JpaRepository<Preferencia, Long> {
}