package com.flatrock.task.packageanddeliveryservice.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @ElementCollection
    private Map<Long, Integer> productAmounts;
    private LocalDate createdAt;
    private String clientEmail;
    private String clientPhone;
    private String status; //create Enum of Pending, Delivered, Canceled
    private Long userId; //maybe string depends on security
}
