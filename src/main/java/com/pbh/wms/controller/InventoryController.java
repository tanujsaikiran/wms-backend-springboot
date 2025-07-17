package com.pbh.wms.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbh.wms.entity.Inventory;
import com.pbh.wms.repository.InventoryRepository;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository repo;

    @GetMapping
    public List<Inventory> listAll() {
        return repo.findAll();
    }

    @PostMapping
    public Inventory add(@RequestBody Inventory inv) {
        inv.setLastUpdated(LocalDate.now());	
        return repo.save(inv);
    }

    @PutMapping("/inventory")
    public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory inventory) {
        Optional<Inventory> existing = repo.findById(inventory.getId());
        if (existing.isPresent()) {
            Inventory updated = repo.save(inventory);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable("id") Long id) {
        if (repo.existsById(id)) {
        	repo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}