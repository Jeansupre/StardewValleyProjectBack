package com.jean.stardewvalleyapi.repository;

import com.jean.stardewvalleyapi.model.Aldeano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAldeanoRepository extends JpaRepository<Aldeano, Long> {
}