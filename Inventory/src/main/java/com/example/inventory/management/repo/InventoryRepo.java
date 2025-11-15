package com.example.inventory.management.repo;

import com.example.inventory.management.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer> {
    @Query(value = "SELECT * FROM inventory WHERE item_id = :id", nativeQuery = true)
    Inventory getInventoryItemByItemId(@Param("id") Integer id);
}
