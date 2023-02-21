package com.flatrock.task.orderservice.dto;

import com.flatrock.task.orderservice.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Map<Long, Integer> productAmounts;
    private LocalDate createdAt;
    private String clientEmail;
    private String clientPhone;
    private OrderStatus status;
    private Long userId;
}