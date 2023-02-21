package com.flatrock.task.notificationservice.dto;

import com.flatrock.task.notificationservice.OrderStatus;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {
    private Long id;
    private Map<Long, Integer> productAmounts;
    private LocalDate createdAt;
    private OrderStatus status;
    private Long userId;
    private String clientEmail;
    private String clientPhone;
}
