package com.flatrock.task.productsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {
    private Long id;
    private Map<Long, Integer> productAmounts;
    private LocalDate createdAt;
    private Long userId;
    private String clientEmail;
    private String clientPhone;
}