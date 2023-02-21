package com.flatrock.task.orderservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flatrock.task.orderservice.dto.OrderDTO;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PlaceOrderController {
    private final RedisPubSubCommands<String, String> redisSynchronousPublisher;
    private final ObjectMapper mapper;
    private final static String CHANNEL_NAME = "order_channel";

    public PlaceOrderController(RedisPubSubCommands<String, String> redisSynchronousPublisher,
                          ObjectMapper mapper) {
        this.mapper = mapper;
        this.redisSynchronousPublisher = redisSynchronousPublisher;
    }

    @PostMapping("/place-order/")
    public void buyProduct(@RequestBody OrderDTO orderDTO) {
        Long publishCount = redisSynchronousPublisher.publish(CHANNEL_NAME, serializePurchaseDTO(orderDTO));
        log.debug(String.format("Published message was sent to %d subscribers", publishCount));
    }

    private String serializePurchaseDTO(OrderDTO orderDTO) {
        try {
            return mapper.writeValueAsString(orderDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
