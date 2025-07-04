package com.pbh.wms.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/{id}")
    public Inventory update(@PathVariable Long id, @RequestBody Inventory inv) {
        inv.setId(id);
        inv.setLastUpdated(LocalDate.now());
        return repo.save(inv);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}