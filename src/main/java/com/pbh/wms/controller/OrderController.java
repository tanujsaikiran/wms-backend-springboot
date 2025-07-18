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
import com.pbh.wms.entity.Order;
import com.pbh.wms.repository.OrderRepository;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository rrepo;

    @GetMapping
    public List<Order> listAll() {
        return rrepo.findAll();
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        order.setOrderDate(LocalDate.now());
        order.setStatus("PENDING");
        return rrepo.save(order);
    }	

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateorder(@RequestBody Order order) {
        Optional<Order> existing = rrepo.findById(order.getId());
        if (existing.isPresent()) {
            Order updated = rrepo.save(order);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        if (rrepo.existsById(id)) {
            rrepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();  
        }
    }

}