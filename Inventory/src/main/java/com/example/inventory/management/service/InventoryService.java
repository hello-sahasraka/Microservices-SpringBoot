package com.example.inventory.management.service;

import com.example.inventory.management.dto.InventoryDTO;
import com.example.inventory.management.model.Inventory;
import com.example.inventory.management.repo.InventoryRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<InventoryDTO> getAllInventoryItems() {
        return modelMapper.map(inventoryRepo.findAll(),new TypeToken<List<InventoryDTO>>(){}.getType());
    };

    public InventoryDTO createInventoryItem(InventoryDTO inventoryDTO) {
        Inventory savedInventoryItem = inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return modelMapper.map(savedInventoryItem,InventoryDTO.class);
    }

    public InventoryDTO updateInventoryItem(InventoryDTO inventoryDTO) {
        Inventory updatedInventoryItem = inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return modelMapper.map(updatedInventoryItem,InventoryDTO.class);
    }

    public String deleteInventoryItem(int id) {
        inventoryRepo.deleteById(id);
        return "Inventoty item deleted succesfully!";
    }
}
