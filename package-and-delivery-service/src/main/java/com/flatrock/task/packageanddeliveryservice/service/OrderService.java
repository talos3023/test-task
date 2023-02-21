package com.flatrock.task.packageanddeliveryservice.service;

import com.flatrock.task.packageanddeliveryservice.model.Order;
import com.flatrock.task.packageanddeliveryservice.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> getAll() {
        return repository.findAll();
    }

    public Order findById(long id) {
        Optional<Order> order = repository.findById(id);
        if (order.isEmpty()) {
            throw new EntityNotFoundException("id: " + id);
        }
        return order.get();
    }

    public Object createOrder(Order order) {
        Order createdUser = repository.save(order);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
