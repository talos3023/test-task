package com.flatrock.task.notificationservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flatrock.task.notificationservice.dto.OrderDTO;
import com.flatrock.task.notificationservice.service.NotificationService;
import io.lettuce.core.pubsub.RedisPubSubListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Listener implements RedisPubSubListener<String, String> {
    private final ObjectMapper mapper;
    private final NotificationService notificationService;

    public Listener(ObjectMapper mapper, NotificationService notificationService) {
        this.mapper = mapper;
        this.notificationService = notificationService;
    }

    @Override
    public void message(String channel, String message) {
        log.info("channel " + channel + " message " + message);
        OrderDTO orderDTO = extractPurchaseFromPubSubMessage(message);
        String subject = "Order was successfully placed";
        String context = "order with id: " + orderDTO.getId() + " was accepted with status pending";
        notificationService.sendNotification(orderDTO.getClientEmail(),subject, context);
        notificationService.sendSMS(orderDTO.getClientPhone(), subject + " " + context);
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