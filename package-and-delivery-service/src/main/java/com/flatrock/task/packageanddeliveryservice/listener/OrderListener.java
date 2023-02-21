package com.flatrock.task.packageanddeliveryservice.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flatrock.task.packageanddeliveryservice.service.OrderService;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderListener {
    private final OrderService service;
    private final ObjectMapper mapper;
    private final static String CHANNEL_NAME = "order_channel";
    private final StatefulRedisPubSubConnection<String, String> pubSubCommands;

    public OrderListener(OrderService service,
                         ObjectMapper mapper,
                         StatefulRedisPubSubConnection<String, String> pubSubCommands) {
        this.service = service;
        this.mapper = mapper;
        this.pubSubCommands = pubSubCommands;
        this.startListening();
    }

    private void startListening() {
        pubSubCommands.addListener(new Listener(mapper, service));
        pubSubCommands.sync().subscribe(CHANNEL_NAME);
    }
}