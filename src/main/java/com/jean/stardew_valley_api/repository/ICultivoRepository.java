package com.jean.stardew_valley_api.repository;

import com.jean.stardew_valley_api.model.Cultivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICultivoRepository extends JpaRepository<Cultivo, Long> {
}