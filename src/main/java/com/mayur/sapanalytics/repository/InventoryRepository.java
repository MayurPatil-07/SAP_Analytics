package com.mayur.sapanalytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mayur.sapanalytics.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
