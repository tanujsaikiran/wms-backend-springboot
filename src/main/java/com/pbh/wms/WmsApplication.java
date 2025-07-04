package com.pbh.wms;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pbh.wms.entity.Inventory;
import com.pbh.wms.entity.Order;
import com.pbh.wms.repository.InventoryRepository;
import com.pbh.wms.repository.OrderRepository;

@SpringBootApplication
public class WmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(WmsApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner insertData(InventoryRepository inventoryRepo, OrderRepository orderRepo) {
        return args -> {
            Inventory inv = new Inventory();
            inv.setItemName("Laptop");
            inv.setQuantity(10);
            inv.setLocation("Rack A1");
            inv.setLastUpdated(LocalDate.now());
            inventoryRepo.save(inv);

            Order order = new Order();
            order.setOrderNumber("ORD-1001");
            order.setItemName("Laptop");
            order.setQuantity(2);
            order.setOrderDate(LocalDate.now());
            order.setStatus("PENDING");
            orderRepo.save(order);
        };
    }		
}	