package com.jean.stardew_valley_api.repository;

import com.jean.stardew_valley_api.model.Preferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPreferenciaRepository extends JpaRepository<Preferencia, Long> {
}