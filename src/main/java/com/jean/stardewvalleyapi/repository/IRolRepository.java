package com.jean.stardewvalleyapi.repository;

import com.jean.stardewvalleyapi.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long> {
}