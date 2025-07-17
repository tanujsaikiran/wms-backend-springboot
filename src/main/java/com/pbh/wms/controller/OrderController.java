package com.pbh.wms.controller;

import java.time.LocalDate;
import java.util.List;

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
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        return rrepo.findById(id)
                .map(order -> {
                    order.setOrderNumber(updatedOrder.getOrderNumber());
                    order.setItemName(updatedOrder.getItemName());
                    order.setQuantity(updatedOrder.getQuantity());
                    order.setOrderDate(updatedOrder.getOrderDate());
                    order.setStatus(updatedOrder.getStatus());
                    Order savedOrder = rrepo.save(order);
                    return ResponseEntity.ok(savedOrder); 
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (rrepo.existsById(id)) {
            rrepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();  
        }
    }

}