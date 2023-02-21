package com.flatrock.task.productsservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flatrock.task.productsservice.dto.OrderDTO;
import com.flatrock.task.productsservice.service.ProductService;
import io.lettuce.core.pubsub.RedisPubSubListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Listener implements RedisPubSubListener<String, String> {
    private final ObjectMapper mapper;
    private final ProductService service;

    public Listener(ObjectMapper mapper, ProductService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @Override
    public void message(String channel, String message) {
        OrderDTO orderDTO = extractPurchaseFromPubSubMessage(message);
        service.decreaseQuantity(orderDTO.getProductAmounts().keySet());
    }

    private OrderDTO extractPurchaseFromPubSubMessage(String message) {
        try {
            return mapper.readValue(message, OrderDTO.class);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void message(String pattern, String channel, String message) {
        log.info("MyChannel " + channel + " message " + message);
    }

    @Override
    public void subscribed(String channel, long count) {
        log.info("SUBChannel " + channel + " count " + count);
    }

    @Override
    public void psubscribed(String pattern, long count) {
        log.info("PUBChannel " + pattern + " message " + count);
    }

    @Override
    public void unsubscribed(String channel, long count) {

    }

    @Override
    public void punsubscribed(String pattern, long count) {

    }
}