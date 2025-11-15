package com.example.inventory.management.controller;

import com.example.inventory.management.dto.InventoryDTO;
import com.example.inventory.management.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getallinventoryitems")
    public List<InventoryDTO> getInventoryItem() {
        return inventoryService.getAllInventoryItems();
    }

    @GetMapping("getinventoryitembyitemid/{id}")
    public InventoryDTO  getInventoryItemByItemId(@PathVariable int id) {
        return inventoryService.getInventoryItemByItemId(id);
    }

    @PostMapping("/createinventoryitem")
    public InventoryDTO createInventoryItem(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.createInventoryItem(inventoryDTO);
    }

    @PutMapping("/updateinventoryitem")
    public InventoryDTO updateInventoryItem(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateInventoryItem(inventoryDTO);
    }

    @DeleteMapping("/deleteinventoryitem/{id}")
    public String deleteInventoryItem(@PathVariable int id) {
        return inventoryService.deleteInventoryItem(id);
    }

    @GetMapping("/test")
    public Map<String, String> test() {
        return Map.of("msg", "ok");
    }
}
