package com.flatrock.task.packageanddeliveryservice.repository;

import com.flatrock.task.packageanddeliveryservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
