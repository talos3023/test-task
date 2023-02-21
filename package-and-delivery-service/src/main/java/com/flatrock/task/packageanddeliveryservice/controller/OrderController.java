package com.flatrock.task.packageanddeliveryservice.controller;

import com.flatrock.task.packageanddeliveryservice.model.Order;
import com.flatrock.task.packageanddeliveryservice.service.OrderService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Order> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Order findOne(@PathVariable long id) {
        return service.findById(id);
    }

    @PostMapping("/")
    public Object createUser(@RequestBody Order order) {
        return service.createOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        service.deleteById(id);
    }
}
