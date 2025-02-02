package com.jean.stardewvalleyapi.repository;

import com.jean.stardewvalleyapi.model.Regalo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRegaloRepository extends JpaRepository<Regalo, Long> {
}