package com.jean.stardewvalleyapi.repository;

import com.jean.stardewvalleyapi.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemRepository extends JpaRepository<Item, Long> {
}